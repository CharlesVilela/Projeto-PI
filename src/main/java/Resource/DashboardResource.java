package Resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Model.Coisa;
import Model.Dashboard;
import Model.Historico;
import Service.DashboardService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("dashboard")
public class DashboardResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private DashboardService dsService;
	
	public DashboardResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dashboard> ListarTodosDashboard(){
		List<Dashboard> dashboard = dsService.ListarTodosDashboard();
		return dashboard;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BuscarDashboardPeloID(@PathParam("id") Integer id) {
		try {
			Dashboard dashboard = dsService.BuscarPeloID(id);
			
			if(dashboard != null)
				return Response.ok().entity(dashboard).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarDashboard(Dashboard dashboard) {
		try {
			dsService.CadastrarDashboard(dashboard);
			URI uri = URI.create("/dashboard/" + dashboard.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response DeletarDashboard(@PathParam("id") Integer id) {
		try {
			boolean success = dsService.RemoverDashboard(id);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarHistoricoAoDashboard(@PathParam("id") Integer idDashboard, Historico historico) {
		dsService.AdicionarHistoricoADashboard(idDashboard, historico);
	}
	
	@Path("{id}")
	@DELETE
	public void RemoverHistoricoDashboard(@PathParam("id") Integer idDashboard, Integer idHistorico) {
		dsService.RemoverHistoricoDoDashboard(idDashboard, idHistorico);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarDispositivoAoDashboard(@PathParam("id") Integer idDashboard, Coisa coisa) {
		dsService.AdicionarDispositivoAoDashboard(idDashboard, coisa);
	}
	
	@Path("{id}")
	@DELETE
	public void RemoverDispositivoDoDashboard(@PathParam("id") Integer idDashboard, Integer idCoisa) {
		dsService.RemoverDispositivoDoDashboard(idDashboard, idCoisa);
	}
	
}
