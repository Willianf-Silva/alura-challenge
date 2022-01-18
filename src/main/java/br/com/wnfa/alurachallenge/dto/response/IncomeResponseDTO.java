package br.com.wnfa.alurachallenge.dto.response;

import java.time.LocalDate;

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
	
	private LocalDate data;
	
    private String descricao;

    private double valor;
}
