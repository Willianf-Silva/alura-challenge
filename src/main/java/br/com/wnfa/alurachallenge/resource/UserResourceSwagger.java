package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuários")
public interface UserResourceSwagger {
	@ApiOperation("Incluir um novo usuário.")
	public ResponseEntity<UserResponseDTO> createUser(
			@ApiParam("Dados do usuário que será incluído") UserRequestDTO userRequestDTO, 
			HttpServletResponse resp);
}
