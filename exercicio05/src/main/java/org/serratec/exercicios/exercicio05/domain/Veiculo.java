package org.serratec.exercicios.exercicio05.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(schema = "exercicio05", name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Não preencheu a placa. Preencha!!!!!")
    @Size(max = 7)
    @Column(nullable = false, length = 7)
    private String placa;

    @Size(max = 30)
    @NotBlank(message = "Não preencheu a marca. Preencha!!!!!")
    @Column(nullable = false, length = 30)
    private String marca;

    @Size(max = 40)
    @NotBlank(message = "Não preencheu o modelo. Preencha!!!!!")
    @Column(nullable = false, length = 40)
    private String modelo;

    @Embedded
    private Caracteristica caracteristica;

    @OneToOne
    @JoinColumn(name = "id_proprietario")
    private Proprietario proprietario;

    @JsonManagedReference
    @OneToMany(mappedBy = "veiculo")
    private List<Manutencao> manutencoes;

    public Veiculo(String placa, String marca, String modelo, Caracteristica caracteristica) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristica = caracteristica;
    }

    public Veiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }
}
