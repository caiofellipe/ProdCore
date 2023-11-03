package br.com.api.prodcore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.NivelAcessoDTO;
import br.com.api.prodcore.dto.PlanoAcessoDTO;
import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.dto.UsuarioPlanoAcessoDTO;
import br.com.api.prodcore.dto.mapper.NivelAcessoMapper;
import br.com.api.prodcore.dto.mapper.PlanoAcessoMapper;
import br.com.api.prodcore.dto.mapper.UsuarioMapper;
import br.com.api.prodcore.dto.mapper.UsuarioPlanoAcessoMapper;
import br.com.api.prodcore.exception.NivelAcessoException;
import br.com.api.prodcore.exception.PlanoAcessoException;
import br.com.api.prodcore.exception.UsuarioException;
import br.com.api.prodcore.model.BeneficioAcesso;
import br.com.api.prodcore.model.NivelAcesso;
import br.com.api.prodcore.model.PlanoAcesso;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.model.UsuarioPlanoAcesso;
import br.com.api.prodcore.repository.BeneficioAcessoRepository;
import br.com.api.prodcore.repository.NivelAcessoRepository;
import br.com.api.prodcore.repository.PlanoAcessoRepository;
import br.com.api.prodcore.repository.UsuarioPlanoAcessoRepository;
import br.com.api.prodcore.repository.UsuarioRepository;
import br.com.api.prodcore.util.UtilDatas;

@Service
public class PlanoAcessoService {

	private final PlanoAcessoRepository planoAcessoRepository;
	private final NivelAcessoRepository nivelAcessoRepository;
	private final BeneficioAcessoRepository beneficioAcessoRepository;
	private final UsuarioRepository usuarioRepository;
	private final UsuarioPlanoAcessoRepository usuarioPlanoAcessoRepository;
	
	private final UsuarioService usuarioService;
	
	private final PlanoAcessoMapper planoAcessoMapper;
	private final UsuarioMapper usuarioMapper;
	private final UsuarioPlanoAcessoMapper usuarioPlanoAcessoMapper;
	private final NivelAcessoMapper nivelAcessoMapper;
	private UtilDatas util = new UtilDatas();

	public PlanoAcessoService(PlanoAcessoRepository planoAcessoRepository, NivelAcessoRepository nivelAcessoRepository,
			BeneficioAcessoRepository beneficioAcessoRepository, UsuarioRepository usuarioRepository, 
			UsuarioPlanoAcessoRepository usuarioPlanoAcessoRepository, UsuarioService usuarioService, 
			PlanoAcessoMapper planoAcessoMapper,  UsuarioMapper usuarioMapper, UsuarioPlanoAcessoMapper usuarioPlanoAcessoMapper, 
			NivelAcessoMapper nivelAcessoMapper) {
		super();
		this.planoAcessoRepository = planoAcessoRepository;
		this.nivelAcessoRepository = nivelAcessoRepository;
		this.beneficioAcessoRepository = beneficioAcessoRepository;
		this.usuarioRepository = usuarioRepository;
		this.usuarioPlanoAcessoRepository = usuarioPlanoAcessoRepository;
		
		this.usuarioService = usuarioService;
		
		this.planoAcessoMapper = planoAcessoMapper;
		this.usuarioMapper = usuarioMapper;
		this.usuarioPlanoAcessoMapper = usuarioPlanoAcessoMapper;
		this.nivelAcessoMapper = nivelAcessoMapper;
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
	
	public List<NivelAcessoDTO> listarNiveisDeAcessoComBeneficios(){
		List<NivelAcesso> listNiveisAcesso = planoAcessoRepository.listarNiveisDeAcessoComBeneficios();
		
		return listNiveisAcesso.stream().map(nivelAcessoMapper::toDTO).collect(Collectors.toList());
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
		
		planoAcesso.setEditadoPeloUsuarioId(usuarioService.usuarioAtual().id());
		
		planoAcesso.setNivelAcesso(nivelAcesso);
		
		planoAcessoRepository.save(planoAcesso);
		
		return planoAcessoMapper.toDTO(planoAcesso);
	}

	public UsuarioDTO contratar(UsuarioDTO usuarioDTO) throws Exception {
		int comparaDataPlano = 0;
		Usuario usuario = new Usuario();
		UsuarioPlanoAcesso usuarioPlanoAcesso = new UsuarioPlanoAcesso();
		
		PlanoAcesso planoAcesso = planoAcessoRepository.findById(usuarioDTO.planoAcesso().getId()).orElseThrow(() -> new PlanoAcessoException("Plano não encontrado"));
		usuario = usuarioService.procurarUsuarioPorEmail(usuarioDTO.email());
	
		if(usuario == null) {
			throw new UsuarioException("Usuario não encontrado", null);
		}
		
		if(planoAcesso == null) {
			throw new PlanoAcessoException("Plano de Acesso não encontrado");
		}

		if(usuario.getPlanoAcesso() != null) {
			usuarioPlanoAcesso = usuarioPlanoAcessoRepository.pesquisaPorUsuarioIdEPlanoAcessoId(usuario.getId(), usuario.getPlanoAcesso().getId());
			comparaDataPlano = usuarioPlanoAcesso.getDataExpiracao().compareTo(usuarioPlanoAcesso.getDataContratado());
			
			if(comparaDataPlano < 0) {
				usuario.setPlanoAcesso(new PlanoAcesso());
				throw new Exception("Plano de acesso está vencido! Contrate outro");
			}
		}

		usuario.setPlanoAcesso(planoAcesso);

		usuarioPlanoAcesso = new UsuarioPlanoAcesso(
				usuario.getId(), planoAcesso.getId(), util.dataAtual(), util.expiracaoPlanoAcesso());
		
		usuario = usuarioRepository.save(usuario);
		usuarioPlanoAcessoRepository.save(usuarioPlanoAcesso);
		
		return usuarioMapper.toDTO(usuario);
	}

	public UsuarioPlanoAcessoDTO contratoAtual(Long usuarioId) throws Exception {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioException("Usuario não encontrado", null));
		
		UsuarioPlanoAcesso usuarioPlanoAcesso = usuarioPlanoAcessoRepository.pesquisaPlanoPeloIdUsuario(usuario.getId());
		
		if(usuarioPlanoAcesso == null) {
			throw new Exception("Usuario não possui Plano de acesso.");
		}
		
		return usuarioPlanoAcessoMapper.toDTO(usuarioPlanoAcesso);
	}

	public List<NivelAcessoDTO> listarNiveisAcesso(){
		List<NivelAcesso> listNiveisAcesso = planoAcessoRepository.listarNiveisDeAcesso();
		
		return listNiveisAcesso.stream().map(nivelAcessoMapper::toDTO).collect(Collectors.toList());
	}
	
}
