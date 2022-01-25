package br.com.wnfa.alurachallenge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
	ALIMENTACAO("Alimentação"),
	SAUDE("Saúde"),
	MORADIA("Moradia"),
	TRANSPORTE("Transporte"),
	EDUCACAO("Educação"),
	LAZER("Lazer"),
	IMPREVISTOS("Imprevistos"),
	OUTRAS("Outras");

	private String descricao;
}