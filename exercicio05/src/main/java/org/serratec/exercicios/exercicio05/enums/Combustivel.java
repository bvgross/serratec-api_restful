package org.serratec.exercicios.exercicio05.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.serratec.exercicios.exercicio05.exceptions.EnumValidationException;

public enum Combustivel {
    ALCOOL(1, "álcool"),
    GASOLINA(2, "gasolina"),
    FLEX(3, "flex"),
    DIESEL(4, "diesel");

    private Integer codigo;
    private String tipo;

    Combustivel(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    @JsonValue
    public String getTipo() {
        return tipo;
    }

    @JsonCreator
    public static Combustivel verificar(Object valor) throws EnumValidationException {
        if (valor instanceof Integer) {
            for (Combustivel c: values()) {
                if (valor.equals(c.getCodigo())) {
                    return c;
                }
            }
            throw new EnumValidationException("Código de combustível inválido. " +
                "Códigos válidos são 1(álcool), 2(gasolina), 3(flex), 4(diesel)");
        }
        throw new EnumValidationException("Dado inserido inválido. Deve ser inserido um número inteiro." +
            "Códigos válidos são 1(álcool), 2(gasolina), 3(flex), 4(diesel)");
    }
}
