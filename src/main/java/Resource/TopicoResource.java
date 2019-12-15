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

import Model.Topico;
import Service.TopicoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("topico")
public class TopicoResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089894L;
	
	@Inject
	private TopicoService tsService;
	
	public TopicoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Topico> ListarTopicos(){
		List<Topico> topicos = tsService.ListarTodosTopicos();
		return topicos;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopicoPorID(@PathParam("id") Integer id) {
		try {
			Topico topico = tsService.BuscarTopicoPorID(id);
			
			if(topico != null)
				return Response.ok().entity(topico).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CadastrarTopico(Topico topico) {
		try {
			tsService.CadastrarTopico(topico);
			URI uri = URI.create("/topico/" + topico.getId());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response DeletarTopico(@PathParam("id") Integer id) {
		try {
			boolean success = tsService.RemoverTopicoPorID(id);
			
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
	public void AtualizarTopico(@PathParam("id") Integer id, Topico topico) {
		tsService.AtualizarCamposDeTopico(id, topico);
	}
}
