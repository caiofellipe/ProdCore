package br.com.api.prodcore.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.api.prodcore.dto.ProdutoDTO;
import br.com.api.prodcore.model.Categoria;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Produto;
import br.com.api.prodcore.model.SubCategoria;

@Component
public class ProdutoMapper {
	public ProdutoDTO toDTO(Produto produto) {
		if(produto == null) {
			return null;
		}

		return new ProdutoDTO(
				produto.getId(), produto.getNome(), produto.getCategoria(),
				produto.getSubCategoria(), produto.getDescricao(), produto.getEmpresa(),
				produto.getImagem()
				);
	} 
	
	public Produto toEntity(ProdutoDTO produtoDTO) {
		if(produtoDTO == null) {
			return null;
		}

		Produto produto = new Produto();
		
		
		if(produtoDTO.id() != null){
			produto.setId(produtoDTO.id());
		}
		
		produto.setId(produtoDTO.id()); 
		produto.setNome(produtoDTO.nome());
		produto.setDescricao(produtoDTO.descricao()); 
		produto.setImagem(produtoDTO.imagem());
		
		Empresa empresa = produtoDTO.empresa();
		if(empresa != null) {
			produto.setEmpresa(empresa);
		}
		
		Categoria categoria = produtoDTO.categoria();
		SubCategoria subCategoria = produtoDTO.subCategoria();
		
		if(categoria != null && subCategoria != null) {
			produto.setCategoria(categoria);
			produto.setSubCategoria(subCategoria);
		}
		
		return produto;
	}
}
