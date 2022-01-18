package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Despesas")
public interface ExpenseResouceSwagger {

	@ApiOperation("Inclui uma nova despesa.")
	public ResponseEntity<ExpenseResponseDTO> newIncome(
			@ApiParam("Despesa que será incluída") @RequestBody ExpenseRequestDTO expenseRequestDTO,
			HttpServletResponse resp) throws Exception;

	@ApiOperation("Atualiza uma despesa existente")
	public ResponseEntity<ExpenseResponseDTO> updateIncome(
			@ApiParam(value = "Identificador da despesa", example = "01") @PathVariable Long id,
			@ApiParam("Despesa que será incluída.") @RequestBody ExpenseRequestDTO expenseRequestDTO) throws Exception;

	@ApiOperation("Busca uma despesa cadastrada no banco de dados")
	public ResponseEntity<ExpenseResponseDTO> findById(
			@ApiParam(value = "Identificador da despesa", example = "01") @PathVariable Long id) throws Exception;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiOperation("Lista todas as despesas cadastradas no banco de dados")
	public ResponseEntity<?> findAll(@ApiIgnore Pageable pageable);


	@ApiOperation("Remove uma despesa cadastrada no banco de dados")
	public ResponseEntity<?> deleteById(
			@ApiParam(value = "Identificador da despesa", example = "01") @PathVariable Long id
			) throws Exception;
}