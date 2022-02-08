package br.com.wnfa.alurachallenge.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wnfa.alurachallenge.dto.response.SummaryResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Resumo")
public interface SummaryResourceSwagger {

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Resumo geral para um determinado período")
	public ResponseEntity<SummaryResponseDTO> findByMesAndAno(
			@ApiParam(value = "Filtrar o ano do resumo", example = "2030") @PathVariable Integer ano, 
			@ApiParam(value = "Filtrar o mês do resumo", example = "12") @PathVariable Integer mes
			);
}
