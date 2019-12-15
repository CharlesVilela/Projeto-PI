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
import Model.Canal;
import Service.CanalService;

@Named
@RequestScoped
public class CanalBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Canal canal;
	
	private List<Canal> listaCanal = new ArrayList<Canal>();
	
	@Inject
	private CanalService clService;
	
	public CanalBean() {
		listaCanal.addAll(listaCanal);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public List<Canal> getListaCanal() {
		return listaCanal;
	}

	public void setListaCanal(List<Canal> listaCanal) {
		this.listaCanal = listaCanal;
	}

	public CanalService getClService() {
		return clService;
	}

	public void setClService(CanalService clService) {
		this.clService = clService;
	}

	public void salvar() throws ValidacaoException {
		try {
			clService.CadastrarCanal(canal);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	public void remover() {
		try {
			clService.RemoverCanalPorID(canal.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Canal removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o canal!"));
		}
	}
	
	public List<Canal> listarTodos(){
		listaCanal = clService.ListarCanal();
		return listaCanal;
	}
	
	public void atualizarTipo() throws Exception{
		try {
			clService.AtualizarCanal(id, canal);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Canal atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o canal!"));
		}
	}


}
