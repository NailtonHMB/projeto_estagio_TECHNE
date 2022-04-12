package br.com.jogodaforca.beans;

import javax.enterprise.inject.Model;

@Model
public class LoginBean {

	public void entrar() {
		
	}
	
	public String paginaCadastrar() {
		System.out.println("entrou na função paginaCadastrar");
		return "cadastro?faces-redirect=true";
	}
}
