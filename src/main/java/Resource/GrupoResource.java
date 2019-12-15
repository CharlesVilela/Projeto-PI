package Resource;

import java.io.Serializable;
import java.net.URI;

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
import Model.Grupo;
import Service.GrupoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("grupo")
public class GrupoResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089897L;
	
	@Inject
	private GrupoService gpService;
	
	public GrupoResource() {}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGrupoPorID(@PathParam("id") Integer id) {
		try {
			Grupo grupo = gpService.BuscarGrupoPorID(id);
			if(grupo != null)
				return Response.ok().entity(grupo).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();	
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarGrupo(Grupo grupo) {
		try {
			gpService.CadastrarGrupo(grupo);
			URI uri = URI.create("/grupo/" + grupo.getId());
			return Response.created(uri).build();
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response deleteGrupo(@PathParam("id") Integer id) {
		try {
			boolean success = gpService.RemoverGrupoPorID(id);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AtualizarGrupo(@PathParam("id") Integer id, Grupo grupo) {
		gpService.AtualizarGrupo(id, grupo);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarDispositivoAoGrupo(@PathParam("id") Integer IdGrupo, Coisa coisa) {
		gpService.AdicionarDispositivoAGrupo(IdGrupo, coisa);
	}
	
	@Path("{id}{id}")
	@PATCH
	@DELETE
	public void RemoverDispositivoDoGrupo(@PathParam("id") Integer IdGrupo, @PathParam("id") Integer IdCoisa) {
		gpService.RemoverDispositivoDoGrupo(IdGrupo, IdCoisa);
	}
	
	

}
