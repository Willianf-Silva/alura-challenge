package br.com.wnfa.alurachallenge.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ExpenseResponseDTO {

	@ApiModelProperty(notes = "Identificador da despesa", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Data da despesa", required = true, example = "30/12/2030")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@ApiModelProperty(notes = "Descrição da despesa", required = true, example = "Aluguel")
    private String description;
    
    @ApiModelProperty(notes = "Categoria da despesa", required = true, example = "MORADIA")
    private Category category;

    @ApiModelProperty(notes = "Valor da despesa", required = true, example = "500.00")
    private BigDecimal value;
}
