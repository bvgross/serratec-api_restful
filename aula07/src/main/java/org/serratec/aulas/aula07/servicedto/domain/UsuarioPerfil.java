package org.serratec.aulas.aula07.servicedto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilPK id = new UsuarioPerfilPK();

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    public UsuarioPerfil() {}

    public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao) {
        this.id.setUsuario(usuario);
        this.id.setPerfil(perfil);
        this.dataCriacao = dataCriacao;
    }

    public UsuarioPerfilPK getId() {
        return id;
    }

    public void setId(UsuarioPerfilPK id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.id.setUsuario(usuario);
    }

    public void setPerfil(Perfil perfil) {
        this.id.setPerfil(perfil);
    }
}
