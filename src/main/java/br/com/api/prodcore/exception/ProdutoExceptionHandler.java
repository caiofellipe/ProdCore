package br.com.api.prodcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@ControllerAdvice
public class ProdutoExceptionHandler {
	
	@ExceptionHandler(ProdutoException.class)
	public ResponseEntity<ExceptionDTO> tokenException(ProdutoException produtoException){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(produtoException.getMessage());
		return ResponseEntity.status(status).body(exceptionDTO);
	}
}
