package br.com.wnfa.alurachallenge.repository.filter;

import br.com.wnfa.alurachallenge.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilter {
	private String description;
	private Category category;
}