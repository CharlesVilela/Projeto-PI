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
import Model.Browker;
import Service.BrowkerService;

@Named
@RequestScoped
public class BrowkerBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Browker browker;
	
	private List<Browker> listaBrowker = new ArrayList<Browker>();
	
	@Inject
	private BrowkerService bkService;
	
	public BrowkerBean() {
		listaBrowker.addAll(listaBrowker);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Browker getBrowker() {
		return browker;
	}

	public void setBrowker(Browker browker) {
		this.browker = browker;
	}

	public List<Browker> getListaBrowker() {
		return listaBrowker;
	}

	public void setListaBrowker(List<Browker> listaBrowker) {
		this.listaBrowker = listaBrowker;
	}

	public BrowkerService getBkService() {
		return bkService;
	}

	public void setBkService(BrowkerService bkService) {
		this.bkService = bkService;
	}

	public void salvar() throws ValidacaoException {
		try {
			bkService.CadastrarBrowker(browker);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	public void remover() {
		try {
			bkService.RemoverBrowkerPeloID(browker.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Browker removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o browker!"));
		}
	}
	
	public List<Browker> listarTodos(){
		listaBrowker = bkService.ListarBrowker();
		return listaBrowker;
	}
	
	public void atualizarTipo() throws Exception{
		try {
			bkService.AtualizarBrowker(id, browker);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Browker atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o browker!"));
		}
	}
}
