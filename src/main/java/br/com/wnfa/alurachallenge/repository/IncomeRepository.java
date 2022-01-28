package br.com.wnfa.alurachallenge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.IncomeDO;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeDO, Long>{
	public List<IncomeDO> findByDateBetweenAndDescriptionIgnoreCase(LocalDate dateStart, LocalDate dateEnd, String description);

	@Query("FROM IncomeDO i WHERE LOWER(i.description) LIKE %:description%")
	public Page<IncomeDO> findByDescription(String description, Pageable pageable);
}
