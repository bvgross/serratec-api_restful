package org.serratec.exercicio_03.veiculo.repository;

import org.serratec.exercicio_03.veiculo.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
