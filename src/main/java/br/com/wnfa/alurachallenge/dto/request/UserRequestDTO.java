package br.com.wnfa.alurachallenge.dto.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
	
	@ApiModelProperty(notes = "Nome do usuário", required = true, example = "Willian")
	@NotBlank
    private String name;

	@ApiModelProperty(notes = "E-mail do usuário", required = true, example = "willian@willian.com")
	@NotBlank
    private String email;

	@ApiModelProperty(notes = "Usuário de acesso", required = true, example = "will123")
	@NotBlank
    private String username;

	@ApiModelProperty(notes = "Senha de acesso", required = true, example = "123will")
	@NotBlank
    private String password;
	
	private Set<RoleRequestDTO> roles;
}
