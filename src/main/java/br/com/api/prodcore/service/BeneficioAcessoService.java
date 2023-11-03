package br.com.api.prodcore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.BeneficioAcessoDTO;
import br.com.api.prodcore.dto.mapper.BeneficioAcessoMapper;
import br.com.api.prodcore.exception.BeneficioAcessoException;
import br.com.api.prodcore.model.BeneficioAcesso;
import br.com.api.prodcore.model.NivelAcesso;
import br.com.api.prodcore.repository.BeneficioAcessoRepository;
import br.com.api.prodcore.repository.NivelAcessoRepository;
import br.com.api.prodcore.repository.PlanoAcessoRepository;

@Service
public class BeneficioAcessoService {
	private final BeneficioAcessoRepository beneficioAcessoRepository;
	private final PlanoAcessoRepository planoAcessoRepository;
	private final NivelAcessoRepository nivelAcessoRepository;
	
	private final BeneficioAcessoMapper beneficioAcessoMapper;

	public BeneficioAcessoService(BeneficioAcessoRepository beneficioAcessoRepository,
			PlanoAcessoRepository planoAcessoRepository, NivelAcessoRepository nivelAcessoRepository, BeneficioAcessoMapper beneficioAcessoMapper) {
		super();
		this.beneficioAcessoRepository = beneficioAcessoRepository;
		this.planoAcessoRepository = planoAcessoRepository;
		this.nivelAcessoRepository = nivelAcessoRepository;
		
		this.beneficioAcessoMapper = beneficioAcessoMapper;
	}

	public List<BeneficioAcessoDTO> listarBeneficiosPorIdNivelAcesso(Long idNivelAcesso){
		List<BeneficioAcesso> listBeneficioAcesso = beneficioAcessoRepository.pesquisaBeneficioAcessoPeloIdDoNivelAcesso(idNivelAcesso);
		
		if(listBeneficioAcesso.size() == 0) {
			throw new BeneficioAcessoException("Nenhum Beneficio encontrado.");
		}
		
		List<BeneficioAcessoDTO> listBeneficioAcessoDTO = new ArrayList<BeneficioAcessoDTO>();
		for(BeneficioAcesso beneficioAcesso: listBeneficioAcesso) {
			listBeneficioAcesso.add(beneficioAcesso);
		}
		
		return listBeneficioAcessoDTO;
	}

	public BeneficioAcessoDTO criar(BeneficioAcessoDTO beneficioAcessoDTO) {
		BeneficioAcesso beneficioAcesso = beneficioAcessoRepository.likeNomeBeneficio(beneficioAcessoDTO.nome());
		NivelAcesso nivelAcesso = nivelAcessoRepository.pesquisaNivelAcessoPeloNome(beneficioAcessoDTO.nomeNivelAcesso());
		
		if(beneficioAcesso != null) {
			throw new BeneficioAcessoException("Beneficio de Acesso já cadastrado com o nome " + beneficioAcesso.getNome());
		}
		
		beneficioAcesso = beneficioAcessoMapper.toEntity(beneficioAcessoDTO);
		
		beneficioAcesso.setNivelAcesso(nivelAcesso);
		
		return beneficioAcessoMapper.toDTO(beneficioAcessoRepository.save(beneficioAcesso));
	}

	public BeneficioAcessoDTO atualizar(BeneficioAcessoDTO beneficioAcessoDTO) {
		BeneficioAcesso beneficioAcesso = beneficioAcessoRepository.findById(beneficioAcessoDTO.id()).orElseThrow(() -> new BeneficioAcessoException("Beneficio não cadastrado."));
		
		NivelAcesso nivelAcesso = beneficioAcesso.getNivelAcesso();
		beneficioAcesso = beneficioAcessoMapper.toEntity(beneficioAcessoDTO);
		beneficioAcesso.setNivelAcesso(nivelAcesso);
		
		return beneficioAcessoMapper.toDTO(beneficioAcessoRepository.save(beneficioAcesso));
	}

}
