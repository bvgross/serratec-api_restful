package org.serratec.exercicios.exercicio05.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.serratec.exercicios.exercicio05.enums.Combustivel;

@Converter
public class CombustivelConverter implements AttributeConverter<Combustivel, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Combustivel combustivel) {
        return combustivel != null ? combustivel.getCodigo() : null;
    }

    @Override
    public Combustivel convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Combustivel c: Combustivel.values()) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Código inválido para combustível: " + codigo);
    }
}
