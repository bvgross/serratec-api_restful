package org.serratec.aulas.aula07.servicedto.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
        this.usuarioPerfis = usuarioPerfis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UsuarioPerfil usuarioPerfil: getUsuarioPerfis()) {
            authorities.add(new SimpleGrantedAuthority(usuarioPerfil.getId().getPerfil().getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", email='" + email + '\'' +
            ", senha='" + senha + '\'' +
            ", usuarioPerfis=" + usuarioPerfis.stream()
                .map(up -> up.getId().getPerfil().getNome())
                .collect(Collectors.joining(",")) +
            '}';
    }
}
