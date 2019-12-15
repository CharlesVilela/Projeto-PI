package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.TopicoDAO;
import Exception.ValidacaoException;
import Model.Topico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TopicoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TopicoDAO dao;
	
	public TopicoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarTopico(Topico topico) throws ValidacaoException {
	
		this.ValidaTopico(topico);
		dao.AdicionarTopico(topico);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarTopico(Topico topico) {
		dao.AtualizarTopico(topico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverTopico(Topico topico) {
		dao.RemoverTopico(topico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverTopicoPorID(Integer id) {
		return dao.RemoverPorID(id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarCamposDeTopico(Integer id, Topico topico) {
		Topico topicoDoBanco = dao.BuscarTopicoID(id);
		topicoDoBanco.AtualizarCamposDeTopico(topico);
		dao.AtualizarTopico(topicoDoBanco);
		dao.comitarCache();
	}
	
	public Topico BuscarTopicoPorID(Integer id) {
		return dao.BuscarTopicoID(id);
	}
	
	public List<Topico> ListarTodosTopicos() {
		return dao.ListarTodosTopicos();
	}
	
	private void ValidaTopico(Topico topico) throws ValidacaoException {
		if(topico.getNome() == null || topico.getNome().trim().isEmpty()) {
			throw new ValidacaoException("NOME do topico n�o pode ser vazio");
		}
		
		if(topico.getQoS() != 0 || topico.getQoS() != 1 || topico.getQoS() != 2) {
			throw new ValidacaoException("O QoS n�o podem ser diferente de 0, 1 ou 2");
		}
	}
	
}
