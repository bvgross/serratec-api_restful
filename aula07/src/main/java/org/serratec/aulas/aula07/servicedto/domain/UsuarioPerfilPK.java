package org.serratec.aulas.aula07.servicedto.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioPerfilPK implements Serializable {

    private static final long SerialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPerfilPK that = (UsuarioPerfilPK) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(perfil, that.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, perfil);
    }
}
