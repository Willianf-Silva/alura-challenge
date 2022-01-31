package br.com.wnfa.alurachallenge.dto.response;

import java.math.BigDecimal;

import br.com.wnfa.alurachallenge.enums.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCategoryDTO {
	@ApiModelProperty(notes = "Categoria", required = true, example = "MORADIA")
	private Category category;
	
	@ApiModelProperty(notes = "Total gasto", required = true, example = "500.00")
	private BigDecimal finalBalance;
}
