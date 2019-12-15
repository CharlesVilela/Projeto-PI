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

import Model.Topico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TopicoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Topico> dao;
	
	public TopicoDAO() {}
	
	
	public TopicoDAO(EntityManager manager) {
		this.dao = new DAO<Topico>(manager, Topico.class);
	}
	
	@PostConstruct
	private void initDAO() {		
		this.dao = new DAO<Topico>(manager, Topico.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarTopico(Topico topico) {
		dao.Adicionar(topico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarTopico(Topico topico) {
		dao.Atualizar(topico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverTopico(Topico topico) {
		dao.Remover(topico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverPorID(Integer id) {
		String hql = "DELETE FROM Topico WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public Topico BuscarTopicoID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public List<Topico> ListarTodosTopicos() {
		return  dao.listaTodos();
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}

}
