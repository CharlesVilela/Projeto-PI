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
import Model.Tipo;
import Service.TipoService;

@Named
@RequestScoped
public class TipoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Tipo tipo;
	
	private List<Tipo> listaTipo = new ArrayList<Tipo>();
	
	@Inject
	private TipoService tpService;
	
	public TipoBean() {
		listaTipo.addAll(listaTipo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<Tipo> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public TipoService getTpService() {
		return tpService;
	}

	public void setTpService(TipoService tpService) {
		this.tpService = tpService;
	}
	
	public void salvar() throws ValidacaoException {
		try {
			tpService.CadastrarTipo(tipo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	public void remover() {
		try {
			tpService.RemoverTipo(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tipo removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o tipo!"));
		}
	}
	
	public List<Tipo> listarTodos(){
		listaTipo = tpService.ListarTodosTipo();
		return listaTipo;
	}
	
	public void atualizarTipo() throws Exception{
		try {
			tpService.AtualizarCamposDeTipo(id, tipo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tipo atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o tipo!"));
		}
	}

}
