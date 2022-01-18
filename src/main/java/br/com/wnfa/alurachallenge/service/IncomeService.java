package br.com.wnfa.alurachallenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;

public interface IncomeService {

	/**
	 * Cria uma nova receita
	 * @param receitaRequestDTO
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO createNewIncome(IncomeRequestDTO incomeRequestDTO) throws Exception;

	/**
	 * Atualiza uma receita existente no banco de dados
	 * @param id
	 * @param receitaRequestDTO
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO updateIncome(Long id, IncomeRequestDTO incomeRequestDTO) throws Exception;

	/**
	 * Busca uma receita existente no banco de dados
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO findById(Long id) throws Exception;

	/**	
	 * Busca todas as receitas existentes no banco de dados
	 * @return
	 */
	Page<IncomeResponseDTO> findAll(Pageable pageable);
}