package org.serratec.aulas.aula07.servicedto.service;

import org.serratec.aulas.aula07.servicedto.domain.Usuario;
import org.serratec.aulas.aula07.servicedto.exception.EmailException;
import org.serratec.aulas.aula07.servicedto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario inserir(Usuario user) throws EmailException {
        Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
        if (usuario != null) {
            throw new EmailException("Email já cadastrado");
        }
        return usuarioRepository.save(user);
    }
}
