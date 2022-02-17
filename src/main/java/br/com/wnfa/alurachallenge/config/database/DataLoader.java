package br.com.wnfa.alurachallenge.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.wnfa.alurachallenge.entity.RoleDO;
import br.com.wnfa.alurachallenge.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

	private RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
    	incluirRoles();
    }

	private void incluirRoles() {
		if (roleRepository.findAll().size() == 0) {
			roleRepository.save(new RoleDO(1L, "ROLE_MASTER"));
			roleRepository.save(new RoleDO(2L, "ROLE_ADMIN"));
			roleRepository.save(new RoleDO(3L, "ROLE_OPERATOR"));	
			log.info("Roles incluídas com sucesso!");
		}
		log.info("Roles cadastradas");
	}
}