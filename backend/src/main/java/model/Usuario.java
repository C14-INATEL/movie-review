package com.projeto.model;

public class Usuario {

    private Long id;
    private String nome;
    private String email;

    // Construtor vazio
    public Usuario() {
    }

    // Construtor completo
    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getter e Setter do id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter do nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter do email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}