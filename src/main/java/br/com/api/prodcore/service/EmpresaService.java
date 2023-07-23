package br.com.api.prodcore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	private final EmpresaRepository empresaRepository;
	private final EmpresaMapper empresaMapper;
	
	public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
		super();
		this.empresaRepository = empresaRepository;
		this.empresaMapper = empresaMapper;
	}
	
	public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = empresaRepository.findByCnpj(empresaDTO.cnpj());
		
		if(empresa != null) {
			return empresaMapper.toDTO(empresa);
		}
		
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
		
		empresa.setCnpj(empresaDTO.cnpj());
		empresa.setEmail(empresaDTO.email());
		empresa.setEndereco(empresaDTO.endereco());
	
		empresa.setLogo(empresaDTO.logo());
		empresa.setNome(empresaDTO.nome());
		empresa.setPlanos(empresaDTO.plano());
		empresa.setRamo(empresaDTO.ramo());
		empresa.setTelefone(empresaDTO.telefone());
		empresa.setUsuario(empresaDTO.usuario());
		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}
}
