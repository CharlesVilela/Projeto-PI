package DAO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Model.Canal;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CanalDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Canal> dao;
	
	public CanalDAO() {}
	
	public CanalDAO(EntityManager manager) {
		this.dao = new DAO<Canal>(manager, Canal.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Canal>(manager, Canal.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarCanal (Canal canal) {
		dao.Adicionar(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCanal(Canal canal) {
		dao.Atualizar(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverCanal (Canal canal) {
		dao.Remover(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverCanalPorID(Integer id) {
		String hql = "DELETE FROM Canal WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public Canal BuscarCanalPorID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public List<Canal> ListaTodos() {
		return dao.listaTodos();
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}
	
}
