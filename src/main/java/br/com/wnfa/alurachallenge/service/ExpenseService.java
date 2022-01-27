package br.com.wnfa.alurachallenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;

public interface ExpenseService {

	ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO findById(Long id) throws Exception;

	Page<ExpenseResponseDTO> findAll(ExpenseFilter expenseFilter, Pageable pageable);

	void deleteById(Long id) throws Exception;

}
