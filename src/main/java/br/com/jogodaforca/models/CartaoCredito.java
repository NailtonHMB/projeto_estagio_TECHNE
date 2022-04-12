package br.com.jogodaforca.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CartaoCredito {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String nomeCartao;
	
	@NotBlank
	@Pattern(regexp = "\\d{4} \\d{4} \\d{4} \\d{4}", message = "Número de cartão inválido")
	@Length(max = 19)
	private String numeroCartao;
	
	@Future(message = "data de validade já passou")
	private Calendar dataValidade;
	
	@NotBlank
	@Length(max=3)
	@Pattern(regexp = "\\d{3}", message="Código de segurança inválido")
	private String codSeguranca;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Calendar getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	} 
}
