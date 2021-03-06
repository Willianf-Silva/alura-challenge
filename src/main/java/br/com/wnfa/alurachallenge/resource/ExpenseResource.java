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

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.event.ResourceCreatedEvent;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;
import br.com.wnfa.alurachallenge.resource.swagger.ExpenseResouceSwagger;
import br.com.wnfa.alurachallenge.service.ExpenseService;

@RestController
@RequestMapping("/api/v1/despesas")
public class ExpenseResource extends ResourceBase<ExpenseResponseDTO> implements ExpenseResouceSwagger {
	@Autowired
	private ExpenseService expenseService;

	@Autowired
    private ApplicationEventPublisher publicarEvento;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ExpenseResponseDTO> newIncome(@RequestBody @Valid ExpenseRequestDTO expenseRequestDTO, HttpServletResponse resp) throws Exception {
		ExpenseResponseDTO response = expenseService.createNewExpense(expenseRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ExpenseResponseDTO> updateIncome(@PathVariable Long id,
			@RequestBody @Valid ExpenseRequestDTO expenseRequestDTO) throws Exception {

		ExpenseResponseDTO response = expenseService.updateExpense(id, expenseRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<ExpenseResponseDTO> findById(@PathVariable Long id) throws Exception {
		ExpenseResponseDTO response = expenseService.findById(id);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{ano}/{mes}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<ExpenseResponseDTO>> findByMesAndAno(@PathVariable Integer ano, @PathVariable Integer mes, Pageable pageable){
		Page<ExpenseResponseDTO> expenses = expenseService.findByYearAndMonth(ano, mes, pageable);
		return responderListaDeItensPaginada(expenses);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findAll(ExpenseFilter expenseFilter, Pageable pageable) {
		Page<ExpenseResponseDTO> response = expenseService.findAll(expenseFilter, pageable);
		return responderListaDeItensPaginada(response);
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws Exception{
		expenseService.deleteById(id);
		return responderSucesso();
	}
}