package br.com.jsf.tutorial.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jsf.tutorial.util.JPAUtil;

public class Dao<T> {

	private EntityManager manager = JPAUtil.getEntityManager();
	private final Class<T> classe;
	
	public Dao(Class<T> classe) {
		this.classe = classe;
	}
	
	public void adiciona(T t) {
		manager.getTransaction().begin();  
		manager.persist(t);
		manager.getTransaction().commit();
	}

	public void remove(T t) {
		manager.getTransaction().begin();  
		manager.remove(manager.merge(t));
		manager.getTransaction().commit();
	}

	public void atualiza(T t) {
		manager.getTransaction().begin();  
		manager.merge(t);
		manager.getTransaction().commit();
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Long id) {
		T instancia = manager.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) manager.createQuery(
				"select count(n) from " + classe.getName() + " n")
				.getSingleResult();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = manager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		return lista;
	}
}
