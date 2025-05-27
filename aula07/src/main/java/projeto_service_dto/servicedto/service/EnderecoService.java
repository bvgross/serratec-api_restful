package projeto_service_dto.servicedto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import projeto_service_dto.servicedto.domain.Endereco;
import projeto_service_dto.servicedto.dto.EnderecoDTO;
import projeto_service_dto.servicedto.repository.EnderecoRepository;

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
			// buscando na API Externa (viacep)
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCepOpt = Optional.ofNullable(
					restTemplate.getForObject(url, Endereco.class));
			if (enderecoViaCepOpt.isPresent() && enderecoViaCepOpt.get().getCep() != null) {
				Endereco enderecoViaCep = enderecoViaCepOpt.get();
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

