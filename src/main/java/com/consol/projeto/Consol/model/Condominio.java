package com.consol.projeto.Consol.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Condominio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String rua;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String numero;

    private String cep;

    private double consumoMedioMesKwh;

    private int qtdPaineis;

    private double valorPaineis;

    private double valorAproximado;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_condominio")
    private List<Usuario> moradores;

    public Condominio(String nome, String rua, String bairro, String numero, String cep, double consumoMedioMesKwh, int qtdPaineis, double valorPaineis, double valorAproximado, List<Usuario> moradores) {
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.consumoMedioMesKwh = consumoMedioMesKwh;
        this.qtdPaineis = qtdPaineis;
        this.valorPaineis = valorPaineis;
        this.valorAproximado = valorAproximado;
        this.moradores = moradores;
    }

    public Condominio() {
    }


}
