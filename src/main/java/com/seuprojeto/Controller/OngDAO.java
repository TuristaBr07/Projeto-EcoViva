package com.seuprojeto.Controller;

import com.seuprojeto.Model.Banco;
import com.seuprojeto.Model.Ong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OngDAO {
    private Banco banco;

    public OngDAO() {
        banco = new Banco(); // Inicializa a conexão com o banco de dados
    }

    public int gravar(Ong ong) throws SQLException {
        // Verifica se a ONG já existe pelo CNPJ
        if (existsByCnpj(ong.getCnpj())) {
            throw new SQLException("CNPJ já cadastrado: " + ong.getCnpj());
        }

        String sql = "INSERT INTO ong (nomeOng, cnpj, emailOng) VALUES (?, ?, ?)";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ong.getNomeOng());
            stmt.setString(2, ong.getCnpj());
            stmt.setString(3, ong.getEmailOng());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ong.setCodigo(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        }
    }

    // Método para verificar se o CNPJ já existe
    private boolean existsByCnpj(String cnpj) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ong WHERE cnpj = ?";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o CNPJ já existir
            }
        }
        return false; // Retorna false se o CNPJ não existir
    }

    public List<Ong> listar() throws SQLException {
        List<Ong> ongs = new ArrayList<>();
        String sql = "SELECT * FROM ong";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ong ong = new Ong(
                        rs.getString("nomeOng"), // Altere para o nome correto da coluna
                        rs.getString("cnpj"),
                        rs.getString("emailOng")
                );
                ong.setCodigo(rs.getInt("codigo"));
                ongs.add(ong);
            }
        }
        return ongs;
    }

    public int remover(Ong ong) throws SQLException {
        String sql = "DELETE FROM ong WHERE codigo = ?";
        try (Connection conn = banco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ong.getCodigo());
            return stmt.executeUpdate();
        }
    }
}
