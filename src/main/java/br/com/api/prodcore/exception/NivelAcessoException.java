package br.com.api.prodcore.exception;

public class NivelAcessoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NivelAcessoException(String mensagem) {
		super(mensagem);
	}

}
