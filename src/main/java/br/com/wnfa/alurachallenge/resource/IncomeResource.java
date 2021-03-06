package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import br.com.wnfa.alurachallenge.event.ResourceCreatedEvent;
import br.com.wnfa.alurachallenge.repository.filter.IncomeFilter;
import br.com.wnfa.alurachallenge.resource.swagger.IncomeResouceSwagger;
import br.com.wnfa.alurachallenge.service.IncomeService;

@RestController
@RequestMapping("/api/v1/receitas")
public class IncomeResource extends ResourceBase<IncomeResponseDTO> implements IncomeResouceSwagger {
	@Autowired
	private IncomeService incomeService;

	@Autowired
    private ApplicationEventPublisher publicarEvento;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<IncomeResponseDTO> newIncome(@RequestBody @Valid IncomeRequestDTO incomeRequestDTO, HttpServletResponse resp) throws Exception {
		IncomeResponseDTO response = incomeService.createNewIncome(incomeRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<IncomeResponseDTO> updateIncome(@PathVariable Long id,
			@RequestBody @Valid IncomeRequestDTO incomeRequestDTO) throws Exception {

		IncomeResponseDTO response = incomeService.updateIncome(id, incomeRequestDTO);
		return responderSucessoComItem(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<IncomeResponseDTO> findById(@PathVariable Long id) throws Exception {
		IncomeResponseDTO response = incomeService.findById(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{ano}/{mes}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<IncomeResponseDTO>> findByMesAndAno(@PathVariable Integer ano, @PathVariable Integer mes, Pageable pageable){
		Page<IncomeResponseDTO> incomes = incomeService.findByYearAndMonth(ano, mes, pageable);
		return responderListaDeItensPaginada(incomes);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findAll(IncomeFilter incomeFilter, Pageable pageable) {
		Page<IncomeResponseDTO> response = incomeService.findAll(incomeFilter, pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@DeleteMapping("{id}")	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws Exception{
		incomeService.deleteById(id);
		return responderSucesso();
	}
}