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
@Table(name= "GRUPO")
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotBlank(message = "NOME do Grupo é obrigatorio e não pode ser vazio")
	@Column(name="NOME")
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "GRUPO_DISPOSITIVO", 
	joinColumns = @JoinColumn(name = "ID_GRUPO"), 
	inverseJoinColumns = @JoinColumn(name = "ID_DISPOSITIVO"))
	private List<Coisa> coisa;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID" )
	private Usuario usuario;
	
	
	public Grupo () {}
	


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<Coisa> getCoisa() {
		return coisa;
	}

	public void setCoisa(List<Coisa> coisa) {
		this.coisa = coisa;
	}
	
	public void AdicionarDispositivoAoGrupo(Coisa coisa) {
		this.coisa.add(coisa);
	}
	
	public void RemoverDispositivoDoGrupo(Integer IdCoisa) {
		for (int i = 0; i < this.coisa.size(); i++) {
			if(this.coisa.get(i).getId() == IdCoisa) {
				this.coisa.remove(i);
			}
		}
	}
	
	public void atualizarCampos(Grupo grupo) {
		if(grupo == null) return;
		
		if(grupo.getNome() != null) {
			this.setNome(grupo.getNome());
		}
	}
}
