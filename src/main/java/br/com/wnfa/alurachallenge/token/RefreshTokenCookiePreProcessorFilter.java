package br.com.wnfa.alurachallenge.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter{

	private static final String OAUTH_TOKEN_PATH = "/oauth/token";
	private static final String GRANT_TYPE = "grant_type";
	private static final String REFRESH_TOKEN_PARAMETER = "refresh_token";
	private static final String REFRESH_TOKEN_NAME = "refreshToken";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (OAUTH_TOKEN_PATH.equalsIgnoreCase(req.getRequestURI()) 
				&& REFRESH_TOKEN_PARAMETER.equals(req.getParameter(GRANT_TYPE)) 
				&& req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals(REFRESH_TOKEN_NAME)) {
					String refreshTokenValue = cookie.getValue();
					
					req = new MyServletRequestWrapper(req, refreshTokenValue);
				}
			}
		}
		
		chain.doFilter(req, response);
	}
	
	static class MyServletRequestWrapper extends HttpServletRequestWrapper{
		
		private String refreshTokenValue;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshTokenValue) {
			super(request);
			this.refreshTokenValue = refreshTokenValue;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put(REFRESH_TOKEN_PARAMETER, new String[] {refreshTokenValue});
			map.setLocked(true);
			return map;
		}
		
	}

}
