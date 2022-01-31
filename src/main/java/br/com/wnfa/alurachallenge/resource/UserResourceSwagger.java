package br.com.wnfa.alurachallenge.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Usuários")
public interface UserResourceSwagger {
	@ApiOperation("Incluir um novo usuário.")
	public ResponseEntity<UserResponseDTO> createUser(
			@ApiParam("Dados do usuário que será incluído") UserRequestDTO userRequestDTO, 
			HttpServletResponse resp);
	
	@ApiOperation("Atualiza um usuário existente")
	public ResponseEntity<UserResponseDTO> updateUser(
			@ApiParam(value = "Identificador único do usuário", example = "01") @PathVariable Long id,
			@ApiParam("Novos dados do usuário que será atualizado.") @RequestBody @Valid UserRequestDTO userRequestDTO) throws Exception;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiOperation("Lista todos os usuários cadastrados no banco de dados")
	public ResponseEntity<?> findAll(@ApiIgnore Pageable pageable);
}
