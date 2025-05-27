package org.serratec.aulas.aula07.servicedto.service;

import jakarta.transaction.Transactional;
import org.serratec.aulas.aula07.servicedto.domain.Foto;
import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.serratec.aulas.aula07.servicedto.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
        Foto foto = new Foto();
        foto.setNome(file.getName());
        foto.setTipo(file.getContentType());
        foto.setDados(file.getBytes());
        foto.setFuncionario(funcionario);

        foto = fotoRepository.save(foto);

        return foto;
    }

    @Transactional
    public Foto buscarPorIdFuncionario(Long funcionarioId) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);

        Optional<Foto> fotoOpt = fotoRepository.findByFuncionario(funcionario);
        if (fotoOpt.isEmpty()) {
            return null;
        }
        return fotoOpt.get();
    }
}
