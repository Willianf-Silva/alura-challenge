package br.com.wnfa.alurachallenge.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfa.alurachallenge.enums.Category;
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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
    private String description;
    
    private Category category;

    private double value;
}
