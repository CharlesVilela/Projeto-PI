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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "TOPICO")
public class Topico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@NotBlank(message = "NOME do topico é obrigatorio e não pode ser vazio")
	@Column(name = "NOME")
	private String nome;
	
	@NotBlank(message = "QOS é obrigatorio e não pode ser vazio")
	@Column(name = "QOS")
	private int QoS;
	
	@NotBlank(message = "TIPO do topico é obrigatorio e não pode ser vazio")
	@Column(name = "TIPO")
	private String tipo;
	
	@ManyToMany
	@JoinTable(name = "CANAL_TOPICO", 
	joinColumns = @JoinColumn(name = "ID_CANAL"), 
	inverseJoinColumns = @JoinColumn(name = "ID_TOPICO"))
	private List <Canal> canal;
	
	@ManyToMany(mappedBy = "topico")
	private List <Browker> browker;
	
	
	public Topico () {}
	

	public List<Canal> getCanal() {
		return canal;
	}


	public void setCanal(List<Canal> canal) {
		this.canal = canal;
	}


	public long getId() {
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


	public int getQoS() {
		return QoS;
	}


	public void setQoS(int qoS) {
		QoS = qoS;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public List<Browker> getBrowker() {
		return browker;
	}

	public void setBrowker(List<Browker> browker) {
		this.browker = browker;
	}
	
	public void AtualizarCamposDeTopico(Topico topico) {
		if(topico == null) return;
		
		if(topico.getNome() != null) {
			this.setNome(topico.getNome());
		}
		
		if(topico.getQoS() >= 0 && topico.getQoS() < 3) {
			this.setQoS(topico.getQoS());
		}
	}
}
