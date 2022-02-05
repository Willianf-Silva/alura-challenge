package br.com.wnfa.alurachallenge.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.wnfa.alurachallenge.entity.UserDO;
import br.com.wnfa.alurachallenge.repository.UserRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDO> usuarioEntity = userRepository.findByUsername(username);
		if (usuarioEntity.isEmpty())
			throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
		UserDO userDO = usuarioEntity.get(0);
		return new User(username, userDO.getPassword(), getRoles(userDO));
	}

	private Collection<? extends GrantedAuthority> getRoles(UserDO userDO) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userDO.getRoles()
				.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase())));
		return authorities;
	}
}
