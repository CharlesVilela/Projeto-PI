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

import Model.Usuario;
import Service.UsuarioService;


@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("usuario")
public class UsuarioResource implements Serializable{
	
	private static final long serialVersionUID = 4260572209856089893L;
	
	@Inject
	private UsuarioService usService;
	
	public UsuarioResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuario(){
		List<Usuario> usuario = usService.listarUsuario();
		return usuario;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirUsuario(Usuario usuario) {
		try {
			System.out.println("Usuario Resource");
			usService.cadastrarUsuario(usuario);
			URI uri = URI.create("/usuario/" + usuario.getCpf());
			return Response.created(uri).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@DELETE
	public Response deleteUsuario(@PathParam("id") Integer id) {
		try {
			System.out.println("Chegou no service");
			boolean sucess = usService.removerUsuario(id);
			
			if(sucess)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
		
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioPeloId(Integer id) {
		try {
			Usuario usuario = usService.getUsuarioPeloId(id);
			
			if(usuario != null)
				return Response.ok().entity(usuario).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void AtualizarUsuario(@PathParam("id") Integer id, Usuario usuario) {
		usService.atualizarCampoDeUsuario(id, usuario);
	}
	
}
