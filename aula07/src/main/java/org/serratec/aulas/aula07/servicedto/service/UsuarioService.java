package org.serratec.aulas.aula07.servicedto.service;

import org.serratec.aulas.aula07.servicedto.config.MailConfig;
import org.serratec.aulas.aula07.servicedto.domain.Perfil;
import org.serratec.aulas.aula07.servicedto.domain.Usuario;
import org.serratec.aulas.aula07.servicedto.domain.UsuarioPerfil;
import org.serratec.aulas.aula07.servicedto.dto.UsuarioDTO;
import org.serratec.aulas.aula07.servicedto.dto.UsuarioInserirDTO;
import org.serratec.aulas.aula07.servicedto.exception.EmailException;
import org.serratec.aulas.aula07.servicedto.exception.SenhaException;
import org.serratec.aulas.aula07.servicedto.repository.UsuarioPerfilRepository;
import org.serratec.aulas.aula07.servicedto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MailConfig mailConfig;

    public List<UsuarioDTO> buscarTodos() {
        UserDetails detais = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(detais.getUsername());
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDto = new ArrayList<>();
        for (Usuario u: usuarios) {
            UsuarioDTO uDto = new UsuarioDTO(u);
            usuariosDto.add(uDto);
        }
        return usuariosDto;
    }

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
        for (Perfil p: usuarioInserirDTO.getPerfis()) {
            p = perfilService.buscar(p.getId());
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, p, LocalDate.now());
            usuarioPerfis.add(usuarioPerfil);
        }

        usuario.setUsuarioPerfis(usuarioPerfis);

        usuario = usuarioRepository.save(usuario);//para gerar o id e registrar no repositorio

        mailConfig.sendEmail(usuario.getEmail(), "Cadastro de Usuário!", usuario.toString());

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return new UsuarioDTO(usuario);
    }
}
