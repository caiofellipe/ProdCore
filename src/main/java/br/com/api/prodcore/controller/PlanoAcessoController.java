package br.com.api.prodcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.PlanoAcessoDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.service.PlanoAcessoService;

@RestController
@RequestMapping("/api/plano-acesso")
public class PlanoAcessoController {

	@Autowired
	private PlanoAcessoService planoAcessoService;
	
	@GetMapping("/")
	public List<PlanoAcessoDTO> listarTodos(){
		return planoAcessoService.listarTodos();
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PlanoAcessoDTO> salvar(@RequestBody PlanoAcessoDTO planoAcessoDTO){
		return ResponseEntity.ok(planoAcessoService.criar(planoAcessoDTO));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<PlanoAcessoDTO> atualizar(@RequestBody PlanoAcessoDTO planoAcessoDTO){
		return ResponseEntity.ok(planoAcessoService.atualizar(planoAcessoDTO));
	}
	
	@PostMapping("/contratar")
	public ResponseEntity<UsuarioDTO> contratar(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
		return ResponseEntity.ok(planoAcessoService.contratar(usuarioDTO));
	}
}
