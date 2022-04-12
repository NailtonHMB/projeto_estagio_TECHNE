package br.com.jogodaforca.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Logradouro {

	/**
	 * INSERT INTO logradouro (cep, uf, cidade, rua) VALUES('10000-000', 'CE', 'Fortaleza', 'rua A') VALUES('20000-000', 'SP','São Paulo', 'rua B') VALUES('30000-000', 'RJ','Rio de Janeiro', 'rua C');
	 */
	  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String uf;
	@NotBlank
	private String cidade;
	@NotBlank
	private String rua;
	
	private String cep;
	
	public Logradouro() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	
	/**
	 * Equals and hash code são necessários para utilizar os converters autorConverter corretamente
	 * permite o converter fazer a comprarção dos autores que chegam dos autores que estão no BD
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logradouro other = (Logradouro) obj;
		return Objects.equals(id, other.id);
	}
}
