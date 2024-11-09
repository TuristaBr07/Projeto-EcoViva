package com.seuprojeto.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_ecoviva"; // Substitua pelo nome do seu banco de dados
    private static final String USUARIO = "root"; // Substitua pelo seu usuário
    private static final String SENHA = "Fe47223552@"; // Substitua pela sua senha

    /**
     * Cria e retorna uma nova conexão com o banco de dados.
     * 
     * @return uma nova conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao conectar-se ao banco de dados
     */
    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Fecha a conexão fornecida.
     * 
     * @param conexao a conexão a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
