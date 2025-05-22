package org.serratec.aulas.aula07.servicedto.repository;

import org.serratec.aulas.aula07.servicedto.domain.UsuarioPerfil;
import org.serratec.aulas.aula07.servicedto.domain.UsuarioPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}
