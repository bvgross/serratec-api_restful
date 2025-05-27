package projeto_service_dto.servicedto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_service_dto.servicedto.domain.Foto;
import projeto_service_dto.servicedto.domain.Funcionario;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long>{
	
	public Optional<Foto> findByFuncionario(Funcionario funcionario);

}
