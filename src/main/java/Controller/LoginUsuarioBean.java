package Controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;

import Model.Usuario;
import Service.UsuarioService;

@Named
@SessionScoped
public class LoginUsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usService;
	
	private Usuario usuarioLogado;
	
	private String email;
	private String senha;
	
	public LoginUsuarioBean() {}
	
	
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UsuarioService getUsService() {
		return usService;
	}
	public void setUsService(UsuarioService usService) {
		this.usService = usService;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String loginUsuario() {
		try {
			if(senha.trim().isEmpty() != true && email.trim().isEmpty() != true) {
				Usuario usuarioSession = usService.loginUsuarrio(senha, email);
				usuarioLogado = usuarioSession;
				if(usuarioSession != null) {
					FacesContext sessao = FacesContext.getCurrentInstance();
					sessao.getExternalContext().getSessionMap().put("usuario", usuarioSession);
					return "pagina_inicial?faces-redirect=true";
				}
				else {
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Email ou senha incorretos!"));
				}
			}
		}catch(Exception e) {
			return e.getMessage();
		}
		return null;
	}
	
	public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}

}
