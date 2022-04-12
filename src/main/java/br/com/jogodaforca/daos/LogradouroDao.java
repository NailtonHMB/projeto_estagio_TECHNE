package br.com.jogodaforca.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jogodaforca.models.Logradouro;

public class LogradouroDao {

	@PersistenceContext
	private EntityManager manager;
		
	public List<Logradouro> buscarLogradouro(){
		String jpql = "select l from Logradouro l";
		return manager.createQuery(jpql, Logradouro.class)
				.getResultList();
		
		
	}

	public List<String> buscarCidades(String uf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> buscarRuas(String uf, String uf2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> buscarCep() {
		
		return null;
	}

	public Logradouro buscarPorId(Integer id) {
		
		String jpql = "select l from Logradouro l where l.id=:id";
		System.out.println(jpql);
		return manager.createQuery(jpql, Logradouro.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
