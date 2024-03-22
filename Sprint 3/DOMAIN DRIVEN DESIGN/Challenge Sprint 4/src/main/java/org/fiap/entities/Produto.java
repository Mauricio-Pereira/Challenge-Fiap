package org.fiap.entities;

import java.util.List;

public class Produto extends _BaseEntity{
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto() {
    }

    public Produto(int id, String nome, String descricao, double preco, int estoque) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto {\n" +
                "\tNome: '" + nome + "',\n" +
                "\tDescrição: '" + descricao + "',\n" +
                "\tPreço: " + preco + ",\n" +
                "\tEstoque: " + estoque + ",\n" +
                "\t" + super.toString() + "\n" +
                "}";
    }
}
