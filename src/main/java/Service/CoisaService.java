package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.CoisaDAO;
import Model.Coisa;
import Model.Tipo;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CoisaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CoisaDAO dao;
	
	public CoisaService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarDispositivo(Coisa coisa) {
		dao.CadastrarDispositivo(coisa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverDispositivo(Integer id) {
		boolean resultado = dao.RemoverDispositivoPeloID(id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarDispositivo(Coisa coisa) {
		dao.AtualizarDispositivo(coisa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCamposDeDispositivo(Integer id,Coisa coisa) {
		Coisa coisaDoBanco = dao.BuscarDispositivoPeloID(id);
		coisaDoBanco.AtualizaCampos(coisa);
		dao.AtualizarDispositivo(coisaDoBanco);
		dao.comitarCache();
	}
	
	public void AdicionarTipoAoDispositivo(Integer idCoisa, Tipo tipo) {
		Coisa coisaDoBanco = dao.BuscarDispositivoPeloID(idCoisa);
		coisaDoBanco.AdicionarTipoADispositivo(tipo);
		dao.AtualizarDispositivo(coisaDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverTipoDoDispositivo(Integer idCoisa, Integer idTipo) {
		Coisa coisaDoBanco = dao.BuscarDispositivoPeloID(idCoisa);
		coisaDoBanco.RemoverTipoDoDispositivo(idTipo);
		dao.AtualizarDispositivo(coisaDoBanco);
		dao.comitarCache();
	}
	
	public List<Coisa> ListarTodosDispositivos(){
		return dao.ListarTodosDispositivos();
	}
	
	public Coisa BuscarDispositivoPeloID(Integer id) {
		return dao.BuscarDispositivoPeloID(id);
	}
}
