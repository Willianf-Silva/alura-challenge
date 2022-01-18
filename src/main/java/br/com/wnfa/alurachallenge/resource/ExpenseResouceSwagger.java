package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Despesas")
public interface ExpenseResouceSwagger {

	@ApiOperation("Inclui uma nova despesa.")
	public ResponseEntity<ExpenseResponseDTO> newIncome(
			@ApiParam("Despesa que será incluída") @RequestBody ExpenseRequestDTO expenseRequestDTO,
			HttpServletResponse resp
			) throws Exception;

}