package Model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HISTORICO")
public class Historico implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="DATA")
	@Temporal(TemporalType.DATE)
	private Date dataHora;
	
	
	@ManyToMany(mappedBy= "historico")
	private List<Dashboard> dashboard;
	
	@ManyToOne
	@JoinColumn(name ="ID_CANAL", referencedColumnName = "ID")
	private Canal canal;
	
	public Historico () {}
	

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}


	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}


	public List<Dashboard> getDashboard() {
		return dashboard;
	}

	public void setDashboard(List<Dashboard> dashboard) {
		this.dashboard = dashboard;
	}

	
}
