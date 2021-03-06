package br.com.wnfa.alurachallenge.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.IncomeDO;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeDO, Long>{
	public Page<IncomeDO> findByDateBetweenOrderByDateAsc(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth, Pageable pageable);
	
	public List<IncomeDO> findByDateBetweenAndDescriptionIgnoreCase(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth, String description);

	@Query("FROM IncomeDO i WHERE LOWER(i.description) LIKE %:description%")
	public Page<IncomeDO> findByDescription(String description, Pageable pageable);

	@Query("SELECT SUM(i.value) FROM IncomeDO i WHERE i.date BETWEEN :firstDayOfMonth AND :lastDayOfMonth")
	public Optional<BigDecimal> findSumValueMonth(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);
}
