package br.com.api.prodcore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.dto.mapper.ProdutoMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Produto;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final EmpresaRepository empresaRepository;
	private final ProdutoRepository produtoRepository;
	private final ProdutoMapper produtoMapper;
	
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper, EmpresaRepository empresaRepository) {
		super();
		this.produtoRepository = produtoRepository;
		this.produtoMapper = produtoMapper;

		this.empresaRepository = empresaRepository;
	}
	
	public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
		Optional<Produto> produto = produtoRepository.findById(produtoDTO.id());
		
		if(produto.isPresent()) {
			return produtoMapper.toDTO(produto.get());
		}
		
		return produtoMapper.toDTO(produtoRepository.save(produto.get()));
	}
	
	public ProdutoDTO procurarProdutoPeloId(Long id) {
		return produtoRepository.findById(id)
				.map(produtoMapper::toDTO).orElseThrow();
	}
	
	public List<ProdutoDTO> listarTodosProdutos(){
		return produtoRepository.findAll()
				.stream()
				.map(produtoMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.findById(produtoDTO.id()).orElseThrow();
		Empresa empresa = empresaRepository.findById(produtoDTO.empresa().getId()).get();
		
		produto.setId(produtoDTO.id()); 
		produto.setNome(produtoDTO.nome());
		produto.setDescricao(produtoDTO.descricao()); 
		produto.setEmpresa(empresa);
		produto.setImagem(produtoDTO.imagem());
		
		return produtoMapper.toDTO(produtoRepository.save(produto));
	}
}
