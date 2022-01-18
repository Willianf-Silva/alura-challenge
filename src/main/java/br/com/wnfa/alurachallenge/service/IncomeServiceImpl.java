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

import br.com.wnfa.alurachallenge.dto.request.IncomeRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.IncomeResponseDTO;
import br.com.wnfa.alurachallenge.entity.IncomeDO;
import br.com.wnfa.alurachallenge.exception.IncomeAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.mapper.IncomeMapper;
import br.com.wnfa.alurachallenge.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService{

	@Autowired
	private IncomeRepository incomeRepository;
	
	private final IncomeMapper incomeMapper = IncomeMapper.INSTANCE;
	
	@Override
	public IncomeResponseDTO createNewIncome(IncomeRequestDTO incomeRequestDTO) throws Exception {
		verifyIfDuplicate(incomeRequestDTO);
		IncomeDO incomeSaved = incomeRepository.save(incomeMapper.toModel(incomeRequestDTO));
		return incomeMapper.toResponseDTO(incomeSaved);
	}

	@Override
	public IncomeResponseDTO updateIncome(Long id, IncomeRequestDTO incomeRequestDTO) throws Exception {
		IncomeDO incomeDO = this.verifyIfExists(id);
		verifyIfDuplicate(incomeRequestDTO);
		BeanUtils.copyProperties(incomeRequestDTO, incomeDO, "id");
		return incomeMapper.toResponseDTO(incomeRepository.save(incomeDO));
	}

	@Override
	public IncomeResponseDTO findById(Long id) throws Exception {
		IncomeDO incomeDO = this.verifyIfExists(id);
		return incomeMapper.toResponseDTO(incomeDO);
	}

	@Override
	public Page<IncomeResponseDTO> findAll(Pageable pageable) {
		Page<IncomeDO> incomeDO = incomeRepository.findAll(pageable);
		
		List<IncomeResponseDTO> response = incomeDO.stream()
				.map(incomeMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, incomeDO.getTotalElements());
	}

	private void verifyIfDuplicate(IncomeRequestDTO incomeRequestDTO) throws IncomeAlreadyRegisteredException {
		LocalDate firstDayOfMonth = incomeRequestDTO.getDate().with(firstDayOfMonth());
		LocalDate lastDayOfMonth = incomeRequestDTO.getDate().with(lastDayOfMonth());
		List<IncomeDO> incomeList = incomeRepository.findByDateBetweenAndDescriptionIgnoreCase(firstDayOfMonth, lastDayOfMonth, incomeRequestDTO.getDescription());
		if (!incomeList.isEmpty()) {
			throw new IncomeAlreadyRegisteredException();
		}
	}

	private IncomeDO verifyIfExists(Long id) throws Exception{
		Optional<IncomeDO> incomeOptional = incomeRepository.findById(id);
		if (incomeOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return incomeOptional.get();
	}

}