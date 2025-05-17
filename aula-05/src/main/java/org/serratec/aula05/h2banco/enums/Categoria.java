package org.serratec.aula05.h2banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.sql.results.graph.entity.internal.EntitySelectFetchByUniqueKeyInitializer;
import org.serratec.aula05.h2banco.exception.EnumException;

public enum Categoria {

    HATCH,
    SEDAN,
    PICAPE,
    SUV;

    @JsonCreator
    public static Categoria verifica(String value) throws EnumException {
        for(Categoria c: values()) {
            if (value.equals(c.name())) {
                return c;
            }
        }
        throw new EnumException("Categoria inserida corratamente");
    }
}

