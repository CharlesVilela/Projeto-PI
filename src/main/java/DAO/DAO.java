package DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;


public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager manager, Class<T> classe) {
		this.em = manager;
		this.classe = classe;
	}

	public void Adicionar (T t) {
		this.em.persist(t);
	}
	
	public T Atualizar (T t) {
		return this.em.merge(t);
	}
	
	public void Remover (T t) {
		
		em.remove(em.merge(t));
		this.em.remove(this.em.merge(t));
	}

	@Override
	public String toString() {
		return "DAO [classe=" + classe + "]";
	}
	
	public List<T> listaTodos() {
		CriteriaQuery<T> query = this.em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = this.em.createQuery(query).getResultList();

		return lista;
	}
	
	public T buscaPorId(Integer id) {
		T instancia = this.em.find(classe, id);
		return instancia;
	}
	
	public List<T> listaTodosPaginada(int firstResult, int maxResults){
		CriteriaQuery<T> query = this.em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = this.em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		
		return lista;
	}
	
	public T buscarPeloCPF(String cpf) {
		T instancia = this.em.find(classe, cpf);
		return instancia;
	}
	
	
	public void comitarCache() {
		em.flush();
	}
	
	
	
}
