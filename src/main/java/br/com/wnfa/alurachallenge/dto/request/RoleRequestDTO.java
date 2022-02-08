package br.com.wnfa.alurachallenge.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {
	
	@ApiModelProperty(notes = "Nome da role", required = true, example = "ROLE_ADMIN")
	private String roleName;
}
