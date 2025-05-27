package projeto_service_dto.servicedto.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import projeto_service_dto.servicedto.domain.Foto;
import projeto_service_dto.servicedto.domain.Funcionario;
import projeto_service_dto.servicedto.repository.FotoRepository;

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
