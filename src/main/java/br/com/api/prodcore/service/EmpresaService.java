package br.com.api.prodcore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Endereco;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.EnderecoRepository;
import br.com.api.prodcore.repository.PlanoRepository;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class EmpresaService {
	
	private final EmpresaRepository empresaRepository;
	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	private final PlanoRepository planoRepository;

	private final EmpresaMapper empresaMapper;
	public EmpresaService(EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository,
			PlanoRepository planoRepository, EmpresaMapper empresaMapper) {
		super();
		this.empresaRepository = empresaRepository;
		this.usuarioRepository = usuarioRepository;
		this.enderecoRepository = enderecoRepository;
		this.planoRepository = planoRepository;
		
		this.empresaMapper = empresaMapper;
	}
	
	public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa();

		if(empresaDTO.id() != null) {
			empresa = empresaRepository.findByCnpj(empresaDTO.cnpj());
			return empresaMapper.toDTO(empresa);
		}
		
		empresa = empresaMapper.toEntity(empresaDTO);

		Endereco endereco = empresaDTO.endereco();
		if(endereco != null) {
			empresa.setEndereco(endereco);
			endereco.setEmpresa(empresa);
		}

		Empresa empresaSalva = empresaRepository.save(empresa);
		
		for(Plano plano: empresa.getPlanos()) {
			if(plano.getId() == null) {
				plano.setEmpresa(empresa);
			}
			planoRepository.save(plano);
		}
		return empresaMapper.toDTO(empresaSalva);
	}
	
	public EmpresaDTO procurarEmpresaPeloId(Long id) {
		return empresaRepository.findById(id)
				.map(empresaMapper::toDTO).orElseThrow();
	}
	
	public List<EmpresaDTO> listarEmpresas(){
		return empresaRepository.findAllEmpresa()
				.stream()
				.map(empresaMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public EmpresaDTO atualizarEmpresa(EmpresaDTO empresaDTO) {
		empresaRepository.findById(empresaDTO.id()).orElseThrow();
		
		Empresa empresa = empresaMapper.toEntity(empresaDTO);
		
		if(empresa.getEndereco() != null) {
			enderecoRepository.save(empresa.getEndereco());
		}
		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}
}
