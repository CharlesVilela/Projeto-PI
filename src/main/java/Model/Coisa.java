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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="DISPOSITIVO")
public class Coisa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotBlank(message = "NOME do dispositivo é obrigatorio e não pode ser vazio")
	@Column(name="NOME")
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "DISPOSITIVO_TIPO", 
	joinColumns = @JoinColumn(name = "ID_DISPOSITIVO"), 
	inverseJoinColumns = @JoinColumn(name = "ID_TIPO"))
	private List <Tipo> tipo;
	
	@ManyToMany(mappedBy = "coisa")
	private List <Canal> canal;
	
	@Column(name="FABRICANTE")
	private String fabricante;
	
	@Column(name="ICONE")
	private String icone;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_DASHBOARD", referencedColumnName = "ID")
	private Dashboard dashboard;
	
	@ManyToMany(mappedBy= "coisa")
	private List<Grupo> grupo;
	
	
	public Coisa () {}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public void setCanal(List<Canal> canal) {
		this.canal = canal;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Tipo> getTipo() {
		return tipo;
	}

	public void setTipo(List<Tipo> tipo) {
		this.tipo = tipo;
	}

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<Canal> getCanal() {
		return canal;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void AdicionarTipoADispositivo(Tipo tipo) {
		this.tipo.add(tipo);
	}
	
	public void RemoverTipoDoDispositivo(Integer idTipo) {
		for (int i = 0; i < this.tipo.size(); i++) {
			if(this.tipo.get(i).getId() == idTipo) {
				this.tipo.remove(i);
			}
		}
	}
	
	public void AtualizaCampos(Coisa coisa) {
		if(coisa == null) return;
		
		if(coisa.getNome() != null) {
			this.setNome(coisa.getNome());
		}
		
		if(coisa.getIcone() != null) {
			this.setIcone(coisa.getIcone());
		}
		
		if(coisa.getFabricante() != null) {
			this.setFabricante(coisa.getFabricante());
		}
	}
}
