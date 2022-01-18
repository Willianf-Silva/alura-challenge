package br.com.wnfa.alurachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfa.alurachallenge.entity.ExpenseDO;

public interface ExpenseRepository extends JpaRepository<ExpenseDO, Long>{

}
