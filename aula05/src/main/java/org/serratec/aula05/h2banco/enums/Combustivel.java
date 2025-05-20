package org.serratec.aula05.h2banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.aula05.h2banco.exception.EnumException;

public enum Combustivel {
    ALCOOL(1, "Alcool"),
    GASOLINA(2, "Gasolina"),
    DIESEL(3, "Diesel"),
    FLEX(4, "Flex");

    private Integer codigo;
    private String tipo;

    private Combustivel(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    @JsonCreator
    public static Combustivel verifica(Integer value) throws EnumException {
        for(Combustivel c: values()) {
            if (value.equals(c.getCodigo())) {
                return c;
            }
        }
        throw new EnumException("Combustível preenchido incorretamente!");
    }
}
