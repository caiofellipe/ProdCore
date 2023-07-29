package br.com.api.prodcore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.EmpresaDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.EnderecoRepository;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class EmpresaService {
	
	private final EmpresaRepository empresaRepository;
	private final EmpresaMapper empresaMapper;
	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	
	public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
		super();
		this.empresaRepository = empresaRepository;
		this.usuarioRepository = usuarioRepository;
		this.enderecoRepository = enderecoRepository;
		
		this.empresaMapper = empresaMapper;
	}
	
	public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = new Empresa();

		if(empresaDTO.id()  != null) {
			empresa = empresaRepository.findByCnpj(empresaDTO.cnpj());
			return empresaMapper.toDTO(empresa);
		}
		empresa = empresaMapper.toEntity(empresaDTO);
		
		// TODO 1. Faça o cadastro de planos e produtos no front e back - TODO CORRIGIR ERRO AO SALVAR UM PRODUTO COM PLANO (modelo no postman)
		// TODO 2. Teste o fluxo de cadastros de planos > produtos > vinculado a empresa
		// TODO 3. Usuario pode selecionar plano cadastrado e vincular a seu perfil (pagamento fake)
		// TODO 4. implemente cadastro de usuario e login (veja sobre o spring security no youtube)
		// TODO 5. Implementar docker e encontrar uma hospedagem free para testar online
		
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
		empresaRepository.findById(empresaDTO.id()).orElseThrow();
		
		Empresa empresa = empresaMapper.toEntity(empresaDTO);
		
		if(empresa.getEndereco() != null) {
			enderecoRepository.save(empresa.getEndereco());
		}
		
		return empresaMapper.toDTO(empresaRepository.save(empresa));
	}
}
