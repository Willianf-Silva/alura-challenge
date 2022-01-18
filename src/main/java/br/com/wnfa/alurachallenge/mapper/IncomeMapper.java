package br.com.wnfa.alurachallenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import br.com.wnfa.alurachallenge.entity.IncomeDO;
import org.mapstruct.Mapping;

@Mapper
public interface IncomeMapper {

	IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

    @Mapping(target = "id", ignore = true)
	IncomeDO toModel(IncomeRequestDTO incomeRequestDTO);

    IncomeResponseDTO toResponseDTO(IncomeDO incomeDO);
}