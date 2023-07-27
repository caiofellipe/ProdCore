package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.model.Produto;

@Component
public class ProdutoMapper {
	public ProdutoDTO toDTO(Produto produto) {
		if(produto == null) {
			return null;
		}

		return new ProdutoDTO(
				produto.getId(), produto.getNome(), produto.getCategoria(),
				produto.getSubCategoria(), produto.getDescricao(), produto.getPlano().getId(),
				produto.getImagens()
				);
	} 
	
	public Produto toEntity(ProdutoDTO produtoDTO) {
		if(produtoDTO == null) {
			return null;
		}

		Produto produto = new Produto();
		Plano plano = new Plano();
		
		if(produtoDTO.id() != null){
			produto.setId(produtoDTO.id());
		}
		
		produto.setId(produtoDTO.id()); 
		produto.setNome(produtoDTO.nome());
		produto.setCategoria(produtoDTO.categoria());
		produto.setSubCategoria(produtoDTO.subCategoria());
		produto.setDescricao(produtoDTO.descricao()); 
		produto.setImagens(produtoDTO.imagens());
		
		plano.setId(produtoDTO.planoId());
		produto.setPlano(plano);
		
		return produto;
	}
}
