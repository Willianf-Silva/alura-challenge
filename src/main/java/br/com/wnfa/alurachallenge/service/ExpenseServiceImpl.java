package br.com.wnfa.alurachallenge.service;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.exception.ExpenseAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.mapper.ExpenseMapper;
import br.com.wnfa.alurachallenge.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	private final ExpenseMapper incomeMapper = ExpenseMapper.INSTANCE;
	
	@Override
	public ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception {
		verifyIfDuplicate(expenseRequestDTO);
		ExpenseDO incomeSaved = expenseRepository.save(incomeMapper.toModel(expenseRequestDTO));
		return incomeMapper.toResponseDTO(incomeSaved);
	}

	@Override
	public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception {
		ExpenseDO incomeDO = this.verifyIfExists(id);
		verifyIfDuplicate(expenseRequestDTO);
		BeanUtils.copyProperties(expenseRequestDTO, incomeDO, "id");
		return incomeMapper.toResponseDTO(expenseRepository.save(incomeDO));
	}
	
	@Override
	public ExpenseResponseDTO findById(Long id) throws Exception {
		ExpenseDO incomeDO = this.verifyIfExists(id);
		return incomeMapper.toResponseDTO(incomeDO);
	}

	private void verifyIfDuplicate(ExpenseRequestDTO expenseRequestDTO) throws ExpenseAlreadyRegisteredException {
		LocalDate firstDayOfMonth = expenseRequestDTO.getDate().with(firstDayOfMonth());
		LocalDate lastDayOfMonth = expenseRequestDTO.getDate().with(lastDayOfMonth());
		List<ExpenseDO> incomeList = expenseRepository.findByDateBetweenAndDescriptionIgnoreCase(firstDayOfMonth, lastDayOfMonth, expenseRequestDTO.getDescription());
		if (!incomeList.isEmpty()) {
			throw new ExpenseAlreadyRegisteredException();
		}
	}
	
	private ExpenseDO verifyIfExists(Long id) throws Exception{
	Optional<ExpenseDO> incomeOptional = expenseRepository.findById(id);
	if (incomeOptional.isEmpty()) {
		throw new ResourceNotFoundException();
	}
	return incomeOptional.get();
}

}