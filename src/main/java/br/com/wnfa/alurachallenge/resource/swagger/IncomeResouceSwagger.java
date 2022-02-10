package br.com.wnfa.alurachallenge.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import br.com.wnfa.alurachallenge.repository.filter.IncomeFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Receitas")
public interface IncomeResouceSwagger {

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Inclui uma nova receita.")
	public ResponseEntity<IncomeResponseDTO> newIncome(
			@ApiParam("Receita que será incluída") IncomeRequestDTO incomeRequestDTO,
			HttpServletResponse resp
			) throws Exception;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualiza uma receita existente")
	public ResponseEntity<IncomeResponseDTO> updateIncome(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id,
			@ApiParam("Receita que será incluída.") IncomeRequestDTO incomeRequestDTO
			) throws Exception;

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca uma receita cadastrada no banco de dados")
	public ResponseEntity<IncomeResponseDTO> findById(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id
			) throws Exception;
	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todas as receitas cadastradas no banco de dados")
	public ResponseEntity<?> findAll(
			@ApiParam("Filtro para consulta de receita") IncomeFilter incomeFilter,
			@ApiIgnore Pageable pageable
			);
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todas as receitas cadastradas de acordo com o ano e mês informado")
	public ResponseEntity<Page<IncomeResponseDTO>> findByMesAndAno(
			@ApiParam(value = "Filtrar o ano da receita", example = "2030") @PathVariable Integer year,
			@ApiParam(value = "Filtrar o mês da receita", example = "12") @PathVariable Integer month,
			@ApiIgnore Pageable pageable
			);
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Remove uma receita cadastrada no banco de dados")
	public ResponseEntity<?> deleteById(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id
			) throws Exception;
}