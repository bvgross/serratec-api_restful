package org.serratec.exercicios.exercicio05.enums;

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

    public String getTipo() {
        return tipo;
    }
}
