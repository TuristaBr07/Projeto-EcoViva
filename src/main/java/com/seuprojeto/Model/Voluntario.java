package com.seuprojeto.Model;

public class Voluntario {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;

    // Construtor que aceita nome, cpf e email
    public Voluntario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Construtor sem parâmetros (opcional)
    public Voluntario() {
        // Inicializa valores padrão, se necessário
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
