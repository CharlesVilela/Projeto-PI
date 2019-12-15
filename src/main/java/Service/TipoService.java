package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.TipoDAO;
import Model.Tipo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TipoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoDAO dao;
	
	public TipoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarTipo(Tipo tipo) {
		dao.CadastrarTipo(tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverTipo(Integer id) {
		boolean resultado = dao.RemoverTipoPeloID(id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarTipo(Tipo tipo) {
		dao.AtualizarTipo(tipo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCamposDeTipo(Integer id, Tipo tipo) {
		Tipo tipoDoBanco = dao.BuscarTipoPeloID(id);
		tipoDoBanco.AtualizarCamposDeTipo(tipo);
		dao.AtualizarTipo(tipoDoBanco);
		dao.comitarCache();
	}
	
	public List<Tipo> ListarTodosTipo(){
		return dao.ListarTodos();
	}
	
	public Tipo BuscarTipoPeloID(Integer id) {
		return dao.BuscarTipoPeloID(id);
	}

}
