package org.serratec.exercicios.exercicio05.respositories;

import org.serratec.exercicios.exercicio05.domain.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

}
