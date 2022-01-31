package br.com.wnfa.alurachallenge.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wnfa.alurachallenge.dto.response.ExpenseCategoryDTO;
import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.repository.custom.ExpenseRepositoryCustom;

public interface ExpenseRepository extends JpaRepository<ExpenseDO, Long>, ExpenseRepositoryCustom{

	List<ExpenseDO> findByDateBetweenAndDescriptionIgnoreCase(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
			String description);

	Page<ExpenseDO> findByDateBetweenOrderByDateAsc(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth,
			Pageable pageable);

	@Query("SELECT SUM(e.value) FROM ExpenseDO e WHERE e.date BETWEEN :firstDayOfMonth AND :lastDayOfMonth")
	Optional<BigDecimal> findSumValueMonth(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);

	@Query("SELECT new br.com.wnfa.alurachallenge.dto.response.ExpenseCategoryDTO(e.category, SUM(e.value)) FROM ExpenseDO e " +
            " WHERE e.date BETWEEN :firstDayOfMonth AND :lastDayOfMonth " +
            " group by e.category")
	List<ExpenseCategoryDTO> summaryCategoryByMonth(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);
	
}
