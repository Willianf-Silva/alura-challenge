package br.com.wnfa.alurachallenge.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import br.com.wnfa.alurachallenge.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
    @Column(length=100, unique = true)
    private String descricao;
	
	@NotNull
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@NotNull
    private double valor;
}