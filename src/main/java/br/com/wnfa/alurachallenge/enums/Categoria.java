package br.com.wnfa.alurachallenge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categoria {
	MORADIA("Moradia"),
	COMUNICACAO("Comunicação"),
	TRANSPORTE("Transporte"),
	OUTROS("Outros");

	private String descricao;
}