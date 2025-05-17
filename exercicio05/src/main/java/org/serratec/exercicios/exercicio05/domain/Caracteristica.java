package org.serratec.exercicios.exercicio05.domain;

import jakarta.persistence.Embeddable;
import org.serratec.exercicios.exercicio05.enums.Categoria;
import org.serratec.exercicios.exercicio05.enums.Combustivel;

@Embeddable
public class Caracteristica {

    private String renavam;
    private Integer ano;
    private Categoria categoria;
    private Combustivel combustivel;

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }
}
