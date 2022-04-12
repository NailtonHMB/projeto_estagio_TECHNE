package br.com.jogodaforca.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Length(min=3, message="Nome de usuário inválido")
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	private String email;
	@Pattern(regexp = "\\d{3}.\\d{3}.\\d{3}-\\d{2}", message="cpf inválido")
	@NotBlank
	private String cpf;
	@NotBlank
	private String senha;
	@NotNull
	@ManyToOne
	private Logradouro logradouro;

	private Double pontuacao=0D;
	
	/**
	 *@OneToMany(cascade = CascadeType.PERSIST) >> necessário para poder salvar
	 *o cartão junto com o usuário ao mesmo tempo 
	 */
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<CartaoCredito> cartoes = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<SystemRole> systemRole = new ArrayList<>();
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Logradouro getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<SystemRole> getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(List<SystemRole> systemRole) {
		this.systemRole = systemRole;
	}
	public Double getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}
}
