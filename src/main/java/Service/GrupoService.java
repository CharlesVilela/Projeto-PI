package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.GrupoDAO;
import Exception.ValidacaoException;
import Model.Coisa;
import Model.Grupo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class GrupoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoDAO dao;
	
	public GrupoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarGrupo(Grupo grupo) throws ValidacaoException {
		dao.AdicionarGrupo(grupo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarGrupo(Grupo grupo) {
		dao.AtualizarGrupo(grupo);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverGrupo(Grupo grupo) {
		dao.RemoverGrupo(grupo);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverGrupoPorID(Integer id) {
		boolean resultado = dao.RemoverGrupoPorID(id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarGrupo(Integer id, Grupo grupo) {
		Grupo grupoDoBanco = dao.BuscarGrupoPorID(id);
		grupoDoBanco.atualizarCampos(grupo);
		dao.AtualizarGrupo(grupoDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarDispositivoAGrupo(Integer IdGrupo, Coisa coisa) {
		Grupo grupoDoBanco = dao.BuscarGrupoPorID(IdGrupo);
		grupoDoBanco.AdicionarDispositivoAoGrupo(coisa);
		dao.AtualizarGrupo(grupoDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverDispositivoDoGrupo(Integer IdGrupo, Integer IdCoisa) {
		Grupo grupoDoBanco = dao.BuscarGrupoPorID(IdGrupo);
		grupoDoBanco.RemoverDispositivoDoGrupo(IdCoisa);
		dao.AtualizarGrupo(grupoDoBanco);
		dao.comitarCache();
	}
	
	public List<Grupo> ListarTodosGrupos(){
		return dao.ListaTodos();
	}
	
	public Grupo BuscarGrupoPorID(Integer id) {
		return dao.BuscarGrupoPorID(id);
	}
	
	
	private void ValidarGrupo(Grupo grupo) throws ValidacaoException {
		
		
		if(grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
			throw new ValidacaoException("NOME do grupo n�o pode ser vazio");
		}
	}
	
}
