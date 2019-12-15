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

import Model.Coisa;
import Model.Tipo;
import Service.CoisaService;

public class CoisaResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private CoisaService csService;
	
	public CoisaResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coisa> ListarDispositivos(){
		List<Coisa> coisa = csService.ListarTodosDispositivos();
		return coisa;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BuscarDispositivoPeloID(@PathParam("id") Integer id) {
		try {
			Coisa coisa = csService.BuscarDispositivoPeloID(id);
			
			if(coisa != null)
				return Response.ok().entity(coisa).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarDispositivo(Coisa coisa) {
		try {
			csService.CadastrarDispositivo(coisa);
			URI uri = URI.create("/coisa/" + coisa.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response DeletarDispositivo(@PathParam("id") Integer id) {
		try {
			boolean success = csService.RemoverDispositivo(id);
			
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
	public void AtualizarCamposDoDispositivo(@PathParam("id") Integer id, Coisa coisa) {
		csService.AtualizarCamposDeDispositivo(id, coisa);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarTipoADispositivo(@PathParam("id") Integer idCoisa, Tipo tipo) {
		csService.AdicionarTipoAoDispositivo(idCoisa, tipo);
	}
	
	@Path("{id}")
	@DELETE
	public void RemoverTipoDispositivo(@PathParam("id") Integer idCoisa, Integer idTipo) {
		csService.RemoverTipoDoDispositivo(idCoisa, idTipo);
	}
}
