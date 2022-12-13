package com.consol.projeto.Consol.model;

public enum Estado {

    AC("Acre"),
    AL("Alagoas"),
    AP("Amapa"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceara"),
    DF("Distrito Federal"),
    ES("Espirito Santo"),
    GO("Goias"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Para"),
    PB("Paraiba"),
    PR("Parana"),
    PE("Pernanbuco"),
    PI("Piaui"),
    RN("Rio Grande de Norte"),
    RS("Rio Grande do Sul"),
    RJ("Rio de Janeiro"),
    RO("Rondonia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("Sao Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    private String descricao;

    Estado(String descricao){
        this.descricao = descricao;
    }

    Estado() {
    }

    public String getDescricao() {
        return descricao;
    }

}
