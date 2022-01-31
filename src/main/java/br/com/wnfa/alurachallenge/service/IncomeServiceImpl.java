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

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import br.com.wnfa.alurachallenge.entity.IncomeDO;
import br.com.wnfa.alurachallenge.exception.IncomeAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.mapper.IncomeMapper;
import br.com.wnfa.alurachallenge.repository.IncomeRepository;
import br.com.wnfa.alurachallenge.repository.filter.IncomeFilter;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	private final IncomeMapper incomeMapper = IncomeMapper.INSTANCE;

	@Override
	public IncomeResponseDTO createNewIncome(IncomeRequestDTO incomeRequestDTO) throws Exception {
		verifyIfDuplicate(null, incomeRequestDTO);
		IncomeDO incomeSaved = incomeRepository.save(incomeMapper.toModel(incomeRequestDTO));
		return incomeMapper.toResponseDTO(incomeSaved);
	}

	@Override
	public IncomeResponseDTO updateIncome(Long id, IncomeRequestDTO incomeRequestDTO) throws Exception {
		IncomeDO incomeDO = this.verifyIfExists(id);
		verifyIfDuplicate(id, incomeRequestDTO);
		BeanUtils.copyProperties(incomeRequestDTO, incomeDO, "id");
		return incomeMapper.toResponseDTO(incomeRepository.save(incomeDO));
	}

	@Override
	public IncomeResponseDTO findById(Long id) throws Exception {
		IncomeDO incomeDO = this.verifyIfExists(id);
		return incomeMapper.toResponseDTO(incomeDO);
	}

	@Override
	public Page<IncomeResponseDTO> findAll(IncomeFilter incomeFilter, Pageable pageable) {

		String description = incomeFilter.getDescription() != null ? incomeFilter.getDescription() : "";

		Page<IncomeDO> incomes = incomeRepository.findByDescription(description.toLowerCase(), pageable);

		List<IncomeResponseDTO> response = incomes.stream().map(incomeMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, incomes.getTotalElements());
	}

	@Override
	public Page<IncomeResponseDTO> findByYearAndMonth(Integer year, Integer month, Pageable pageable) {

		Page<IncomeDO> incomes = incomeRepository.findByDateBetweenOrderByDateAsc(
				getFirstDayOfMonth(year, month), 
				getLastDayOfMonth(year, month),
				pageable);

		List<IncomeResponseDTO> response = incomes.stream().map(incomeMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, incomes.getTotalElements());
	}

	@Override
	public Optional<BigDecimal> summaryByMonth(Integer year, Integer month) {
		return incomeRepository.findSumValueMonth(
				getFirstDayOfMonth(year, month), 
				getLastDayOfMonth(year, month)
				);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		verifyIfExists(id);
		incomeRepository.deleteById(id);
	}

	private void verifyIfDuplicate(Long id, IncomeRequestDTO incomeRequestDTO) throws IncomeAlreadyRegisteredException {
		List<IncomeDO> incomeList = incomeRepository.findByDateBetweenAndDescriptionIgnoreCase(
				getFirstDayOfMonth(incomeRequestDTO.getDate().getYear(), incomeRequestDTO.getDate().getMonthValue()),
				getLastDayOfMonth(incomeRequestDTO.getDate().getYear(), incomeRequestDTO.getDate().getMonthValue()), 
				incomeRequestDTO.getDescription()
				);

		// TODO Melhorar a lógica da validação estudando boas praticas de programação

		if (incomeList.size() == 1) {
			if (id == null || incomeList.get(0).getId() != id) {
				throw new IncomeAlreadyRegisteredException();
			}
		}
		if (incomeList.size() > 1) {
			throw new IncomeAlreadyRegisteredException();
		}
	}

	private IncomeDO verifyIfExists(Long id) throws Exception {
		Optional<IncomeDO> incomeOptional = incomeRepository.findById(id);
		if (incomeOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return incomeOptional.get();
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