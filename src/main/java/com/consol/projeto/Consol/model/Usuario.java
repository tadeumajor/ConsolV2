package com.consol.projeto.Consol.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nomeMorador;

    @NotEmpty
    private String cpf;
    @NotEmpty
    @Column(unique = true)
    @Email(message = "Email inválido, gentileza conferir")
    private String email;
//    @NotBlank
//    @Size(min = 8, message = "Necessário no minimo oito (8) caracteres")
//    private String password;
    @NotEmpty
    private String telContato;
    @NotEmpty
    private String numAp;

    private boolean log;




    @ManyToOne
    @JoinColumn(name = "codigo_condominio")
    private Condominio condominio;

    public Usuario() {
    }

    public Usuario(String nomeMorador, String cpf, String email, String telContato, String numAp, boolean log, Condominio condominio) {
        this.nomeMorador = nomeMorador;
        this.cpf = cpf;
        this.email = email;
        //this.password = password;
        this.telContato = telContato;
        this.numAp = numAp;
        this.log = false;
        this.condominio = condominio;
    }
}