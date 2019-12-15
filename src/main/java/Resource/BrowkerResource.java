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

import Model.Browker;
import Service.BrowkerService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("browker")
public class BrowkerResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089895L;
	
	@Inject
	private BrowkerService bkService;
	
	public BrowkerResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Browker> listarTodosBrowker(){
		List<Browker> browker = bkService.ListarBrowker();
		return browker;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BuscarBrowkerPeloID(@PathParam("id") Integer id) {
		try {
			Browker browker = bkService.BuscarBrowkerPeloID(id);
			
			if(browker != null)
				return Response.ok().entity(browker).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarBrowker(Browker browker) {
		try {
			bkService.CadastrarBrowker(browker);
			URI uri = URI.create("/browker/" + browker.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response DeleteBrowker(@PathParam("id") Integer id) {
		try {
			boolean success = bkService.RemoverBrowkerPeloID(id);
			
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
	public void AtualizarBrowker(@PathParam("id") Integer id, Browker browker) {
		bkService.AtualizarBrowker(id, browker);
	}
	
}
