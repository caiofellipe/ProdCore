package br.com.api.prodcore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.dto.mapper.ProdutoMapper;
import br.com.api.prodcore.exception.EmpresaException;
import br.com.api.prodcore.exception.ProdutoException;
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
	
	public List<ProdutoDTO> cadastrarProdutos(List<ProdutoDTO> produtosDTO) {
		Produto produto = new Produto();
		List<ProdutoDTO> listProdutos = new ArrayList<ProdutoDTO>();

		 
		for(ProdutoDTO produtoDTO: produtosDTO) {
			if(produtoDTO.id() != null) {
				produto = produtoRepository.findById(produtoDTO.id()).orElseThrow(() -> new ProdutoException("Produto já cadastrado"));
			}
			
			produto = produtoMapper.toEntity(produtoDTO);
			produtoRepository.save(produto);
			listProdutos.add(produtoMapper.toDTO(produto));
			
		}
		
		return listProdutos;
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
		Produto produto = produtoRepository.findById(produtoDTO.id()).orElseThrow(() -> new ProdutoException("Produto não encontrado."));
		Empresa empresa = empresaRepository.findById(produto.getEmpresa().getId()).orElseThrow(() -> new EmpresaException("Empresa não encontrada.", null));
		
		produto = produtoMapper.toEntity(produtoDTO);
		
		produto.setEmpresa(empresa);
		
		return produtoMapper.toDTO(produtoRepository.save(produto));
	}
}
