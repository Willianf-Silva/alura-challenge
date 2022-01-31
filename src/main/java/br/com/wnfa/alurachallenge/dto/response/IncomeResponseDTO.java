package br.com.wnfa.alurachallenge.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponseDTO {

	@ApiModelProperty(notes = "Identificador da receita", required = true, example = "1")
	private Long id;
	
	@ApiModelProperty(notes = "Data da receita", required = true, example = "25/12/2030")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@ApiModelProperty(notes = "Descrição da receita", required = true, example = "Salário")
    private String description;

	@ApiModelProperty(notes = "Valor da receita", required = true, example = "900.00")
    private BigDecimal value;
}
