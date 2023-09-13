package br.com.api.prodcore.dto;

import org.springframework.http.HttpStatus;

public record ErroDTO (
	Long horario,
	HttpStatus status,
	String erro,
	String mensagem,
	String caminho
	){}
