package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.UsuarioDAO;
import Exception.ValidacaoException;
import Model.Usuario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO dao;
	
	public UsuarioService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarUsuario(Usuario usuario) throws ValidacaoException {
		this.ValidarUsuario(usuario);
		dao.CadastrarUsuario(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarUsuario(Usuario usuario) {
		dao.AtualizarUsuario(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarCampoDeUsuario(Integer id, Usuario usuario) {
		Usuario usuarioDoBanco = dao.buscarPeloId(id);
		usuarioDoBanco.AtualizarCampos(usuario);
		dao.AtualizarUsuario(usuarioDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerUsuario(Integer id) {
		System.out.println("Chegou no service");
		boolean resultado = dao.RemoverPeloId(id);
		dao.comitarCache();
		return resultado;
	}
	
	public Usuario loginUsuarrio(String senha, String email) {
		return dao.loginUsuario(senha, email);
	}
	
	public List<Usuario> listarUsuario(){
		return dao.ListaTodos();
	}
	
	public Usuario getUsuarioPeloId(Integer id) {
		return dao.buscarPeloId(id);
	}
	
	private void ValidarUsuario(Usuario usuario) throws ValidacaoException {
		if(usuario.getNomeUsuario() == null || usuario.getNomeUsuario().trim().isEmpty()) {
			throw new ValidacaoException("NOME do usu�rio � obrigat�rio e n�o pode ser vazio");
		}
		if(usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			throw new ValidacaoException("SENHA do usu�rio � obrigat�ria e n�o pode ser vazia");
		}
		if(usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			throw new ValidacaoException("E-MAIL do usuário é obrigatório e não pode ser vazio");
		}
	}
	
}
