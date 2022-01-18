package br.com.wnfa.alurachallenge.dto.response;

import java.time.LocalDate;

import br.com.wnfa.alurachallenge.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDTO {

	private Long id;
	
	private LocalDate data;
	
    private String descricao;
    
    private Categoria categoria;

    private double valor;
}
