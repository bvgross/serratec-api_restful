package projeto_service_dto.servicedto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import projeto_service_dto.servicedto.config.MailConfig;
import projeto_service_dto.servicedto.domain.Perfil;
import projeto_service_dto.servicedto.domain.Usuario;
import projeto_service_dto.servicedto.domain.UsuarioPerfil;
import projeto_service_dto.servicedto.dto.UsuarioDTO;
import projeto_service_dto.servicedto.dto.UsuarioInserirDTO;
import projeto_service_dto.servicedto.exception.EmailException;
import projeto_service_dto.servicedto.exception.SenhaException;
import projeto_service_dto.servicedto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MailConfig mailconfig;
	
	public List<UsuarioDTO> buscarTodos() {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		System.out.println(details.getUsername());
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for(Usuario usuario: usuarios) {
			UsuarioDTO usuDTO = new UsuarioDTO(usuario);
			usuariosDTO.add(usuDTO);
		}
		return usuariosDTO;
	}
	
	//@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		if(!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já cadastrado");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		
		Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
		for(Perfil perfil: usuarioInserirDTO.getPerfis()) {
			perfil = perfilService.buscar(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
			usuarioPerfis.add(usuarioPerfil);
		}
		
		usuario.setUsuarioPerfis(usuarioPerfis);
		
		usuario = usuarioRepository.save(usuario);
		
		mailconfig.sendEmail(usuario.getEmail(), "Cadastro de Usuario!", usuario.toString());
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}

}