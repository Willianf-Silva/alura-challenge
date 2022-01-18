package br.com.wnfa.alurachallenge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
	MORADIA("Moradia"),
	COMUNICACAO("Comunicação"),
	TRANSPORTE("Transporte"),
	OUTROS("Outros");

	private String descricao;
}