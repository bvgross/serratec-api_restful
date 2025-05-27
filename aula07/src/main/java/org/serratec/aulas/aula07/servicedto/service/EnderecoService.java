package org.serratec.aulas.aula07.servicedto.service;

import org.serratec.aulas.aula07.servicedto.domain.Endereco;
import org.serratec.aulas.aula07.servicedto.dto.EnderecoDTO;
import org.serratec.aulas.aula07.servicedto.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO buscar(String cep) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findByCep(cep);
        if (enderecoOpt.isPresent()) {
            EnderecoDTO dto = new EnderecoDTO(enderecoOpt.get());
            return dto;
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://viacep.com.br/ws/" + cep + "/json/";
            Optional<Endereco> enderecoViaCapOtp = Optional.ofNullable(restTemplate.getForObject(url, Endereco.class));
            if (enderecoViaCapOtp.isPresent() && enderecoViaCapOtp.get().getCep() != null) {
                Endereco enderecoViaCep = enderecoViaCapOtp.get();
                String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
                enderecoViaCep.setCep(cepSemTraco);
                return inserir(enderecoViaCep);
            } else {
                return null;
            }
        }
    }

    public EnderecoDTO inserir(Endereco endereco) {
        endereco = enderecoRepository.save(endereco);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        return enderecoDTO;
    }
}