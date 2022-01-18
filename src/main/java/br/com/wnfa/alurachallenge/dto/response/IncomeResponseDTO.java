package br.com.wnfa.alurachallenge.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponseDTO {

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
    private String description;

    private double value;
}
