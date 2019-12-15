package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.BrowkerDAO;
import Exception.ValidacaoException;
import Model.Browker;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BrowkerService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BrowkerDAO dao;
	
	public BrowkerService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarBrowker(Browker browker) throws ValidacaoException {
		this.ValidarUsuario(browker);
		dao.AdicionarBrowker(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtuzalizarBrowker(Browker browker) {
		dao.AtualizarBrowker(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverBrowker(Browker browker) {
		dao.RemoverBrowker(browker);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverBrowkerPeloID(Integer id) {
		boolean resultado = dao.RemoverBrowkerPorID(id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarBrowker(Integer id, Browker browker) {
		Browker browkerDoBanco = dao.BuscarPorID(id);
		browkerDoBanco.AtualizarCampos(browker);
		dao.AtualizarBrowker(browkerDoBanco);
		dao.comitarCache();
	}
	
	public Browker BuscarBrowkerPeloID(Integer id) {
		return dao.BuscarPorID(id);
	}
	
	public List<Browker> ListarBrowker() {
		return dao.ListaTodosBrowkers();
	}
	
	
	private void ValidarUsuario(Browker browker) throws ValidacaoException {
		if(browker.getNumeroIP() != null || browker.getPorta() != null) {
			throw new ValidacaoException("NUMERO DE IP ou a PORTA n�o pode ser menor ou igual ZERO");
		}
	}
}
