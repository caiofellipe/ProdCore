package br.com.api.prodcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.CadastroUsuarioDTO;
import br.com.api.prodcore.dto.TokenUsuarioDTO;
import br.com.api.prodcore.dto.UsuarioAuthDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.service.AutenticacaoService;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {
	
	@Autowired
	AutenticacaoService autenticacaoService;
	
	@PostMapping("/login")
	public ResponseEntity<TokenUsuarioDTO> login(@RequestBody UsuarioAuthDTO usuarioAuthDTO) throws Exception {
		return ResponseEntity.ok(autenticacaoService.autenticacao(usuarioAuthDTO));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) {
		return ResponseEntity.ok(autenticacaoService.cadastrar(cadastroUsuarioDTO));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok(autenticacaoService.logout());
	}
}
