package projeto_service_dto.servicedto.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_service_dto.servicedto.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
