package com.seuprojeto.Controller;

import com.seuprojeto.Model.Banco;
import com.seuprojeto.Model.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Banco banco;

    public ProjetoDAO() {
        banco = new Banco(); // Inicializa a conexão com o banco de dados
    }

    // Método para gravar um projeto no banco de dados
    public int gravar(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projeto (nomeProjeto, ong, data, horario, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getOng());
            stmt.setDate(3, Date.valueOf(projeto.getData()));
            stmt.setTime(4, Time.valueOf(projeto.getHorario()));
            stmt.setString(5, projeto.getEndereco());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        projeto.setCodigo(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        }
    }

    // Método para listar todos os projetos
    public List<Projeto> listar() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto(
                        rs.getString("nomeProjeto"),
                        rs.getString("ong"),
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("horario").toLocalTime(),
                        rs.getString("endereco")
                );
                projeto.setCodigo(rs.getInt("codigo"));
                projetos.add(projeto);
            }
        }
        return projetos;
    }

    // Método para remover um projeto
    public int remover(Projeto projeto) throws SQLException {
        String sql = "DELETE FROM projeto WHERE codigo = ?";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projeto.getCodigo());
            return stmt.executeUpdate();
        }
    }
}
