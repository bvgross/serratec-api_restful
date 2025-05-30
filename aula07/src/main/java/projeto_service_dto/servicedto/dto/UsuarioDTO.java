package projeto_service_dto.servicedto.dto;

import java.util.HashSet;
import java.util.Set;

import projeto_service_dto.servicedto.domain.Perfil;
import projeto_service_dto.servicedto.domain.Usuario;
import projeto_service_dto.servicedto.domain.UsuarioPerfil;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private Set<Perfil> perfis;
	
	public UsuarioDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.perfis = new HashSet<>();
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfis = new HashSet<>();
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPerfis()) {
			this.perfis.add(usuarioPerfil.getId().getPerfil());
		}
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

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
	
}
