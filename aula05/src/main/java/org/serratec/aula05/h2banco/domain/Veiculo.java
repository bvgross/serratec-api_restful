package org.serratec.aula05.h2banco.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Preencha a placa")
    @Size(max = 7)
    @Column(nullable = false, length = 7)
    private String placa;

    @NotBlank(message = "Preencha a marca")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String marca;

    @NotBlank(message = "Preencha a modelo")
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String modelo;

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    @Embedded
    private Caracteristica caracteristica;

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
}
