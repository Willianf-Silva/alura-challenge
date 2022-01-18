package br.com.wnfa.alurachallenge.service;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfa.alurachallenge.dto.request.ExpenseRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.exception.ExpenseAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.mapper.ExpenseMapper;
import br.com.wnfa.alurachallenge.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	private final ExpenseMapper expenseMapper = ExpenseMapper.INSTANCE;

	@Override
	public ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception {
		verifyIfDuplicate(expenseRequestDTO);
		ExpenseDO expenseSaved = expenseRepository.save(expenseMapper.toModel(expenseRequestDTO));
		return expenseMapper.toResponseDTO(expenseSaved);
	}

	@Override
	public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception {
		ExpenseDO expenseDO = this.verifyIfExists(id);
		verifyIfDuplicate(expenseRequestDTO);
		BeanUtils.copyProperties(expenseRequestDTO, expenseDO, "id");
		return expenseMapper.toResponseDTO(expenseRepository.save(expenseDO));
	}

	@Override
	public ExpenseResponseDTO findById(Long id) throws Exception {
		ExpenseDO expenseDO = this.verifyIfExists(id);
		return expenseMapper.toResponseDTO(expenseDO);
	}

	@Override
	public Page<ExpenseResponseDTO> findAll(Pageable pageable) {
		Page<ExpenseDO> expenseDO = expenseRepository.findAll(pageable);

		List<ExpenseResponseDTO> response = expenseDO.stream().map(expenseMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, expenseDO.getTotalElements());
	}

	@Override
	public void deleteById(Long id) throws Exception {
		verifyIfExists(id);
		expenseRepository.deleteById(id);
	}

	private void verifyIfDuplicate(ExpenseRequestDTO expenseRequestDTO) throws ExpenseAlreadyRegisteredException {
		LocalDate firstDayOfMonth = expenseRequestDTO.getDate().with(firstDayOfMonth());
		LocalDate lastDayOfMonth = expenseRequestDTO.getDate().with(lastDayOfMonth());
		List<ExpenseDO> expenseList = expenseRepository.findByDateBetweenAndDescriptionIgnoreCase(firstDayOfMonth,
				lastDayOfMonth, expenseRequestDTO.getDescription());
		if (!expenseList.isEmpty()) {
			throw new ExpenseAlreadyRegisteredException();
		}
	}

	private ExpenseDO verifyIfExists(Long id) throws Exception {
		Optional<ExpenseDO> expenseOptional = expenseRepository.findById(id);
		if (expenseOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return expenseOptional.get();
	}

}