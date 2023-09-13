package br.com.api.prodcore.exception;

public class TokenException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TokenException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}
}
