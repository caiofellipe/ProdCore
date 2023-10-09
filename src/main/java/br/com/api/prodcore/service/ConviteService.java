package br.com.api.prodcore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.prodcore.dto.ConviteDTO;
import br.com.api.prodcore.dto.mapper.ConviteMapper;
import br.com.api.prodcore.dto.mapper.EmpresaMapper;
import br.com.api.prodcore.repository.ConviteRepository;

@Service
public class ConviteService {

	private final ConviteRepository conviteRepository;
	private final ConviteMapper conviteMapper;
	
	ConviteService(ConviteRepository conviteRepository, ConviteMapper conviteMapper){
		super();
		this.conviteRepository = conviteRepository;
		this.conviteMapper = conviteMapper;
	}
	
	
	/*public List<ConviteDTO> todosConvites() {
		return conviteRepository.findAll()
				.stream()
				.map(conviteMapper::toDTO)
	}*/

}
