package br.com.wnfa.alurachallenge.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO {
	@ApiModelProperty(notes = "Identificador único do usuário", example = "999999")
    private Long id;

    @ApiModelProperty(notes = "Nome do usuário", required = true, example = "Willian")
    private String name;

    @ApiModelProperty(notes = "Email do usuário", required = true, example = "willian@willian.com")
    private String email;

    @ApiModelProperty(notes = "Usuário de acesso", required = true, example = "will123")
    private String username;

    @ApiModelProperty(notes = "Usuário está ativo na plataforma", required = true)
    private Boolean active;
    
    Set<RoleResponseDTO> roles;
}
