package br.com.wnfa.alurachallenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfa.alurachallenge.entity.ExpenseDO;

public interface ExpenseRepository extends JpaRepository<ExpenseDO, Long>{

	List<ExpenseDO> findByDateBetweenAndDescriptionIgnoreCase(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
			String description);

}
