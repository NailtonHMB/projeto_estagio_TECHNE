package br.com.jogodaforca.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.jogodaforca.daos.LogradouroDao;
import br.com.jogodaforca.daos.UsuarioDao;
import br.com.jogodaforca.models.CartaoCredito;
import br.com.jogodaforca.models.Logradouro;
import br.com.jogodaforca.models.SystemRole;
import br.com.jogodaforca.models.Usuario;

@Model
public class CadastroBean {

	@Inject 
	private UsuarioDao usuarioDao;	
	@Inject
	private LogradouroDao logradouroDao;
//-------------------------------------------------------	
	private Usuario usuario = new Usuario();
	private CartaoCredito cartao = new CartaoCredito();
//-------------------------------------------------------	
	private String senha;
	private String confSenha;
	
	@Transactional
	public String cadastrar() {
			usuario.getCartoes().add(getCartao());
			usuario.getSystemRole().add(new SystemRole("PLAYER"));
			usuarioDao.salvar(usuario);
			/**
			 * redireciona para login assim que termina cadastro com sucesso
			 */
			return "index?faces-redirect=true";
	}
	
	/*------------RETORNA OS LOGRADOUROS PARA EXPOR CEP NA TELA---------------*/
	
	public List<Logradouro> buscarLogradouro(){
		return logradouroDao.buscarLogradouro();
	}
		
	/*-------------------GETTERS E SETTERS-----------------------*/
	
	 public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
}
