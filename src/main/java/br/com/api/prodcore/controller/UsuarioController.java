package br.com.api.prodcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public List<UsuarioDTO> listaTodosUsuarios(){
		return usuarioService.listaTodosUsuarios();
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO listarUsuarioPorId(@PathVariable Long id) {
		return usuarioService.procurarUsuarioId(id);
	}
	
	@GetMapping
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> listarUsuario(@RequestParam("nome") String nome, @RequestParam("email") String email, @RequestParam("login") String login) {
		return usuarioService.listarUsuario(nome, email, login);
	}
	
	@PostMapping
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO usuarioDTO){
		return ResponseEntity.ok(usuarioService.criarUsuario(usuarioDTO));
	}
	
	@PutMapping("/{id}")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) throws Exception {
		return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuarioDTO));
	}
	
	
	
}
