package br.com.wnfa.alurachallenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;

public interface ExpenseService {

	ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO findById(Long id) throws Exception;

	Page<ExpenseResponseDTO> findAll(Pageable pageable);

}
