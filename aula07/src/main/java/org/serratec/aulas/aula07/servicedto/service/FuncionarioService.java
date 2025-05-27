package org.serratec.aulas.aula07.servicedto.service;

import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.serratec.aulas.aula07.servicedto.dto.FuncionarioDTO;
import org.serratec.aulas.aula07.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<FuncionarioDTO> funcionariosDTO = funcionarioRepository
            .findAll()
            .stream()
            .map(f -> createFuncionarioDTO(f))
            .collect(Collectors.toList());

        return funcionariosDTO;
    }

    public FuncionarioDTO buscar(Long id) {
        Optional<Funcionario> funcOpt = funcionarioRepository.findById(id);
        if (funcOpt.isPresent()) {
            return createFuncionarioDTO(funcOpt.get());
        }
        return null;
    }

    public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
        funcionario = funcionarioRepository.save(funcionario);
        fotoService.inserir(funcionario, file);
        return createFuncionarioDTO(funcionario);
    }
}
