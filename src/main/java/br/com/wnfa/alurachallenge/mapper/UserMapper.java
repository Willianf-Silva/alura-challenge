package br.com.wnfa.alurachallenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import br.com.wnfa.alurachallenge.entity.UserDO;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target = "active", ignore = true)
	@Mapping(target = "dateCreate", ignore = true)
	@Mapping(target = "dateUpdate", ignore = true)
	@Mapping(target = "id", ignore = true)
	UserDO toModel(UserRequestDTO userRequestDTO);

    UserResponseDTO toResponseDTO(UserDO userDO);
}