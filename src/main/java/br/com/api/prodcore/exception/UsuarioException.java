package br.com.api.prodcore.exception;

public class UsuarioException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsuarioException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}
	
}
