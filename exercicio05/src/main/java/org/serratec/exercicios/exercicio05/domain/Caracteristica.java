package org.serratec.exercicios.exercicio05.domain;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.serratec.exercicios.exercicio05.enums.Categoria;
import org.serratec.exercicios.exercicio05.enums.Combustivel;
import org.serratec.exercicios.exercicio05.utils.CombustivelConverter;

@Embeddable
public class Caracteristica {

    private String renavam;
    private Integer ano;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Convert(converter = CombustivelConverter.class)
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
