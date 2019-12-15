package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table (name= "USUARIO")
@JsonInclude(Include.NON_EMPTY)
public class Usuario implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID")
		private int id;
		
		@CPF
		@Column(name="CPF")
		private String cpf;
		
		@NotBlank(message = "NOME do usuário é obrigatorio e não pode ser vazio")
		@Column(name="NOME", nullable = false)
		private String nomeUsuario;
		
		@Email(message = "E-MAIL do usuário é obrigatorio e não pode ser vazio")
		@Column(name="EMAIL", nullable = false)
		private String email;
		
		@NotBlank(message = "SENHA do usuário é obrigatorio e não pode ser vazio")
		@Column(name="SENHA", nullable = false)
		private String senha;
		
		@OneToMany(mappedBy = "usuario")
		private Collection <Coisa> coisa;
		
		@OneToMany(mappedBy = "usuario")
		private  List <Browker> browker;
		
		@OneToMany(mappedBy = "usuario")
		private  List <Grupo> grupo;
		
		@OneToMany(mappedBy = "usuario")
		private List<Dashboard> dashboard;
	    
	    public Usuario() {}	
	    

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCpf() {
			return cpf;
		}
		
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getNomeUsuario() {
			return nomeUsuario;
		}

		public void setNomeUsuario(String nomeUsuario) {
			this.nomeUsuario = nomeUsuario;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@JsonIgnore
		public String getSenha() {
			return senha;
		}

		@JsonProperty
		public void setSenha(String senha) {
			this.senha = senha;
		}

		public Collection<Coisa> getCoisa() {
			return coisa;
		}

		public void setCoisa(Collection<Coisa> coisa) {
			this.coisa = coisa;
		}

		public List<Browker> getBrowker() {
			return browker;
		}

		public void setBrowker(List<Browker> browker) {
			this.browker = browker;
		}

		public List<Grupo> getGrupo() {
			return grupo;
		}

		public void setGrupo(List<Grupo> grupo) {
			this.grupo = grupo;
		}
		
		public List<Dashboard> getDashboard() {
			return dashboard;
		}

		public void setDashboard(List<Dashboard> dashboard) {
			this.dashboard = dashboard;
		}
	
		public void AtualizarCampos(Usuario usuario) {
			if(usuario == null) return;
			
			if(usuario.getCpf() != null) {
				this.setCpf(usuario.getCpf());
			}
			
			if(usuario.getNomeUsuario() != null) {
				this.setNomeUsuario(usuario.getNomeUsuario());
			}
			
			if(usuario.getEmail() != null) {
				this.setEmail(usuario.getEmail());
			}
			
			if(usuario.getSenha() != null) {
				this.setSenha(usuario.getSenha());
			}
		}
}