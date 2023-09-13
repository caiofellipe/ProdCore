package br.com.api.prodcore.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class TokenExceptionHandler {
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ExceptionDTO> tokenException(TokenException tokenException){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(tokenException.getMessage());
		return ResponseEntity.status(status).body(exceptionDTO);
	}
}
