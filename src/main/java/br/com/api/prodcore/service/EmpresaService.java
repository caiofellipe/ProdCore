package br.com.api.prodcore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.exception.EmpresaException;
import br.com.api.prodcore.exception.UsuarioException;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Endereco;
import br.com.api.prodcore.model.Produto;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.EnderecoRepository;
import br.com.api.prodcore.repository.ProdutoRepository;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class EmpresaService {
	
	private final EmpresaRepository empresaRepository;
	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	private final ProdutoRepository produtoRepository;

	private final EmpresaMapper empresaMapper;
	
	public EmpresaService(EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository,
			ProdutoRepository produtoRepository, EmpresaMapper empresaMapper) {
		super();
		this.empresaRepository = empresaRepository;
		this.usuarioRepository = usuarioRepository;
		this.enderecoRepository = enderecoRepository;
		this.produtoRepository = produtoRepository;
		
		this.empresaMapper = empresaMapper;
	}
	
	public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa();
		empresa = empresaRepository.findByCnpj(empresaDTO.cnpj());

		if(empresa != null) {
			throw new EmpresaException("Já existe uma empresa com este CNPJ.", null);
		}
		
		empresa = empresaMapper.toEntity(empresaDTO);

		Endereco endereco = empresaDTO.endereco();
		if(endereco != null) {
			empresa.setEndereco(endereco);
			endereco.setEmpresa(empresa);
		}
		
		Usuario usuario = usuarioRepository.findById(empresaDTO.usuario().getId()).orElseThrow(() -> new UsuarioException("Usuario não encontrado!", null));
		 
		if(usuario != null) {
			usuario.setEmpresa(empresa);
			usuario.setDataAlterado(LocalDateTime.now());
			empresa.setUsuario(usuario);
		}

		Empresa empresaSalva = empresaRepository.save(empresa);
		
		for(Produto produto: empresa.getProduto()) {
			if(produto.getId() == null) {
				produto.setEmpresa(empresa);
			}
			produtoRepository.save(produto);
		}
		
		return empresaMapper.toDTO(empresaSalva);
	}
	
	public EmpresaDTO procurarEmpresaPeloId(Long id) {
		return empresaRepository.findById(id)
				.map(empresaMapper::toDTO).orElseThrow(() -> new EmpresaException("Empresa não encontrada", null));
	}
	
	public List<EmpresaDTO> listarEmpresas(){
		return empresaRepository.todasEmpresasComEndereco()
				.stream()
				.map(empresaMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public EmpresaDTO atualizarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa(); 
		empresa = empresaRepository.findById(empresaDTO.id()).orElseThrow(() -> new EmpresaException("Empresa não encontrada.", null));
		
		if(empresa == null) {
			throw new EmpresaException("Empresa não encontrada.", null);
		}
		
		empresa = empresaMapper.toEntity(empresaDTO);
		
		Endereco endereco = empresaDTO.endereco();
		if(endereco != null) {
			empresa.setEndereco(endereco);
			endereco.setEmpresa(empresa);
		}
		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}

	public List<EmpresaDTO> procurarEmpresaPorEstadoECidade(String uf, String cidade) {
		List<EmpresaDTO> empresas = new ArrayList<>();
		List<Endereco> enderecoEmpresas = enderecoRepository.localizacaoEmpresa(uf, cidade);
		
		for(Endereco enderecoEmpresa: enderecoEmpresas) {
			empresas.add(empresaMapper.toDTO(enderecoEmpresa.getEmpresa()));
		}
		
		return empresas;
	}
}
