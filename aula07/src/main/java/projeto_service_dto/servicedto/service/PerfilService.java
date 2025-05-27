package projeto_service_dto.servicedto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto_service_dto.servicedto.domain.Perfil;
import projeto_service_dto.servicedto.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public Perfil buscar(Long id) {
		Optional<Perfil> perfilOpt = perfilRepository.findById(id);
		return perfilOpt.get();
	}
	
}
