package br.com.wnfa.alurachallenge.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.ExpenseDO;
import br.com.wnfa.alurachallenge.repository.filter.ExpenseFilter;

@Repository
public interface ExpenseRepositoryCustom {
	Page<ExpenseDO> FindExpenses(ExpenseFilter expenseFilter, Pageable pageable);
}
