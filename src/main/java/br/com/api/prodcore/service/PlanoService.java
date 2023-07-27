package br.com.api.prodcore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.PlanoDTO;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.dto.mapper.PlanoMapper;
import br.com.api.prodcore.model.Empresa;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.repository.EmpresaRepository;
import br.com.api.prodcore.repository.PlanoRepository;

@Service
public class PlanoService {

	private final PlanoRepository planoRepository;
	private final PlanoMapper planoMapper;
	private final EmpresaRepository empresaRepository;
	private final EmpresaMapper empresaMapper;
	
	public PlanoService(PlanoRepository planoRepository, PlanoMapper planoMapper, EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
		super();
		this.planoRepository = planoRepository;
		this.empresaRepository = empresaRepository;

		this.planoMapper = planoMapper;
		this.empresaMapper = empresaMapper;
	}
	
	public PlanoDTO criarPlano(PlanoDTO planoDTO) {
		Plano plano = new Plano();
		
		if(planoDTO.id() != null) {
			plano = planoRepository.findById(planoDTO.id()).get();
			return planoMapper.toDTO(plano);
		}
		
		plano = planoMapper.toEntity(planoDTO);
		
		return planoMapper.toDTO(planoRepository.save(plano));
	}
	
	public PlanoDTO procurarPlanoPeloId(Long id) {
		return planoRepository.findById(id)
				.map(planoMapper::toDTO).orElseThrow();
	}
	
	public List<PlanoDTO> listarTodosPlanos(){
		return planoRepository.findAll()
				.stream()
				.map(planoMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public PlanoDTO atualizarPlano(PlanoDTO planoDTO) {
		Plano plano = planoRepository.findById(planoDTO.id()).orElseThrow();
		
		plano.setId(planoDTO.id());
		plano.setNome(planoDTO.nome());
		plano.setNivel(planoDTO.nivel());
		plano.setProduto(planoDTO.produto());
		
		if(planoDTO.empresaId() == null) {
			Empresa empresa = empresaRepository.findById(planoDTO.empresaId()).get();
			plano.setEmpresa(empresa);
		}
		
		return planoMapper.toDTO(planoRepository.save(plano));
	}
	
}
