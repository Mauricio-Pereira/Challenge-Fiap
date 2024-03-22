package org.fiap.entities;

import java.util.regex.Pattern;

public abstract class Endereco {
    private static final Pattern CepPattern = Pattern.compile("^\\d{5}-\\d{3}");
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String pais) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Endereco {\n" +
                "\tCEP: '" + cep + "',\n" +
                "\tLogradouro: '" + logradouro + "',\n" +
                "\tNúmero: '" + numero + "',\n" +
                "\tComplemento: '" + (complemento != null ? complemento : "") + "',\n" +
                "\tBairro: '" + bairro + "',\n" +
                "\tCidade: '" + cidade + "',\n" +
                "\tEstado: '" + estado + "',\n" +
                "\tPaís: '" + pais + "'\n" +
                "}";
    }
}
