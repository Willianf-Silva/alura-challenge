package br.com.wnfa.alurachallenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseCategoryDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;

public interface ExpenseService {

	ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO findById(Long id) throws Exception;

	Page<ExpenseResponseDTO> findAll(ExpenseFilter expenseFilter, Pageable pageable);

	void deleteById(Long id) throws Exception;

	/**
	 * Método responsável por buscar todas as despesas para determinado ano e mes
	 * @param year
	 * @param month
	 * @param pageable 
	 * @return
	 */
	Page<ExpenseResponseDTO> findByYearAndMonth(Integer year, Integer month, Pageable pageable);

	/**
	 * Método responsável por obter a somatoria das despesas no mês
	 * @param year
	 * @param month
	 * @return
	 */
	Optional<BigDecimal> summaryByMonth(Integer year, Integer month);

	/**
	 * Método responsável por obter a somatória das despesas por categoria
	 * @param year
	 * @param month
	 * @return
	 */
	List<ExpenseCategoryDTO> summaryByCategory(Integer year, Integer month);

}
