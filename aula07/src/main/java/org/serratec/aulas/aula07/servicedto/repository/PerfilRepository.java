package org.serratec.aulas.aula07.servicedto.repository;

import org.serratec.aulas.aula07.servicedto.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
