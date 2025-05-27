package org.serratec.aulas.aula07.servicedto.repository;

import org.serratec.aulas.aula07.servicedto.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    public Optional<Endereco> findByCep(String cep);
}
