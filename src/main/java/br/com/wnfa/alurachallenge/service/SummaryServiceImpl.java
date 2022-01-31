package br.com.wnfa.alurachallenge.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfa.alurachallenge.dto.response.ExpenseCategoryDTO;
import br.com.wnfa.alurachallenge.dto.response.SummaryResponseDTO;

@Service
public class SummaryServiceImpl implements SummaryService {
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Override
	public SummaryResponseDTO findByYearAndMonth(Integer year, Integer month) {
		BigDecimal summaryIncomeMonth = incomeService.summaryByMonth(year, month).orElse(BigDecimal.ZERO);
		BigDecimal summaryExpenseMonth = expenseService.summaryByMonth(year, month).orElse(BigDecimal.ZERO);
		List<ExpenseCategoryDTO> expenseCategories = expenseService.summaryByCategory(year, month);
		
		return SummaryResponseDTO.builder()
				.totalIncome(summaryIncomeMonth)
				.totalExpense(summaryExpenseMonth)
				.finalBalance(summaryIncomeMonth.subtract(summaryExpenseMonth))
				.expenseCategory(expenseCategories)
				.build();
	}

}
