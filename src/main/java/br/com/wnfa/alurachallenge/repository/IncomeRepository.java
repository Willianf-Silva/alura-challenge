package br.com.wnfa.alurachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfa.alurachallenge.entity.IncomeDO;

public interface IncomeRepository extends JpaRepository<IncomeDO, Long>{

}
