package br.com.api.prodcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.PlanoDTO;
import br.com.api.prodcore.service.PlanoService;

@RestController
@RequestMapping("/api/plano")
public class PlanoController{
	@Autowired
	private PlanoService planoService;
	
	@GetMapping("/")
	public List<PlanoDTO> listarPlanos(){
		return planoService.listarPlanos();
	}
	
	@GetMapping("/{id}")
	public PlanoDTO listarPlanoPeloId(@PathVariable Long id) {
		return planoService.procurarPlanoPeloId(id);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlanoDTO criarPlano(@RequestBody PlanoDTO planoDTO) {
		return planoService.criarPlano(planoDTO);
	}
	
	@PutMapping("/atualizar")
	public PlanoDTO atualizarPlano(@RequestBody PlanoDTO planoDTO) {
		return planoService.atualizarPlano(planoDTO);
	}
	
}
