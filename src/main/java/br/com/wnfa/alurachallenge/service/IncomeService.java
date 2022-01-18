package br.com.wnfa.alurachallenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;

public interface IncomeService {

	/**
	 * Cria uma nova receita
	 * @param receitaRequestDTO - Dados de entrada da receita para salvar no banco de dados
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO createNewIncome(IncomeRequestDTO incomeRequestDTO) throws Exception;

	/**
	 * Atualiza uma receita existente no banco de dados
	 * @param id - Identificador unico da receita no banco de dados
	 * @param receitaRequestDTO - Dados de entrada da receita para salvar no banco de dados
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO updateIncome(Long id, IncomeRequestDTO incomeRequestDTO) throws Exception;

	/**
	 * Busca uma receita existente no banco de dados
	 * @param id - Identificador unico da receita no banco de dados
	 * @return
	 * @throws Exception 
	 */
	IncomeResponseDTO findById(Long id) throws Exception;

	/**	
	 * Busca todas as receitas existentes no banco de dados
	 * @return
	 */
	Page<IncomeResponseDTO> findAll(Pageable pageable);

	/**
	 * Remove uma receita existente através do parametro
	 * @param id - Identificador unico da receita no banco de dados
	 * @throws Exception 
	 */
	void deleteById(Long id) throws Exception;
}