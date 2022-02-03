package br.com.wnfa.alurachallenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfa.alurachallenge.dto.request.RoleRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.RoleResponseDTO;
import br.com.wnfa.alurachallenge.entity.RoleDO;
import org.mapstruct.Mapping;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
	
	@Mapping(target = "id", ignore = true)
	RoleDO toModel(RoleRequestDTO roleRequestDTO);
	
	RoleResponseDTO toResponseDTO(RoleDO roleDO);
}