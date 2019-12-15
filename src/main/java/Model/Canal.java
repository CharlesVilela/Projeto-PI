package Model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="CANAL")
public class Canal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotBlank(message = "NOME do canal é obrigatorio e não pode ser vazio")
	@Column(name="NOME")
	private String nome;
	
	@ManyToMany(mappedBy = "canal")
	private List <Topico> topico;
	
	//FAZER ANOTACAO
	//@SuppressWarnings("unused")
	//private enum Tipo {STRING, INT, DOUBLE, FLOAT, LONG, BOOELAN}
	
	@ManyToMany
	@JoinTable(name = "CANAL_DISPOSITIVO", 
	joinColumns = @JoinColumn(name = "ID_CANAL"), 
	inverseJoinColumns = @JoinColumn(name = "ID_DISPOSITIVO"))
	private List <Coisa> coisa;
	
	@OneToMany(mappedBy = "canal")
	private List <Historico> historico;
	
	
	public Canal () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Topico> getTopico() {
		return topico;
	}

	public void setTopico(List<Topico> topico) {
		this.topico = topico;
	}

	public List<Coisa> getCoisa() {
		return coisa;
	}

	public void setCoisa(List<Coisa> coisa) {
		this.coisa = coisa;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
	
	public void AdicionarHistoricoACanal(Historico historico) {
		this.historico.add(historico);
	}
	
	public void RemoverHistoricoDeCanal(Integer IdHistorico) {
		for (int i = 0; i < this.historico.size(); i++) {
			if(this.historico.get(i).getId() == IdHistorico) {
				this.historico.remove(i);
			}
		}
	}
	
	public void AdicionarDispositivoAoCanal(Coisa coisa) {
		this.coisa.add(coisa);
	}
	
	public void RemoverDispositivoDeCanal(Integer IdCoisa) {
		for (int i = 0; i < this.coisa.size(); i++) {
			if(this.coisa.get(i).getId() == IdCoisa) {
				this.coisa.remove(i);
			}
		}
	}
	
	public void AdicionarTopicoAoCanal(Topico topico) {
		this.topico.add(topico);
	}
	
	public void RemoverTopicoDeCanal(Integer IdTopico) {
		for (int i = 0; i < this.topico.size(); i++) {
			if(this.topico.get(i).getId() == IdTopico) {
				this.topico.remove(i);
			}
		}
	}
	
	public void AtualizarCampos(Canal canal) {
		if(canal == null) return;
		
		if(canal.getNome() != null) {
			this.setNome(canal.getNome());
		}
	}
	
	
}
