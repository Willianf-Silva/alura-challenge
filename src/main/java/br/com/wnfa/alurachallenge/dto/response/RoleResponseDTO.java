package br.com.wnfa.alurachallenge.dto.response;

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
public class RoleResponseDTO {
	
	@ApiModelProperty(notes = "Identificador único para a role", required = true, example = "01")
    private Long id;
	
	@ApiModelProperty(notes = "Nome da role", required = true, example = "ROLE_ADMIN")
	private String roleName;
}
