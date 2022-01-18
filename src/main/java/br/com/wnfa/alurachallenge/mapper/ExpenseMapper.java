package br.com.wnfa.alurachallenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.entity.ExpenseDO;

@Mapper
public interface ExpenseMapper {

	ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(target = "id", ignore = true)
	ExpenseDO toModel(ExpenseRequestDTO expenseRequestDTO);

    ExpenseResponseDTO toResponseDTO(ExpenseDO expenseDO);
}