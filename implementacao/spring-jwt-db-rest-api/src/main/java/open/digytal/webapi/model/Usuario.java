package open.digytal.webapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@Column(length=15)
    private String login;
	@Column(length=100)
    private String senha;
	@Column(length=50)
    private String nome;
	@Column(length=70)
    private String email;
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "login",nullable=false), inverseJoinColumns = @JoinColumn(name = "nome",nullable=false))
	private Set<Role> roles = new HashSet<>();
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void addRole(Role role) {
		this.roles.add(role);
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
