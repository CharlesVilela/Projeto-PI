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
import Model.Topico;
import Service.TopicoService;

@Named
@RequestScoped
public class TopicoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Inject
	private Topico topico;
	
	private List<Topico> listaTopico = new ArrayList<Topico>();
	
	@Inject
	private TopicoService tsService;
	
	public TopicoBean() {
		listaTopico.addAll(listaTopico);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Topico> getListaTopico() {
		return listaTopico;
	}

	public void setListaTopico(List<Topico> listaTopico) {
		this.listaTopico = listaTopico;
	}
	
	
	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public TopicoService getTsService() {
		return tsService;
	}

	public void setTsService(TopicoService tsService) {
		this.tsService = tsService;
	}

	public void salvar() throws ValidacaoException {
		System.out.println("Usuario Bean");
		try {
			tsService.CadastrarTopico(topico);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}
	
	
	public void remover() throws ValidacaoException{
		System.out.println("Chegou no BEan");
		try {
			tsService.RemoverTopicoPorID(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Topico removido com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao remover o topico!"));
		}
		
	}
	
	
	public List<Topico> listarTodos(){
		listaTopico = tsService.ListarTodosTopicos();
		return listaTopico;
	}
	
	public void atualizarTopico() throws Exception{
		try {
			tsService.AtualizarCamposDeTopico(id, topico);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Topico atualizado com sucesso!"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao atualizar o topico!"));
		}
	}
}
