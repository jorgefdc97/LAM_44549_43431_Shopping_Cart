package com.example.lam_44549_43431_shopping_cart;

public class Contact {
    private int id;
    private String nome;
    private String morada;

    public Contact(int id, String nome, String morada) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
    }

    public Contact() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getId() {
        return id;
    }

    public String getMorada() {
        return morada;
    }

    public String getNome() {
        return nome;
    }
}
