package com.seuprojeto.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Projeto {
    private int codigo; // ID do projeto
    private String nome;
    private String ong;
    private LocalDate data;
    private LocalTime horario;
    private String endereco;

    // Construtor completo
    public Projeto(String nome, String ong, LocalDate data, LocalTime horario, String endereco) {
        this.nome = nome;
        this.ong = ong;
        this.data = data;
        this.horario = horario;
        this.endereco = endereco;
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

    public String getOng() {
        return ong;
    }

    public void setOng(String ong) {
        this.ong = ong;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
