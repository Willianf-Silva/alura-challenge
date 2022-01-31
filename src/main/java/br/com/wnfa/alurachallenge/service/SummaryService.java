package br.com.wnfa.alurachallenge.service;

import br.com.wnfa.alurachallenge.dto.response.SummaryResponseDTO;

public interface SummaryService {

	SummaryResponseDTO findByYearAndMonth(Integer year, Integer month);

}
