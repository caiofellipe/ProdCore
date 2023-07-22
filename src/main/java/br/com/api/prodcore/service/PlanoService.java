package br.com.api.prodcore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.PlanoDTO;
import br.com.api.prodcore.dto.mapper.PlanoMapper;
import br.com.api.prodcore.model.Plano;
import br.com.api.prodcore.repository.PlanoRepository;

@Service
public class PlanoService {

	private final PlanoRepository planoRepository;
	private final PlanoMapper planoMapper;
	
	public PlanoService(PlanoRepository planoRepository, PlanoMapper planoMapper) {
		super();
		this.planoRepository = planoRepository;
		this.planoMapper = planoMapper;
	}
	
	public PlanoDTO criarPlano(PlanoDTO planoDTO) {
		Optional<Plano> plano = planoRepository.findById(planoDTO.id());
		
		if(plano.isPresent()) {
			return planoMapper.toDTO(plano.get());
		}
		
		return planoMapper.toDTO(planoRepository.save(plano.get()));
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
		plano.setEmpresa(planoDTO.empresa());
		
		return planoMapper.toDTO(planoRepository.save(plano));
	}
	
}
