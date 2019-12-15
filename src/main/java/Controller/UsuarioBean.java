package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Exception.ValidacaoException;
import Model.Usuario;
import Service.UsuarioService;

@Named
@RequestScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Usuario usuario;
	
	private List<Usuario> listaUsuario = new ArrayList<Usuario>();
	
	@Inject
	private UsuarioService usService;
	
	public UsuarioBean() {
		listaUsuario.addAll(listaUsuario);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public UsuarioService getUsService() {
		return usService;
	}
	
	public void setUsService(UsuarioService usService) {
		this.usService = usService;
	}
	
	
	public void salvar() throws ValidacaoException {
		System.out.println("Usuario Bean");
		try {
			usService.cadastrarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	
	public void remover() throws ValidacaoException{
		System.out.println("Chegou no BEan");
		try {
			usService.removerUsuario(usuario.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o usuario!"));
		}
		
	}
	
	
	public List<Usuario> listarTodos(){
		listaUsuario = usService.listarUsuario();
		return listaUsuario;
		
	}
	
	public void atualizarUsuario() throws Exception{
		try {
			usService.atualizarCampoDeUsuario(id, usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o usuario!"));
		}
	}
	
	
	
}
