package projeto_service_dto.servicedto.dto;

import projeto_service_dto.servicedto.domain.Endereco;

public class EnderecoDTO {

	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String uf;
	private Long ibge;

	public EnderecoDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.uf = endereco.getUf();
		this.ibge = endereco.getIbge();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getIbge() {
		return ibge;
	}

	public void setIbge(Long ibge) {
		this.ibge = ibge;
	}

}

