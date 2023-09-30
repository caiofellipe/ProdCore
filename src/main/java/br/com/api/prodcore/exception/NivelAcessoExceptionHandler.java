package br.com.api.prodcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@ControllerAdvice
public class NivelAcessoExceptionHandler {

	@ExceptionHandler(NivelAcessoException.class)
	public ResponseEntity<ExceptionDTO> NivelAcessoException(NivelAcessoException nivelAcessoException){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(nivelAcessoException.getMessage());
		return ResponseEntity.status(status).body(exceptionDTO);
	}
}
