package org.serratec.exercicios.exercicio05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PessoaFisica extends Fornecedor{
    @Column
    private String rg;

    @Column
    private String orgaoEmissor;

    @Column
    private String cpg;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getCpg() {
        return cpg;
    }

    public void setCpg(String cpg) {
        this.cpg = cpg;
    }
}
