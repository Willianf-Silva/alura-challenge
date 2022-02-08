package br.com.wnfa.alurachallenge.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import br.com.wnfa.alurachallenge.config.property.WnfaApiProperty;

/*
 * Essa classe intercepta a resposta da API de acordo com o objeto inserido no implements
 * nesse caso, toda vez que retornar um objeto OAuth2AccessToken ele vai interceptar e 
 * fazer alguma operação.
 * 
 * o método supports é onde iremos aplicar a lógica se ele vai fazer alguma operação ou não,
 * nesse caso se o nome do método for postAccessToken ele vai chamar o metodo beforeBodyWrite.
 * OBS.: Você pode utilizar a lógica dessa classe de processamento para qualquer coisa, basta mudar a classe que 
 * está entre o diamante no implements e montar a lógica para os outros métodos
 * 
 * o método beforeBodyWrite é onde será executado a operação
 */
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{
	
	@Autowired
	private WnfaApiProperty wnfaApiProperty;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse res = ((ServletServerHttpResponse) response).getServletResponse();
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		
		String refreshToken = body.getRefreshToken().getValue();
		
		adicionarRefreshTokenNoCookie(refreshToken, req, res);
		removerRefreshTokenBody(token);
		
		return body;
	}

	private void removerRefreshTokenBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse res) {
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(wnfaApiProperty.getSecurity().isEnableHttps());
		refreshTokenCookie.setPath(req.getContextPath()+"/oauth/token");
		refreshTokenCookie.setMaxAge(2592000); // 30 dias
		res.addCookie(refreshTokenCookie);
	}

}
