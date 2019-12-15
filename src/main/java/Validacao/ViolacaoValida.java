package Validacao;

import java.util.List;

public class ViolacaoValida {
	
	private List<String> mensagens;
	
	public ViolacaoValida(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
}
