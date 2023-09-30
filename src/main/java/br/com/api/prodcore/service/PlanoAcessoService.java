package br.com.api.prodcore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.PlanoAcessoDTO;
import br.com.api.prodcore.dto.mapper.PlanoAcessoMapper;
import br.com.api.prodcore.exception.NivelAcessoException;
import br.com.api.prodcore.exception.PlanoAcessoException;
import br.com.api.prodcore.model.BeneficioAcesso;
import br.com.api.prodcore.model.NivelAcesso;
import br.com.api.prodcore.model.PlanoAcesso;
import br.com.api.prodcore.repository.BeneficioAcessoRepository;
import br.com.api.prodcore.repository.NivelAcessoRepository;
import br.com.api.prodcore.repository.PlanoAcessoRepository;

@Service
public class PlanoAcessoService {

	private final PlanoAcessoRepository planoAcessoRepository;
	private final NivelAcessoRepository nivelAcessoRepository;
	private final BeneficioAcessoRepository beneficioAcessoRepository;
	
	private final PlanoAcessoMapper planoAcessoMapper;

	public PlanoAcessoService(PlanoAcessoRepository planoAcessoRepository, NivelAcessoRepository nivelAcessoRepository,
			BeneficioAcessoRepository beneficioAcessoRepository, PlanoAcessoMapper planoAcessoMapper) {
		super();
		this.planoAcessoRepository = planoAcessoRepository;
		this.nivelAcessoRepository = nivelAcessoRepository;
		this.beneficioAcessoRepository = beneficioAcessoRepository;
		
		this.planoAcessoMapper = planoAcessoMapper;
	}
	
	public PlanoAcessoDTO criar(PlanoAcessoDTO planoAcessoDTO) {
		PlanoAcesso planoAcesso = new PlanoAcesso();
		NivelAcesso nivelAcesso = new NivelAcesso();
		
		planoAcesso = planoAcessoRepository.findByNomeEValor(planoAcessoDTO.nome(), planoAcessoDTO.valor());
		nivelAcesso = nivelAcessoRepository.findById(planoAcessoDTO.nivelAcesso().getId()).get();
		
		if(planoAcesso != null) {
			throw new PlanoAcessoException("Já existe um Plano com estes dados.");
		}
		
		if(nivelAcesso == null) {
			throw new NivelAcessoException("Nivel de acesso não encontrado");
		}
		
		//nivelAcesso = nivelBeneficioRepository.pesquisaNivelAcessoEBeneficioAcessoPorId(planoAcessoDTO.nivelAcesso().getId());
		
		planoAcesso = planoAcessoMapper.toEntity(planoAcessoDTO);
		
		for(BeneficioAcesso beneficioAcesso: planoAcessoDTO.nivelAcesso().getBeneficioAcesso()) {
			beneficioAcessoRepository.save(beneficioAcesso);
		}
		
		planoAcesso.setNivelAcesso(planoAcessoDTO.nivelAcesso());

		planoAcessoRepository.save(planoAcesso);
		
		return planoAcessoMapper.toDTO(planoAcesso);
	}
	
	public List<PlanoAcessoDTO> listarTodos(){
		List<PlanoAcesso> listPlanos = planoAcessoRepository.listarTodosNiveisDeAcessoComBeneficio();
		
		return listPlanos.stream().map(planoAcessoMapper::toDTO).collect(Collectors.toList());
		/*return planoAcessoRepository.findAll()
				.stream()
				.map(planoAcessoMapper::toDTO)
				.collect(Collectors.toList());
			*/
	}
	
	public PlanoAcessoDTO atualizar(PlanoAcessoDTO planoAcessoDTO) {
		PlanoAcesso planoAcesso = new PlanoAcesso();
		NivelAcesso nivelAcesso = new NivelAcesso();
		
		planoAcesso = planoAcessoRepository.findById(planoAcessoDTO.id()).orElseThrow(() -> new PlanoAcessoException("Plano não encontrado."));
		nivelAcesso = nivelAcessoRepository.findById(planoAcessoDTO.nivelAcesso().getId()).get();
		
		planoAcesso = planoAcessoMapper.toEntity(planoAcessoDTO);
		
		List<BeneficioAcesso> listBeneficios = new ArrayList<BeneficioAcesso>();
		for(BeneficioAcesso beneficioAcesso: planoAcessoDTO.nivelAcesso().getBeneficioAcesso()) {
			beneficioAcesso.setNivelAcesso(nivelAcesso);
			listBeneficios.add(beneficioAcesso);
			nivelAcesso.setBeneficioAcesso(listBeneficios);
			nivelAcessoRepository.save(nivelAcesso);
		}
		
		planoAcesso.setNivelAcesso(nivelAcesso);
		
		planoAcessoRepository.save(planoAcesso);
		
		return planoAcessoMapper.toDTO(planoAcesso);
	}
}
