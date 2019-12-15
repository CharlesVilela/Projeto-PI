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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Model.Canal;
import Model.Coisa;
import Model.Historico;
import Model.Topico;
import Service.CanalService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("canal")
public class CanalResource implements Serializable {
	
	private static final long serialVersionUID = 4260572209856089898L;
	
	@Inject
	private CanalService csService;
	
	public CanalResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Canal> ListarTodosCanal(){
		List<Canal> canal = csService.ListarCanal();
		return canal;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCanalPorID(@PathParam("id") Integer id) {
		try {
			Canal canal = csService.BuscarCanalPorID(id);
			
			if(canal != null)
				return Response.ok().entity(canal).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response CadastrarCanal(Canal canal) {
		try {
			csService.CadastrarCanal(canal);
			URI uri = URI.create("/canal/" + canal.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AtualizarCanal(@PathParam("id") Integer id, Canal canal) {
		csService.AtualizarCanal(id, canal);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarHistoricoACanal(@PathParam("id") Integer IdCanal, Historico historico) {
		csService.AdicionarHistoricoACanal(IdCanal, historico);
	}
	
	@Path("{id}{id}")
	@PATCH
	@DELETE
	public void RemoverHistoricoDeCanal(@PathParam("id") Integer IdCanal, @PathParam("id") Integer IdHistorico) {
		csService.RemoverHistoricoDeCanal(IdCanal, IdHistorico);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarDispositivoAoCanal(@PathParam("id") Integer IdCanal, Coisa coisa) {
		csService.AdicionarDispositivoAoCanal(IdCanal, coisa);
	}
	
	@Path("{id}{id}")
	@PATCH
	@DELETE
	public void RemoverDispositivoDeCanal(@PathParam("id") Integer IdCanal, @PathParam("id") Integer IdCoisa) {
		csService.RemoverDispositivoDoCanal(IdCanal, IdCoisa);
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AdicionarTopicoAoCanal(@PathParam("id") Integer IdCanal, Topico topico) {
		csService.AdicionarTopicoAoCanal(IdCanal, topico);
	}
	
	@Path("{id}{id}")
	@PATCH
	@DELETE
	public void RemoverTopicoDeCanal(@PathParam("id") Integer IdCanal, @PathParam("id") Integer IdTopico) {
		csService.RemoverTopicoDeCanal(IdCanal, IdTopico);
	}
	

}
