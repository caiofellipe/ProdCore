package br.com.api.prodcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.prodcore.dto.ExceptionDTO;

@ControllerAdvice
public class BeneficioAcessoHandler {

	@ExceptionHandler(BeneficioAcessoException.class)
	public ResponseEntity<ExceptionDTO> beneficioAcessoException(BeneficioAcessoException beneficioAcessoException){
		
		ExceptionDTO exceptionDTO = new ExceptionDTO(beneficioAcessoException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}
	
}
