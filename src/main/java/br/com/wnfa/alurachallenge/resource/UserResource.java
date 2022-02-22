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

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import br.com.wnfa.alurachallenge.event.ResourceCreatedEvent;
import br.com.wnfa.alurachallenge.resource.swagger.UserResourceSwagger;
import br.com.wnfa.alurachallenge.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource extends ResourceBase<UserResponseDTO> implements UserResourceSwagger {
	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO,
			HttpServletResponse resp) throws Exception {
		UserResponseDTO user = userService.createUser(userRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, user.getId()));
		return responderItemCriado(user);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
			@RequestBody @Valid UserRequestDTO userRequestDTO) throws Exception {

		UserResponseDTO response = userService.updateUser(id, userRequestDTO);
		return responderSucessoComItem(response);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<UserResponseDTO> response = userService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) throws Exception {
		UserResponseDTO response = userService.findById(id);
		return responderSucessoComItem(response);
	}

	@GetMapping("/login/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) throws Exception {
		UserResponseDTO response = userService.findByUsername(username);
		return responderSucessoComItem(response);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws Exception{
		userService.deleteById(id);
		return responderSucesso();
	}
}
