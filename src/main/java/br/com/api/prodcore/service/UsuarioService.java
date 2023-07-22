package br.com.api.prodcore.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.dto.mapper.UsuarioMapper;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;

	UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
	}
	
	public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findByEmailOrLogin(usuarioDTO.email(),usuarioDTO.login());
		if(usuario == null) {
			usuario = usuarioMapper.toEntity(usuarioDTO);
			usuario.setAtivo(true);
			usuario.setDataCriado(new Date());
			usuario.setDataAlterado(new Date());
			usuario.setIdUsuario(UUID.randomUUID());
			return usuarioMapper.toDTO(usuarioRepository.save(usuario));
		}
		
		return null;
	}
	
	public UsuarioDTO procurarUsuarioId(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(usuarioMapper::toDTO).orElseThrow();
	}

	public List<UsuarioDTO> listaTodosUsuarios() {
		return usuarioRepository.findAll()
				.stream()
				.map(usuarioMapper::toDTO)
				.collect(Collectors.toList());
	
	}

	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuarioEncontrado = usuarioRepository.findById(id)
				.orElseThrow(() -> new Exception("Usuario não encontrado"));
			usuarioEncontrado.setId(usuarioDTO.id());
			usuarioEncontrado.setNome(usuarioDTO.nome());
			usuarioEncontrado.setSobrenome(usuarioDTO.sobrenome());
			usuarioEncontrado.setEmail(usuarioDTO.email());
			usuarioEncontrado.setLogin(usuarioDTO.login());
			usuarioEncontrado.setIdUsuario(usuarioDTO.idUsuario());
			usuarioEncontrado.setDataCriado(usuarioDTO.dataCriado());
			usuarioEncontrado.setNivelUsuario(usuarioDTO.nivelUsuario());
			
			return usuarioMapper.toDTO(usuarioRepository.save(usuarioEncontrado));
	}

	public ResponseEntity<UsuarioDTO> listarUsuario(String nome, String email, String login) {
		Usuario usuarioEncontrado = usuarioRepository.procuraUsuarioPorNomeOuEmailOuLogin(nome,email,login);
		if(usuarioEncontrado != null) {
			return ResponseEntity.ok().body(usuarioMapper.toDTO(usuarioEncontrado));
		}
		
		return null;
	}
	
}
