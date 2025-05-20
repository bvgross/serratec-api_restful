package org.serratec.exercicio_03.veiculo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Preencha a descrição")
    @Size(max = 40, message = "Tamanho máximo 40 carecteres")
    @Column(name = "descricao", nullable = false, length = 40)
    private String descricao;

    @DecimalMax(value="5000", message = "O preço não pode ser maior que R${value},00")
    @DecimalMin(value="5", message = "O preço não pode ser menor que R${value},00")
    @Column
    private BigDecimal valor;

    @Column(name = "data_cadastro")
    private String dataCadastro;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
