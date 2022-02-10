package br.com.wnfa.alurachallenge.resource.swagger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Token")
public interface TokenResourceSwagger {

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Simula o logout removendo o cookie com o refresh token")
	public void revoke(
			@ApiIgnore HttpServletRequest req, 
			@ApiIgnore HttpServletResponse resp) throws Exception;
}
