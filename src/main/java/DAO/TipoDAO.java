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

import Model.Tipo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TipoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Tipo> dao;
	
	
	public TipoDAO() {}
	
	public TipoDAO(EntityManager manager) {
		this.dao = new DAO<Tipo>(manager, Tipo.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Tipo>(manager, Tipo.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarTipo(Tipo tipo) {
		dao.Adicionar(tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarTipo (Tipo tipo) {
		dao.Atualizar(tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverTipo(Tipo tipo) {
		dao.Remover(tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverTipoPeloID(Integer id) {
		String hql = "DELETE FROM Tipo WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public List<Tipo> ListarTodos() {
		return dao.listaTodos();
	}
	
	public Tipo BuscarTipoPeloID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}

}
