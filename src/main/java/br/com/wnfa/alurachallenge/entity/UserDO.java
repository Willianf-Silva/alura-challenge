package br.com.wnfa.alurachallenge.entity;

import java.time.LocalDate;
import java.util.Set;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
	
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
	@JoinTable(
		name = "user_role", // nome da tabela que realiza o relacionamento entre user e role
		joinColumns = @JoinColumn(name = "user_id"), // chave primaria da tabela atual
		inverseJoinColumns = @JoinColumn(name = "role_id") // chave primaria da outra tabrela
	)
    private Set<RoleDO> roles; // Foi utilizado Set para forçar que o usuário não tenha repetição nas roles
}
