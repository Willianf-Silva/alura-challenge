package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Receitas")
public interface IncomeResouceSwagger {

	@ApiOperation("Inclui um novo produto ou serviço.")
	public ResponseEntity<IncomeResponseDTO> newIncome(
			@ApiParam("Serviço ou produto que será incluído.") @RequestBody IncomeRequestDTO serviceRequestDTO,
			HttpServletResponse resp) throws Exception;

	@ApiOperation("Atualiza um produto ou serviço existente.")
	public ResponseEntity<IncomeResponseDTO> updateIncome(
			@ApiParam(value = "ID do serviço ou produto", example = "01") @PathVariable Long id,
			@ApiParam("Serviço ou produto que será incluído.") @RequestBody IncomeRequestDTO serviceRequestDTO
			) throws Exception;

	public ResponseEntity<IncomeResponseDTO> findById(
			@ApiParam(value = "ID do serviço ou produto", example = "01") @PathVariable Long id
			) throws Exception;
	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiOperation("Lista todos os servios cadastrados no banco de dados")
	public ResponseEntity<?> findAll(@ApiIgnore Pageable pageable);
}