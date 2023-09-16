package br.com.api.prodcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@ControllerAdvice
public class EmpresaExceptionHandler {
	
	@ExceptionHandler(EmpresaException.class)
	public ResponseEntity<ExceptionDTO> empresaException(EmpresaException tokenException){
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(tokenException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}
}
