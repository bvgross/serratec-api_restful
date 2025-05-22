package org.serratec.aulas.aula07.servicedto.service;

import org.serratec.aulas.aula07.servicedto.domain.Perfil;
import org.serratec.aulas.aula07.servicedto.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil buscar(Long id) {
        Optional<Perfil> perfilOpt = perfilRepository.findById(id);
        return perfilOpt.get();
    }
}
