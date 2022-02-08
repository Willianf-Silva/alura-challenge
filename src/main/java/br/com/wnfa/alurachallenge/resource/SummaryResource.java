package br.com.wnfa.alurachallenge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfa.alurachallenge.dto.response.SummaryResponseDTO;
import br.com.wnfa.alurachallenge.service.SummaryService;

@RestController
@RequestMapping("/api/v1/resumos")
public class SummaryResource extends ResourceBase<SummaryResponseDTO> implements SummaryResourceSwagger{
	
	@Autowired
	private SummaryService summaryService;

	@GetMapping("/{ano}/{mes}")
	@PreAuthorize("hasRole('ROLE_MASTER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<SummaryResponseDTO> findByMesAndAno(@PathVariable Integer ano, @PathVariable Integer mes){
		SummaryResponseDTO summaryResponseDTO = summaryService.findByYearAndMonth(ano, mes);
		return responderSucessoComItem(summaryResponseDTO);
	}
}
