package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

	@ApiOperation("Inclui uma nova receita.")
	public ResponseEntity<IncomeResponseDTO> newIncome(
			@ApiParam("Receita que será incluída") @RequestBody IncomeRequestDTO incomeRequestDTO,
			HttpServletResponse resp
			) throws Exception;

	@ApiOperation("Atualiza uma receita existente")
	public ResponseEntity<IncomeResponseDTO> updateIncome(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id,
			@ApiParam("Receita que será incluída.") @RequestBody IncomeRequestDTO incomeRequestDTO
			) throws Exception;

	@ApiOperation("Busca uma receita cadastrada no banco de dados")
	public ResponseEntity<IncomeResponseDTO> findById(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id
			) throws Exception;
	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiOperation("Lista todas as receitas cadastradas no banco de dados")
	public ResponseEntity<?> findAll(
			@ApiParam("Filtro para consulta de receita") IncomeFilter incomeFilter,
			@ApiIgnore Pageable pageable
			);
	
	@ApiOperation("Remove uma receita cadastrada no banco de dados")
	public ResponseEntity<?> deleteById(
			@ApiParam(value = "Identificador da receita", example = "01") @PathVariable Long id
			) throws Exception;
}