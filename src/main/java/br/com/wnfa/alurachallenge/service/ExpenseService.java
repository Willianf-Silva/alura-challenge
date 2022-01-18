package br.com.wnfa.alurachallenge.service;

import javax.validation.Valid;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;

public interface ExpenseService {

	ExpenseResponseDTO createNewExpense(@Valid ExpenseRequestDTO expenseRequestDTO) throws Exception;

}
