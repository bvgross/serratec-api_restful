package projeto_service_dto.servicedto.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import projeto_service_dto.servicedto.domain.Funcionario;
import projeto_service_dto.servicedto.dto.FuncionarioDTO;
import projeto_service_dto.servicedto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FotoService fotoService;
	
	public FuncionarioDTO createFuncionarioDTO(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId())
				.toUri();
		
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setNome(funcionario.getNome());
		dto.setDataNascimento(funcionario.getDataNascimento());
		dto.setSalario(funcionario.getSalario());
		dto.setUrl(uri.toString());
		
		return dto;
	}
	
	public List<FuncionarioDTO> listar() {
		List<FuncionarioDTO> funcionariosDTOs = funcionarioRepository
				.findAll()
				.stream()
				.map(f -> createFuncionarioDTO(f))
				.collect(Collectors.toList());
		
		return funcionariosDTOs;
	}
	
	public FuncionarioDTO buscar(Long id) {
		Optional<Funcionario> funcOpt = funcionarioRepository.findById(id);
		if (funcOpt.isPresent()) {
			Funcionario funcionario = funcOpt.get();
			return createFuncionarioDTO(funcionario);
		}
		return null;
	}
	
	public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = funcionarioRepository.save(funcionario);
		fotoService.inserir(funcionario, file);
		FuncionarioDTO funcionarioDTO = createFuncionarioDTO(funcionario);
		return funcionarioDTO;
	}

}

