package br.com.wnfa.alurachallenge.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class IncomeRequestDTO{

	@ApiModelProperty(notes = "Data da receita", required = true, example = "05/12/2030")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@ApiModelProperty(notes = "Descrição da receita", required = true, example = "Salário")
	@NotBlank
    private String description;

	@ApiModelProperty(notes = "Valor da receita", required = true, example = "900.00")
    @DecimalMin("0.01")
    private BigDecimal value;
}