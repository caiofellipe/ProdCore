package br.com.api.prodcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.CadastroUsuarioDTO;
import br.com.api.prodcore.dto.ConviteDTO;
import br.com.api.prodcore.dto.TokenUsuarioDTO;
import br.com.api.prodcore.dto.UsuarioAuthDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.service.AutenticacaoService;
import br.com.api.prodcore.service.ConviteService;

@RestController
@RequestMapping("/api/convite")
public class ConviteController {
	/*	@Autowired
		ConviteService conviteService;
		
		@GetMapping("/")
		public ResponseEntity<List<ConviteDTO>> convites() throws Exception {
			return ResponseEntity.ok(conviteService.todosConvites());
		}
		
		*/
}
