package projeto_service_dto.servicedto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_service_dto.servicedto.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Optional<Endereco> findByCep(String cep);
	
}
