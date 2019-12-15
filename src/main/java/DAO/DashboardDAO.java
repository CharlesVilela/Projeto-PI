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

import Model.Dashboard;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DashboardDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Dashboard> dao;
	
	public DashboardDAO() {}
	
	public DashboardDAO(EntityManager manager) {
		this.dao = new DAO<Dashboard>(manager, Dashboard.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Dashboard>(manager, Dashboard.class);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarDashboard(Dashboard dashboard) {
		dao.Adicionar(dashboard);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarDashboard(Dashboard dashboard) {
		dao.Atualizar(dashboard);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverDashboard( Dashboard dashboard) {
		dao.Remover(dashboard);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverPorID(Integer id) {
		String hql = "DELETE FROM Dashboard WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public Dashboard BuscarDashboardPeloID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public List<Dashboard> ListarTodosDashboard(){
		return dao.listaTodos();
	}
	
	public void ComitarCache() {
		dao.comitarCache();
	}

}
