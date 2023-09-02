package br.com.api.prodcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@ControllerAdvice
public class UsuarioExceptionHandler {

	
	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<ExceptionDTO> usuarioException(UsuarioException usuarioException){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Throwable causa = usuarioException.getCause();
		
		if(causa instanceof BadCredentialsException || causa instanceof DisabledException) {
			status = HttpStatus.UNAUTHORIZED;
		}else if(causa instanceof UsernameNotFoundException) {
			status = HttpStatus.NOT_FOUND;
		}
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(usuarioException.getMessage());
		return ResponseEntity.status(status).body(exceptionDTO);
	}
	
}
