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

import Model.Browker;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BrowkerDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Browker> dao;
	
	public BrowkerDAO() {}
	
	public BrowkerDAO(EntityManager manager) {
		this.dao = new DAO<Browker>(manager, Browker.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Browker>(manager, Browker.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarBrowker(Browker browker) {
		dao.Adicionar(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarBrowker(Browker browker) {
		dao.Atualizar(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverBrowker(Browker browker) {
		dao.Remover(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverBrowkerPorID(Integer id) {
		String hql = "DELETE FROM Browker WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public List<Browker> ListaTodosBrowkers() {
		return dao.listaTodos();
	}
	
	public Browker BuscarPorID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public void ExibirBrowker() {
		dao.toString();
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}
}
