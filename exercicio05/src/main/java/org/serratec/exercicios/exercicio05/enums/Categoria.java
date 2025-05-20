package org.serratec.exercicios.exercicio05.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.serratec.exercicios.exercicio05.exceptions.EnumValidationException;

public enum Categoria {
    SEDAN(1, "sedan"),
    PICAPE(2, "picape"),
    HATCH(3, "hatch"),
    SUV(4, "suv");

    private String categoria;
    private Integer codigo;

    Categoria(Integer codigo, String categoria) {
        this.categoria = categoria;
        this.codigo = codigo;
    }

    @JsonValue
    public String getCategoria() {
        return categoria;
    }

    public Integer getCodigo() {
        return codigo;
    }

    @JsonCreator
    public static Categoria verificar(Object valor) throws EnumValidationException {
        if (valor instanceof String) {
            for (Categoria c: values()) {
                if (valor.equals(c.name())) {
                    return c;
                }
            }
            throw new EnumValidationException("Categoria inválida, categorias válidas são SEDAN, PICAPE, HATCH ou SUV.");
        }
        throw new EnumValidationException("Dado inserido deve ser uma String (SEDAN, PICAPE, HATCH ou SUV).");
    }
}
