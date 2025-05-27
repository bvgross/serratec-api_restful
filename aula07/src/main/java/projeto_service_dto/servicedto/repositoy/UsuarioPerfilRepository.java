package projeto_service_dto.servicedto.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto_service_dto.servicedto.domain.UsuarioPerfil;
import projeto_service_dto.servicedto.domain.UsuarioPerfilPK;

@Repository
public interface UsuarioPerfilRepository  extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK>{

}
