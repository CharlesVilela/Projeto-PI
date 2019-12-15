package Exception;

import Validacao.ViolacaoValida;

public class ValidacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private ViolacaoValida violacaos;
	
	public ValidacaoException() {
		super();
	}
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
	
	public ValidacaoException(ViolacaoValida violacoes) {
		this.violacaos = violacoes;
	}

	public ViolacaoValida getViolacaos() {
		return violacaos;
	}

	public void setViolacaos(ViolacaoValida violacaos) {
		this.violacaos = violacaos;
	}
}
