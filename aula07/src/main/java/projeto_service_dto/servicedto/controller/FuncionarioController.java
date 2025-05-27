package projeto_service_dto.servicedto.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projeto_service_dto.servicedto.domain.Foto;
import projeto_service_dto.servicedto.domain.Funcionario;
import projeto_service_dto.servicedto.dto.FuncionarioDTO;
import projeto_service_dto.servicedto.dto.FuncionarioSalarioDTO;
import projeto_service_dto.servicedto.repository.FuncionarioRepository;
import projeto_service_dto.servicedto.service.FotoService;
import projeto_service_dto.servicedto.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> listar() {
		return ResponseEntity.ok(funcionarioService.listar());
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscarPorIdFuncionario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, foto.getTipo());
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscar(@PathVariable Long id) {
		FuncionarioDTO dto = funcionarioService.buscar(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public FuncionarioDTO inserir(
			@RequestPart MultipartFile file, @RequestPart Funcionario funcionario
			) throws IOException {
		return funcionarioService.inserir(funcionario, file);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}*/
	@GetMapping("/paginado")
	public ResponseEntity<Page<Funcionario>> listaPaginado(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)
	Pageable pageable){
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		return ResponseEntity.ok(funcionarios);}
	
		
	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> listarSalarios(
			@RequestParam Double valorMinimo, @RequestParam Double valorMaximo, Pageable pageable) {
		Page<Funcionario> func = funcionarioRepository.buscarSalarioJPQL(valorMinimo, valorMaximo, pageable);
		return ResponseEntity.ok(func);
		
	}
	@GetMapping("/nome")
	public ResponseEntity<Page<Funcionario>> buscarPorNome(
			@RequestParam(defaultValue = "") String nome, 
			Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(nome, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salarios-por-idade")
	public ResponseEntity<List<FuncionarioSalarioDTO>> buscaSalariosPorIdade() {
		List<FuncionarioSalarioDTO> salarios = funcionarioRepository.buscaSalariosPorIdade();
		return ResponseEntity.ok(salarios);
	}
	}


