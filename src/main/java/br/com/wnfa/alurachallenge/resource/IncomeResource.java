package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import br.com.wnfa.alurachallenge.service.IncomeService;

@RestController
@RequestMapping("/api/v1/receitas")
public class IncomeResource extends ResourceBase<IncomeResponseDTO> implements IncomeResouceSwagger {
	@Autowired
	private IncomeService incomeService;

	@Autowired
    private ApplicationEventPublisher publicarEvento;
	
	@PostMapping
	public ResponseEntity<IncomeResponseDTO> newIncome(@RequestBody @Valid IncomeRequestDTO incomeRequestDTO, HttpServletResponse resp) throws Exception {
		IncomeResponseDTO response = incomeService.createNewIncome(incomeRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<IncomeResponseDTO> updateIncome(@PathVariable Long id,
			@RequestBody @Valid IncomeRequestDTO incomeRequestDTO) throws Exception {

		IncomeResponseDTO response = incomeService.updateIncome(id, incomeRequestDTO);
		return responderSucessoComItem(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<IncomeResponseDTO> findById(@PathVariable Long id) throws Exception {
		IncomeResponseDTO response = incomeService.findById(id);
		return responderSucessoComItem(response);
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<IncomeResponseDTO> response = incomeService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws Exception{
		incomeService.deleteById(id);
		return responderSucesso();
	}
}