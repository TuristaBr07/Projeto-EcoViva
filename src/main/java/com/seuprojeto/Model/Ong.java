package com.seuprojeto.Model;

public class Ong {
    private int codigo;
    private String nomeOng;
    private String cnpj;
    private String emailOng;
    private String senhaOng;
    private String telefone;
    private String enderecoOng;

    // Construtor com validações básicas
    public Ong(String nomeOng, String cnpj, String emailOng) {
        setNomeOng(nomeOng);
        setCnpj(cnpj);
        setEmailOng(emailOng);
    }

    public String getNomeOng() {
        return nomeOng;
    }

    public void setNomeOng(String nomeOng) {
        if (nomeOng == null || nomeOng.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da ONG não pode ser vazio.");
        }
        this.nomeOng = nomeOng;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido. Deve conter 14 dígitos.");
        }
        this.cnpj = cnpj;
    }

    public String getEmailOng() {
        return emailOng;
    }

    public void setEmailOng(String emailOng) {
        if (emailOng == null || !emailOng.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.emailOng = emailOng;
    }

    public String getSenhaOng() {
        return senhaOng;
    }

    public void setSenhaOng(String senhaOng) {
        this.senhaOng = senhaOng; // Adicione hash se necessário
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEnderecoOng() {
        return enderecoOng;
    }

    public void setEnderecoOng(String enderecoOng) {
        this.enderecoOng = enderecoOng;
    }

    public int getCodigo() {
        return codigo;
    }

    // Método setCodigo(int) com visibilidade pública
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
