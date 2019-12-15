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
import Model.Grupo;
import Service.GrupoService;

@Named
@RequestScoped
public class GrupoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Grupo grupo;
	
	private List<Grupo> listaGrupo = new ArrayList<Grupo>();
	
	@Inject
	private GrupoService gpService;
	
	public GrupoBean() {
		listaGrupo.addAll(listaGrupo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupo() {
		return listaGrupo;
	}

	public void setListaGrupo(List<Grupo> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

	public GrupoService getGpService() {
		return gpService;
	}

	public void setGpService(GrupoService gpService) {
		this.gpService = gpService;
	}

	public void salvar() throws ValidacaoException {
		try {
			gpService.CadastrarGrupo(grupo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	public void remover() {
		try {
			gpService.RemoverGrupoPorID(grupo.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grupo removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o grupo!"));
		}
	}
	
	public List<Grupo> listarTodos(){
		listaGrupo = gpService.ListarTodosGrupos();
		return listaGrupo;
	}
	
	public void atualizarTipo() throws Exception{
		try {
			gpService.AtualizarGrupo(id, grupo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grupo atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o grupo!"));
		}
	}

}
