package br.com.wnfa.alurachallenge.entity;

import java.math.BigDecimal;
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

import br.com.wnfa.alurachallenge.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_expense")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDate date;
	
	@NotNull
    @Column(length=100)
    private String description;
	
	@NotNull
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	private Category category;

	@NotNull
    private BigDecimal value;
}