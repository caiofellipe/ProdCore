package br.com.api.prodcore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Endereco;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class EmpresaService {
	
	private final EmpresaRepository empresaRepository;
	private final EmpresaMapper empresaMapper;
	private final UsuarioRepository usuarioRepository;
	
	public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, UsuarioRepository usuarioRepository) {
		super();
		this.empresaRepository = empresaRepository;
		this.usuarioRepository = usuarioRepository;
		
		this.empresaMapper = empresaMapper;
	}
	
	public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = empresaRepository.findByCnpj(empresaDTO.cnpj());
		if(empresa != null) {
			return empresaMapper.toDTO(empresa);
		}
		empresa = empresaMapper.toEntity(empresaDTO);


		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}
	
	public EmpresaDTO procurarEmpresaPeloId(Long id) {
		return empresaRepository.findById(id)
				.map(empresaMapper::toDTO).orElseThrow();
	}
	
	public List<EmpresaDTO> listarTodasEmpresas(){
		return empresaRepository.findAll()
				.stream()
				.map(empresaMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public EmpresaDTO atualizarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = empresaRepository.findById(empresaDTO.id()).orElseThrow();
		
		empresa.setNome(empresaDTO.nome());
		empresa.setCnpj(empresaDTO.cnpj());
		empresa.setEmail(empresaDTO.email());
		empresa.setRamo(empresaDTO.ramo());
		empresa.setTelefone(empresaDTO.telefone());
		empresa.setEndereco(empresaDTO.endereco());
		empresa.setLogo(empresaDTO.logo());
		empresa.setPlanos(empresaDTO.plano());
		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}
}
