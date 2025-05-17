package org.serratec.exercicios.exercicio05.respositories;

import org.serratec.exercicios.exercicio05.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
