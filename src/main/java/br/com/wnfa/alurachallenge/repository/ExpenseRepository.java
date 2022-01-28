package br.com.wnfa.alurachallenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.repository.custom.ExpenseRepositoryCustom;

public interface ExpenseRepository extends JpaRepository<ExpenseDO, Long>, ExpenseRepositoryCustom{

	List<ExpenseDO> findByDateBetweenAndDescriptionIgnoreCase(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
			String description);

	Page<ExpenseDO> findByDateBetweenOrderByDateAsc(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
			Pageable pageable);

}
