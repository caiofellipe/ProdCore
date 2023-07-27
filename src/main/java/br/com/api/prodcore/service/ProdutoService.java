package br.com.api.prodcore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.dto.mapper.ProdutoMapper;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.model.Produto;
import br.com.api.prodcore.repository.PlanoRepository;
import br.com.api.prodcore.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final PlanoRepository planoRepository;
	private final ProdutoRepository produtoRepository;
	private final ProdutoMapper produtoMapper;
	
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper, PlanoRepository planoRepository) {
		super();
		this.planoRepository = planoRepository;
		this.produtoRepository = produtoRepository;
		this.produtoMapper = produtoMapper;
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
		Plano plano = planoRepository.findById(produtoDTO.planoId()).get();
		
		produto.setId(produtoDTO.id()); 
		produto.setNome(produtoDTO.nome());
		produto.setCategoria(produtoDTO.categoria());
		produto.setSubCategoria(produtoDTO.subCategoria());
		produto.setDescricao(produtoDTO.descricao()); 
		produto.setPlano(plano);
		produto.setImagens(produtoDTO.imagens());
		
		return produtoMapper.toDTO(produtoRepository.save(produto));
	}
}
