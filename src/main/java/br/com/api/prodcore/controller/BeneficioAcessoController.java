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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.prodcore.dto.BeneficioAcessoDTO;
import br.com.api.prodcore.service.BeneficioAcessoService;

/**
 * Beneficio é equivalente é permissões
 * */

@RestController
@RequestMapping("/api/beneficio-acesso")
public class BeneficioAcessoController {
	@Autowired
	private BeneficioAcessoService beneficioAcessoService;
	

	@GetMapping("/{idPlanoAcesso}")
	public List<BeneficioAcessoDTO> listarBeneficiosPorIdNivelAcesso(@PathVariable Long idNivelAcesso)  {
		return beneficioAcessoService.listarBeneficiosPorIdNivelAcesso(idNivelAcesso);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BeneficioAcessoDTO> criarBeneficio(@RequestBody BeneficioAcessoDTO beneficioAcessoDTO){
		return ResponseEntity.ok(beneficioAcessoService.criar(beneficioAcessoDTO));
	}
	
	@PutMapping("/atualizar")
	public BeneficioAcessoDTO atualizarBeneficio(@RequestBody BeneficioAcessoDTO beneficioAcessoDTO) {
		return beneficioAcessoService.atualizar(beneficioAcessoDTO);
	}
	

}
