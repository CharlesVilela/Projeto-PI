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

import Model.Grupo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class GrupoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Grupo> dao;
	
	public GrupoDAO() {}
	
	public GrupoDAO(EntityManager manager) {
		this.dao = new DAO<Grupo>(manager, Grupo.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Grupo>(manager, Grupo.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarGrupo(Grupo grupo) {
		dao.Adicionar(grupo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarGrupo (Grupo grupo) {
		dao.Atualizar(grupo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverGrupo (Grupo grupo) {
		dao.Remover(grupo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverGrupoPorID(Integer id) {
		String hql = "DELETE FROM Grupo WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public List<Grupo> ListaTodos() {
		return dao.listaTodos();
	}
	
	public Grupo BuscarGrupoPorID(Integer id) {
		return dao.buscaPorId(id);
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}
}
