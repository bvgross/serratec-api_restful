package org.serratec.aulas.aula07.servicedto.repository;

import org.serratec.aulas.aula07.servicedto.domain.Funcionario;
import org.serratec.aulas.aula07.servicedto.dto.FuncionarioSalarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    //SQL
    @Query(value = "SELECT * FROM Funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo",
        nativeQuery = true)
    Page<Funcionario> buscarSalarioSQL(Double valorMinimo, Double valorMaximo, Pageable pageable);

    // JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo")
    Page<Funcionario> buscarSalarioJPQL(Double valorMinimo, Double valorMaximo, Pageable pageable);

    Page<Funcionario> findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);

    @Query("SELECT f FROm Funcionario f WHERE UPPER(f.nome) like UPPER(CONCAT('%', :nome, '%'))")
    Page<Funcionario> buscarPorNome(String nome, Pageable pageable);

    Page<Funcionario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query(value = """
        SELECT date_part('year', age(now(), data_nascimento)) as idade,
            avg(salario) as mediaSalario,
            min(salario) as menorSalario,
            max(salario) as maiorSalario,
            count(*) as totalFuncionarios
        FROM
            funcionario
        GROUP BY
            idade
        HAVING
            count(*) > 1
        ORDER BY idade desc
        """, nativeQuery = true)
    List<FuncionarioSalarioDTO> buscaSalariosPorIdade();

}