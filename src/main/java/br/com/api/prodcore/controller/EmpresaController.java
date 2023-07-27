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

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.service.EmpresaService;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController{
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/")
	public List<EmpresaDTO> listarTodasEmpresas(){
		return empresaService.listarTodasEmpresas();
	}
	
	@GetMapping("/{id}")
	public EmpresaDTO listarEmpresaPeloId(@PathVariable Long id) {
		return empresaService.procurarEmpresaPeloId(id);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmpresaDTO criarEmpresa(@RequestBody EmpresaDTO empresaDTO) throws Exception {
		return empresaService.criarEmpresa(empresaDTO);
	}
	
	@PutMapping("/atualizar")
	public EmpresaDTO atualizarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
		return empresaService.atualizarEmpresa(empresaDTO);
	}
	
	
}
