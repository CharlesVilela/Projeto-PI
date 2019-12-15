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
import javax.persistence.TypedQuery;

import Model.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "pi20192")
	private EntityManager manager;
	
	private DAO<Usuario> dao;
	
	public UsuarioDAO() {}
	
	
	public UsuarioDAO(EntityManager manager) {
		this.dao = new DAO<Usuario>(manager, Usuario.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Usuario>(manager, Usuario.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarUsuario(Usuario t) {
		System.out.println("Usuario dao");
		this.dao.Adicionar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario AtualizarUsuario(Usuario usuario) {
		return this.dao.Atualizar(usuario);
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverUsuario(Usuario usuario) {
		this.dao.Remover(usuario);	
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverPeloId(Integer id) {
		String hql = "DELETE FROM Usuario WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0)
			return true;
		else
			return false;
	}
	
	public Usuario loginUsuario(String senha, String email) {
		try {
			String hql = "select u from Usuario u where u.senha = :senha and u.email = :email";
			TypedQuery<Usuario> query = manager.createQuery(hql, Usuario.class);
			query.setParameter("senha", senha);
			query.setParameter("email", email);
			Usuario usuario = query.getSingleResult();
			if(usuario != null && usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Usuario buscarPeloId(Integer id) {
		return this.dao.buscaPorId(id);
	}
	
	
	public List<Usuario> ListaTodos() {
		return this.dao.listaTodos();
	}
	
	public String ExibirUsuario (){
		return this.dao.toString();
	}
	
	public void comitarCache() {
		this.dao.comitarCache();
	}

}
