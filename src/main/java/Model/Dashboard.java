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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DASHBOARD")
public class Dashboard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id; 
	
	@ManyToMany
	@JoinTable(name = "HISTORICO_DASHBOARD", 
	joinColumns = @JoinColumn(name = "ID_DASHBOARD"), 
	inverseJoinColumns = @JoinColumn(name = "ID_HISTORICO"))
	private List <Historico> historico;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "dashboard")
	private List <Coisa> coisa;
	
	
	public Dashboard () {}
	
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
	
	public void AdicionarHistoricoADashboard(Historico historico) {
		this.historico.add(historico);
	}
	
	public void RemoverHistoricoDeDashboard(Integer idHistorico) {
		for (int i = 0; i < this.historico.size(); i++) {
			if(this.historico.get(i).getId() == idHistorico) {
				this.historico.remove(i);
			}
		}
	}
	
	public void AdicionarDispositivoADashboard(Coisa coisa) {
		this.coisa.add(coisa);
	}
	
	public void RemoverDispositivoDoDashboard(Integer idCoisa) {
		for (int i = 0; i < this.coisa.size(); i++) {
			if(this.coisa.get(i).getId() == idCoisa) {
				this.coisa.remove(i);
			}
		}
	}

}
