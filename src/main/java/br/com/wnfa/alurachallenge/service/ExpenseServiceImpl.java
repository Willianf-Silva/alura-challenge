package br.com.wnfa.alurachallenge.service;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.math.BigDecimal;
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
import br.com.wnfa.alurachallenge.dto.response.ExpenseCategoryDTO;
import br.com.wnfa.alurachallenge.dto.response.ExpenseResponseDTO;
import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.enums.Category;
import br.com.wnfa.alurachallenge.exception.ExpenseAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.mapper.ExpenseMapper;
import br.com.wnfa.alurachallenge.repository.ExpenseRepository;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	private final ExpenseMapper expenseMapper = ExpenseMapper.INSTANCE;

	@Override
	public ExpenseResponseDTO createNewExpense(ExpenseRequestDTO expenseRequestDTO) throws Exception {
		verifyIfDuplicate(null, expenseRequestDTO);
		if (expenseRequestDTO.getCategory() == null) {
			expenseRequestDTO.setCategory(Category.OUTRAS);
		}
		ExpenseDO expenseSaved = expenseRepository.save(expenseMapper.toModel(expenseRequestDTO));
		return expenseMapper.toResponseDTO(expenseSaved);
	}

	@Override
	public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseRequestDTO) throws Exception {
		ExpenseDO expenseDO = this.verifyIfExists(id);
		verifyIfDuplicate(id, expenseRequestDTO);
		BeanUtils.copyProperties(expenseRequestDTO, expenseDO, "id");
		return expenseMapper.toResponseDTO(expenseRepository.save(expenseDO));
	}

	@Override
	public ExpenseResponseDTO findById(Long id) throws Exception {
		ExpenseDO expenseDO = this.verifyIfExists(id);
		return expenseMapper.toResponseDTO(expenseDO);
	}

	@Override
	public Page<ExpenseResponseDTO> findAll(ExpenseFilter expenseFilter, Pageable pageable) {
		Page<ExpenseDO> expenseDO = expenseRepository.FindExpenses(expenseFilter, pageable);

		List<ExpenseResponseDTO> response = expenseDO.stream().map(expenseMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, expenseDO.getTotalElements());
	}

	@Override
	public Page<ExpenseResponseDTO> findByYearAndMonth(Integer year, Integer month, Pageable pageable) {

		Page<ExpenseDO> expenses = expenseRepository.findByDateBetweenOrderByDateAsc(
				getFirstDayOfMonth(year, month), 
				getLastDayOfMonth(year, month), 
				pageable
				);

		List<ExpenseResponseDTO> response = expenses.stream()
				.map(expenseMapper::toResponseDTO)
				.collect(Collectors.toList());
		
		return new PageImpl<>(response, pageable, expenses.getTotalElements());
	}

	@Override
	public Optional<BigDecimal> summaryByMonth(Integer year, Integer month) {
		return expenseRepository.findSumValueMonth(
				getFirstDayOfMonth(year, month), 
				getLastDayOfMonth(year, month)
				);
	}

	@Override
	public List<ExpenseCategoryDTO> summaryByCategory(Integer year, Integer month) {
		return expenseRepository.summaryCategoryByMonth(
				getFirstDayOfMonth(year, month), 
				getLastDayOfMonth(year, month)
				);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		verifyIfExists(id);
		expenseRepository.deleteById(id);
	}

	private void verifyIfDuplicate(Long id, ExpenseRequestDTO expenseRequestDTO)
			throws ExpenseAlreadyRegisteredException {
		List<ExpenseDO> expenseList = expenseRepository.findByDateBetweenAndDescriptionIgnoreCase(
				getFirstDayOfMonth(expenseRequestDTO.getDate().getYear(), expenseRequestDTO.getDate().getMonthValue()),
				getLastDayOfMonth(expenseRequestDTO.getDate().getYear(), expenseRequestDTO.getDate().getMonthValue()), 
				expenseRequestDTO.getDescription()
				);

		// TODO Melhorar a lógica da validação estudando boas praticas de programação

		if (expenseList.size() == 1) {
			if (id == null || expenseList.get(0).getId() != id) {
				throw new ExpenseAlreadyRegisteredException();
			}
		}
		if (expenseList.size() > 1) {
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

	private LocalDate getLastDayOfMonth(Integer year, Integer month) {
		LocalDate dateFilter = LocalDate.of(year, month, 1);
		return dateFilter.with(lastDayOfMonth());
	}

	private LocalDate getFirstDayOfMonth(Integer year, Integer month) {
		LocalDate dateFilter = LocalDate.of(year, month, 1);
		return dateFilter.with(firstDayOfMonth());
	}

}