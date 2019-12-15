package Service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import DAO.DashboardDAO;
import Model.Coisa;
import Model.Dashboard;
import Model.Historico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DashboardService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DashboardDAO dao;
	
	public DashboardService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void CadastrarDashboard(Dashboard dashboard) {
		dao.AdicionarDashboard(dashboard);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean RemoverDashboard(Integer id) {
		boolean resultado = dao.RemoverPorID(id);
		dao.ComitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AtualizarDashboard(Dashboard dashboard) {
		dao.AtualizarDashboard(dashboard);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarHistoricoADashboard(Integer idDashboard, Historico historico) {
		Dashboard dashboardDoBanco = dao.BuscarDashboardPeloID(idDashboard);
		dashboardDoBanco.AdicionarHistoricoADashboard(historico);
		dao.AtualizarDashboard(dashboardDoBanco);
		dao.ComitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverHistoricoDoDashboard(Integer idDashboard, Integer idHistorico) {
		Dashboard dashboardDoBanco = dao.BuscarDashboardPeloID(idDashboard);
		dashboardDoBanco.RemoverHistoricoDeDashboard(idHistorico);
		dao.AtualizarDashboard(dashboardDoBanco);
		dao.ComitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void AdicionarDispositivoAoDashboard(Integer idDashboard, Coisa coisa) {
		Dashboard dashboardDoBanco = dao.BuscarDashboardPeloID(idDashboard);
		dashboardDoBanco.AdicionarDispositivoADashboard(coisa);
		dao.AtualizarDashboard(dashboardDoBanco);
		dao.ComitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void RemoverDispositivoDoDashboard(Integer idDashboard, Integer idCoisa) {
		Dashboard dashboardDoBanco = dao.BuscarDashboardPeloID(idDashboard);
		dashboardDoBanco.RemoverDispositivoDoDashboard(idCoisa);
		dao.AtualizarDashboard(dashboardDoBanco);
		dao.ComitarCache();
	}
	
	public List<Dashboard> ListarTodosDashboard(){
		return dao.ListarTodosDashboard();
	}
	
	public Dashboard BuscarPeloID(Integer id) {
		return dao.BuscarDashboardPeloID(id);
	}
	

}
