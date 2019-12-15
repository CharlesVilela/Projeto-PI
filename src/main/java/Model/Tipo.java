package Model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "TIPO")
public class Tipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ID")
	private int id;
	
	@NotBlank(message = "NOME do Tipo de dispositivo é obrigatorio e não pode ser vazio")
	@Column(name="NOME")
	private String nome;
	
	@NotBlank(message = "FABRICANTE do dispositivo é obrigatorio e não pode ser vazio")
	@Column(name="FABRICANTE")
	private String fabricante;
	
	@NotBlank(message = "CARACTERISTICAS do Tipo do dispositivo é obrigatorio e não pode ser vazio")
	@Column(name="CARACTERISTICA")
	private String caracteristica;
	
	@ManyToMany(mappedBy = "tipo")
	private List<Coisa> coisa;
	
	
	public Tipo () {}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaracteristica() {
		return caracteristica;
	}


	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}



	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Coisa> getCoisa() {
		return coisa;
	}

	public void setCoisa(List<Coisa> coisa) {
		this.coisa = coisa;
	}
	
	public void AtualizarCamposDeTipo(Tipo tipo) {
		if(tipo == null) return;
		
		if(tipo.getNome() != null) {
			this.setNome(tipo.getNome());
		}
		
		if(tipo.getCaracteristica() != null) {
			this.setCaracteristica(tipo.getCaracteristica());
		}
		
		if(tipo.getFabricante() != null) {
			this.setFabricante(tipo.getFabricante());
		}
		
	}

}
