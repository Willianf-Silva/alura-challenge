package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import br.com.wnfa.alurachallenge.event.ResourceCreatedEvent;
import br.com.wnfa.alurachallenge.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource extends ResourceBase<UserResponseDTO> implements UserResourceSwagger {
	@Autowired
	private UserService userService;
	
	@Autowired
    private ApplicationEventPublisher publicarEvento;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO, HttpServletResponse resp){
		UserResponseDTO user = userService.createUser(userRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, user.getId()));
		return responderItemCriado(user);
	}

}