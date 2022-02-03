package br.com.wnfa.alurachallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.UserDO;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Long>{
	List<UserDO> findByUser(String user);
}
