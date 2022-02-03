package br.com.wnfa.alurachallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.RoleDO;

@Repository
public interface RoleRepository extends JpaRepository<RoleDO, Long> {
	RoleDO findByRoleName(String roleName);
}
