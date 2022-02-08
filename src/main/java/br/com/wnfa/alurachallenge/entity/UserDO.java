package br.com.wnfa.alurachallenge.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDO implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String email;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	private String password;

	@NotNull
	private Boolean active;

	@NotNull
	private LocalDate dateCreate;

	private LocalDate dateUpdate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_role", // nome da tabela que realiza o relacionamento entre user e role
			joinColumns = @JoinColumn(name = "tb_user_id"), // chave primaria da tabela atual
			inverseJoinColumns = @JoinColumn(name = "tb_role_id") // chave primaria da outra tabrela
	)
	private Set<RoleDO> roles; // Foi utilizado Set para forçar que o usuário não tenha repetição nas roles

	@Override
    public String getUsername() {
        return this.username;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(x -> new SimpleGrantedAuthority(x.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
