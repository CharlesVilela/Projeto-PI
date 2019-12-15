package Resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

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

import Model.Tipo;
import Service.TipoService;

public class TipoResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private TipoService tsService;
	
	public TipoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tipo> ListarTodosTipos(){
		List<Tipo> tipo = tsService.ListarTodosTipo();
		return tipo;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BuscarTipoPeloID(@PathParam("id") Integer id) {
		try {
			Tipo tipo = tsService.BuscarTipoPeloID(id);
			
			if(tipo != null)
				return Response.ok().entity(tipo).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarTipo(Tipo tipo) {
		try {
			tsService.CadastrarTipo(tipo);
			URI uri = URI.create("/tipo/" + tipo.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response DeletarTipo(@PathParam("id") Integer id) {
		try {
			boolean success = tsService.RemoverTipo(id);
			
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
	public void AtualizarCamposDeTipo(@PathParam("id") Integer id, Tipo tipo) {
		tsService.AtualizarCamposDeTipo(id, tipo);
	}

}
