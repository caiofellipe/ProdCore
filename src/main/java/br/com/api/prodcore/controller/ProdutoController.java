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

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/")
	public List<ProdutoDTO> listarTodasProdutos(){
		return produtoService.listarTodosProdutos();
	}
	
	@GetMapping("/{id}")
	public ProdutoDTO listarProdutoPeloId(@PathVariable Long id) {
		return produtoService.procurarProdutoPeloId(id);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoDTO criarProduto(@RequestBody ProdutoDTO produtoDTO) {
		return produtoService.criarProduto(produtoDTO);
	}
	
	@PutMapping("/atualizar")
	public ProdutoDTO atualizarProduto(@RequestBody ProdutoDTO produtoDTO) {
		return produtoService.atualizarProduto(produtoDTO);
	}
}
