package br.com.wnfa.alurachallenge.dto.response;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryResponseDTO {
	
	@ApiModelProperty(notes = "Total de receita no período", required = true, example = "900.00")
	private BigDecimal totalIncome;
	
	@ApiModelProperty(notes = "Total de despesa no período", required = true, example = "500.00")
	private BigDecimal totalExpense;
	
	@ApiModelProperty(notes = "Saldo final no período", required = true, example = "400.00")
	private BigDecimal finalBalance;
	
	@ApiModelProperty(notes = "Lista de categorias e seus gastos no período", required = true)
	private List<ExpenseCategoryDTO> expenseCategory;
}
