package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.CanalDAO;
import Exception.ValidacaoException;
import Model.Canal;
import Model.Coisa;
import Model.Historico;
import Model.Topico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CanalService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CanalDAO dao;
	
	public CanalService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarCanal(Canal canal) throws ValidacaoException {
		this.ValidarCanal(canal);
		dao.AdicionarCanal(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCanal(Canal canal) {
		dao.AtualizarCanal(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverCabal(Canal canal) {
		dao.RemoverCanal(canal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverCanalPorID(Integer id) {
		boolean resultado = dao.RemoverCanalPorID(id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCanal(Integer id, Canal canal) {
		Canal canalDoBanco = dao.BuscarCanalPorID(id);
		canalDoBanco.AtualizarCampos(canal);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarHistoricoACanal(Integer IdCanal, Historico historico) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.AdicionarHistoricoACanal(historico);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverHistoricoDeCanal(Integer IdCanal, Integer IdHistorico) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.RemoverHistoricoDeCanal(IdHistorico);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarDispositivoAoCanal(Integer IdCanal, Coisa coisa) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.AdicionarDispositivoAoCanal(coisa);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverDispositivoDoCanal(Integer IdCanal, Integer IdCoisa) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.RemoverDispositivoDeCanal(IdCoisa);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarTopicoAoCanal(Integer IdCanal, Topico topico) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.AdicionarTopicoAoCanal(topico);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverTopicoDeCanal(Integer IdCanal, Integer IdTopico) {
		Canal canalDoBanco = dao.BuscarCanalPorID(IdCanal);
		canalDoBanco.RemoverTopicoDeCanal(IdTopico);
		dao.AtualizarCanal(canalDoBanco);
		dao.comitarCache();
	}
	
	public Canal BuscarCanalPorID(Integer id) {
		return dao.BuscarCanalPorID(id);
	}
	
	public List<Canal> ListarCanal() {
		return dao.ListaTodos();
	}
	
	
	private void ValidarCanal(Canal canal) throws ValidacaoException {
		if(canal.getTopico() == null) {
			throw new ValidacaoException ("O canal precisa de um TOPICO para fazer a comunica��o, ent�o ele n�o pode ser vazio");
		}
		
		if(canal.getNome() == null || canal.getNome().trim().isEmpty()) {
			throw new ValidacaoException ("NOME do canal n�o pode ser Vazio");
		}
	}
	
}
