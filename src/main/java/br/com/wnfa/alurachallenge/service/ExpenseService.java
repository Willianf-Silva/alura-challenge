package br.com.wnfa.alurachallenge.service;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;

public interface ExpenseService {

	ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception;

	ExpenseResponseDTO findById(Long id) throws Exception;

}
