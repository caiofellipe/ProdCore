package br.com.api.prodcore.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.api.prodcore.model.Role;

public record UsuarioDTO(
	Long id,	
	String nome,
	Long idUsuarioConvite,
	String email,
	@JsonIgnore
	String senha,
	boolean ativo,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dataCriado,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dataAlterado,
	List<Role> roles,
	//Long empresaId,
	String foto
) {}
