package org.serratec.aulas.aula07.servicedto.repository;

import org.serratec.aulas.aula07.servicedto.domain.Foto;
import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {

    public Optional<Foto> findByFuncionario(Funcionario funcionario);
}
