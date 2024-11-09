package com.seuprojeto;

import com.seuprojeto.Controller.OngDAO;
import com.seuprojeto.Controller.VoluntarioDAO;
import com.seuprojeto.Controller.ProjetoDAO;
import com.seuprojeto.Model.Ong;
import com.seuprojeto.Model.Voluntario;
import com.seuprojeto.Model.Projeto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criando Controladores de BD
        OngDAO ongDAO = new OngDAO();
        VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO(); // Adicionando o controlador de projetos

        // Testando instâncias de Voluntário e ONG
        Ong semearLuz = new Ong("Semear Luz", "97549554000184", "semearluz@gmail.com");
        Voluntario voluntario1 = new Voluntario("João Silva", "12345678901", "joao@gmail.com");

        // ADICIONANDO ONG
        try {
            int inseridos = ongDAO.gravar(semearLuz);
            System.out.println("ONG inserida, linhas afetadas: " + inseridos);
        } catch (Exception e) {
            System.err.println("Erro ao gravar ONG: " + e.getMessage());
        }

        // ADICIONANDO VOLUNTÁRIO
        try {
            int voluntarioInseridos = voluntarioDAO.gravar(voluntario1);
            System.out.println("Voluntário inserido, linhas afetadas: " + voluntarioInseridos);
        } catch (Exception e) {
            System.err.println("Erro ao gravar Voluntário: " + e.getMessage());
        }

        // Exemplo de Listagem de ONGs
        try {
            List<Ong> listaOngs = ongDAO.listar();
            System.out.println("Lista de ONGs:");
            for (Ong ong : listaOngs) {
                System.out.println("Código: " + ong.getCodigo() +
                                   ", Nome: " + ong.getNomeOng() +
                                   ", CNPJ: " + ong.getCnpj() +
                                   ", Email: " + ong.getEmailOng());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar ONGs: " + e.getMessage());
        }

        // Exemplo de Listagem de Voluntários
        try {
            List<Voluntario> listaVoluntarios = voluntarioDAO.listar();
            System.out.println("Lista de Voluntários:");
            for (Voluntario voluntario : listaVoluntarios) {
                System.out.println("Código: " + voluntario.getCodigo() +
                                   ", Nome: " + voluntario.getNome() +
                                   ", CPF: " + voluntario.getCpf() +
                                   ", Email: " + voluntario.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar Voluntários: " + e.getMessage());
        }

        // Cadastro de Projeto
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Deseja cadastrar um projeto? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                try {
                    System.out.println("Digite o nome do projeto:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o nome da ONG associada:");
                    String ong = scanner.nextLine();

                    System.out.println("Digite a data do projeto (formato yyyy-mm-dd):");
                    LocalDate data = LocalDate.parse(scanner.nextLine());

                    System.out.println("Digite o horário do projeto (formato HH:mm:ss):");
                    LocalTime horario = LocalTime.parse(scanner.nextLine());

                    System.out.println("Digite o endereço do projeto:");
                    String endereco = scanner.nextLine();

                    // Criando e gravando o projeto
                    Projeto novoProjeto = new Projeto(nome, ong, data, horario, endereco);
                    int projetosInseridos = projetoDAO.gravar(novoProjeto);

                    if (projetosInseridos > 0) {
                        System.out.println("Projeto cadastrado com sucesso.");
                    } else {
                        System.out.println("Erro ao cadastrar o projeto.");
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao cadastrar projeto: " + e.getMessage());
                }
            }
        } finally {
            // Fechando o Scanner para evitar vazamento de recursos
            scanner.close();
        }
    }
}
