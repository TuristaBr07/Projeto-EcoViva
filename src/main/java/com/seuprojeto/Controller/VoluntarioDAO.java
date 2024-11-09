package com.seuprojeto.Controller;

import com.seuprojeto.Model.Banco;
import com.seuprojeto.Model.Voluntario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {
    private Banco banco;

    public VoluntarioDAO() {
        banco = new Banco(); // Inicializa a conexão com o banco de dados
    }

    public boolean existeEmail(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM voluntario WHERE email = ?";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public int gravar(Voluntario voluntario) throws SQLException {
        if (existeEmail(voluntario.getEmail())) {
            throw new SQLException("Email já cadastrado: " + voluntario.getEmail());
        }
        String sql = "INSERT INTO voluntario (nome, cpf, email) VALUES (?, ?, ?)";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, voluntario.getNome());
            stmt.setString(2, voluntario.getCpf());
            stmt.setString(3, voluntario.getEmail());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        voluntario.setCodigo(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        }
    }

    public List<Voluntario> listar() throws SQLException {
        List<Voluntario> voluntarios = new ArrayList<>();
        String sql = "SELECT * FROM voluntario";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Voluntario voluntario = new Voluntario(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );
                voluntario.setCodigo(rs.getInt("codigo"));
                voluntarios.add(voluntario);
            }
        }
        return voluntarios;
    }

    public int remover(Voluntario voluntario) throws SQLException {
        String sql = "DELETE FROM voluntario WHERE codigo = ?";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, voluntario.getCodigo());
            return stmt.executeUpdate();
        }
    }
}
