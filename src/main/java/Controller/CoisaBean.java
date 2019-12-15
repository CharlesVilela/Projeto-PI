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
import Model.Coisa;
import Service.CoisaService;

@Named
@RequestScoped
public class CoisaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Coisa coisa;
	
	private List<Coisa> listaCoisa = new ArrayList<Coisa>();
	
	@Inject
	private CoisaService csService;
	
	public CoisaBean() {
		listaCoisa.addAll(listaCoisa);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	


	public Coisa getCoisa() {
		return coisa;
	}

	public void setCoisa(Coisa coisa) {
		this.coisa = coisa;
	}

	public List<Coisa> getListaCoisa() {
		return listaCoisa;
	}

	public void setListaCoisa(List<Coisa> listaCoisa) {
		this.listaCoisa = listaCoisa;
	}

	public CoisaService getCsService() {
		return csService;
	}

	public void setCsService(CoisaService csService) {
		this.csService = csService;
	}

	public void salvar() throws ValidacaoException {
		try {
			csService.CadastrarDispositivo(coisa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	public void remover() {
		try {
			csService.RemoverDispositivo(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dispositivo removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o dispositivo!"));
		}
	}
	
	public List<Coisa> listarTodos(){
		listaCoisa = csService.ListarTodosDispositivos();
		return listaCoisa;
	}
	
	public void atualizarTipo() throws Exception{
		try {
			csService.AtualizarCamposDeDispositivo(id, coisa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dispositivo atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o dispositivo!"));
		}
	}

}