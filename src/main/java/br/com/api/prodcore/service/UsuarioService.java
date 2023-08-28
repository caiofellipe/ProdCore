package br.com.api.prodcore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.api.prodcore.dto.UsuarioDTO;
import br.com.api.prodcore.dto.UsuarioRolesDTO;
import br.com.api.prodcore.dto.mapper.UsuarioMapper;
import br.com.api.prodcore.model.Role;
import br.com.api.prodcore.model.UserRoles;
import br.com.api.prodcore.model.Usuario;
import br.com.api.prodcore.repository.RoleRepository;
import br.com.api.prodcore.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final RoleRepository roleRepository;
	
	private final UsuarioMapper usuarioMapper;

	UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, UsuarioMapper usuarioMapper){
		this.usuarioRepository = usuarioRepository;
		this.roleRepository = roleRepository;

		this.usuarioMapper = usuarioMapper;
	}
	
	public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findByUsuarioEmail(usuarioDTO.email());

		if(usuario != null) {
			throw new Error("Usuario " +usuario.getNome()+ " já está cadastrado!");
		}

		usuario = usuarioMapper.toEntity(usuarioDTO);
		
		usuario.setAtivo(true);
		
		usuario.setDataCriado(LocalDateTime.now());
		usuario.setDataAlterado(LocalDateTime.now());
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(new Role(2L, "USER"));
		usuario.setRoles(roleList);
		
		// TODO Somente em dev
		if(usuario.getIdUsuarioConvite() == null) {
			usuario.setIdUsuarioConvite(0L);
		}
		
		usuario.setSenha(criptografaSenha().encode(usuario.getSenha()));
		
		return usuarioMapper.toDTO(usuarioRepository.save(usuario));
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
			usuarioEncontrado.setEmail(usuarioDTO.email());
			usuarioEncontrado.setDataCriado(usuarioDTO.dataCriado());
			
			return usuarioMapper.toDTO(usuarioRepository.save(usuarioEncontrado));
	}
	
	private BCryptPasswordEncoder criptografaSenha() {
		return new BCryptPasswordEncoder();
	}

	public UsuarioDTO criarUsuarioRoles(UsuarioRolesDTO usuarioRolesDTO) throws Exception {
		List<Role> listRole = new ArrayList<>();
		UserRoles usuarioRole = usuarioRepository.findUserRoles(usuarioRolesDTO.usuarioId(), usuarioRolesDTO.rolesId());
		
		if(usuarioRole != null) {
			throw new Error("Este usuario já possui está role");
		}
		
		Usuario usuario = usuarioRepository.findById(usuarioRolesDTO.usuarioId()).orElseThrow(() -> new Exception("Usuario não encontrado"));
		Role role = roleRepository.findById(usuarioRolesDTO.rolesId()).get();

		if(usuario == null) {
			throw new Error("Usuario não encontrado!");
		}
		
		if(role == null) {
			throw new Error("Role não encontrada!");
		}

		listRole.add(role);
		usuario.setRoles(listRole);

		UsuarioDTO usuarioSalvoDTO = usuarioMapper.toDTO(usuarioRepository.save(usuario));
		
		return usuarioSalvoDTO;
		
	}

	public UsuarioDTO usuarioAtual() {
		Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioMapper.toDTO((Usuario) usuario);
	}
	
}
