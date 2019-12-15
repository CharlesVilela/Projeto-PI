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

import Model.Coisa;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CoisaDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName= "pi20192")
	private EntityManager manager;
	
	private DAO<Coisa> dao;
	
	public CoisaDAO() {}
	
	public CoisaDAO(EntityManager manager) {
		this.dao = new DAO<Coisa>(manager, Coisa.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Coisa>(manager, Coisa.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarDispositivo(Coisa coisa) {
		dao.Adicionar(coisa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverDispositivo(Coisa coisa) {
		dao.Remover(coisa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverDispositivoPeloID(Integer id) {
		String hql = "DELETE FROM Coisa WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	public void AtualizarDispositivo(Coisa coisa) {
		dao.Atualizar(coisa);
	}
	
	public List<Coisa> ListarTodosDispositivos(){
		return dao.listaTodos();
	}
	
	public Coisa BuscarDispositivoPeloID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}
	
}
