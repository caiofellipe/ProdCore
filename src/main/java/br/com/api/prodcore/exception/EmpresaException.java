package br.com.api.prodcore.exception;

public class EmpresaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmpresaException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

}
