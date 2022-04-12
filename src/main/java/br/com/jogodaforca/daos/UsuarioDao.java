package br.com.jogodaforca.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jogodaforca.models.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}


	public List<Usuario> buscarTresPrimeiros() {
		String jpql = "select u from Usuario u order by u.pontuacao desc";
		return manager.createQuery(jpql, Usuario.class)
				.setMaxResults(3)
				.getResultList();
	}


	public void alterarPontuacao(Double pontuacao, String email) {
		String jpql = "select u from Usuario u where u.email=:email";
		Usuario usuario = manager.createQuery(jpql, Usuario.class)
		.setParameter("email", email)
		.getSingleResult();
		usuario.setPontuacao(pontuacao);
		System.out.println("Localizou o Usuario: "+usuario.getCpf());
		manager.merge(usuario);
		manager.flush();
		System.out.println("atualizou a pontuação do usuário");
	}
}
