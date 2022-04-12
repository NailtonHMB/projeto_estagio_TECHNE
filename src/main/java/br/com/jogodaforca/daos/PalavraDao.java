package br.com.jogodaforca.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jogodaforca.models.Palavra;

public class PalavraDao implements Serializable{

	private static final long serialVersionUID = 3291573663851330707L;
	
	@PersistenceContext
	private EntityManager manager;

	public Integer quantidadeDePalavras() {
		String jpql = "select count(p) from Palavra p";
		Long singleResult = (Long)manager.createQuery(jpql).getSingleResult();
		return Math.toIntExact(singleResult);

	}

	public Palavra buscarPorId(Integer palavraEscolhida) {
		String jpql = "select p from Palavra p where p.id=:id";
		
		return manager.createQuery(jpql, Palavra.class)
				.setParameter("id", palavraEscolhida)
				.getSingleResult();
	}
	
	
}
