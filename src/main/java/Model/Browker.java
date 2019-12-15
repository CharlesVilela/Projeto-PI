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
@Table(name="BROWKER")
public class Browker implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotBlank(message = "O NUMERO DO IP é obrigatorio e não pode ser vazio")
	@Column(name="NUMERO_IP", nullable = false)
	private String numeroIP;
	
	@NotBlank(message = "A PORTA é obrigatorio e não pode ser vazio")
	@Column(name="PORTA", nullable = false)
	private String porta;
	
	
	@ManyToMany
	@JoinTable(name = "BROWKER_TOPICO", 
	joinColumns = @JoinColumn(name = "ID_BROWKER"), 
	inverseJoinColumns = @JoinColumn(name = "ID_TOPICO"))
	private List <Topico> topico;
	
	@JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
	@ManyToOne
	private  Usuario usuario;
	
	
	public Browker () {}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNumeroIP() {
		return numeroIP;
	}

	public void setNumeroIP(String numeroIP) {
		this.numeroIP = numeroIP;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public List<Topico> getTopico() {
		return topico;
	}

	public void setTopico(List<Topico> topico) {
		this.topico = topico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void AtualizarCampos(Browker browker) {
		if(browker == null) return ;
		
		if(browker.getNumeroIP() != null) {
			this.setNumeroIP(browker.getNumeroIP());
		}
		
		if(browker.getPorta() != null) {
			this.setPorta(browker.getPorta());
		}
		
	}
		
}
