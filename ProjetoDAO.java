package Controller;

import Model.Projeto;
import Model.Banco;
import java.sql.ResultSet;

public class ProjetoDAO {

    public int gravar(Projeto obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement(
                            "Insert into projeto(nomeProjeto, ong, data, horario, endereco) values(?, ?, ?, ?, ?);");
            bb.comando.setString(1, obj.getNomeProjeto());
            bb.comando.setString(2, obj.getOng());
            bb.comando.setDate(3, obj.getData());
            bb.comando.setString(4, obj.getHorario());
            bb.comando.setString(5, obj.getEndereco());

            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar: " + ex.getMessage());
        }
    }

    public int remover(Projeto obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao.prepareStatement("Delete from projeto where codigo = ?");
            bb.comando.setInt(1, obj.getCodigo());
            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao remover: " + ex.getMessage());
        }
    }

    public int alterar(Projeto obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement(
                            "Update projeto set nomeProjeto=?, ong=?, data=?, horario=?, endereco=? where codigo=?;");
            bb.comando.setString(1, obj.getNomeProjeto());
            bb.comando.setString(2, obj.getOng());
            bb.comando.setDate(3, obj.getData());
            bb.comando.setString(4, obj.getHorario());
            bb.comando.setString(5, obj.getEndereco());
            bb.comando.setInt(6, obj.getCodigo());

            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao alterar: " + ex.getMessage());
        }
    }

    public ResultSet listar() throws Exception {
        Banco bb;
        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement(
                            "Select codigo, nomeProjeto, ong, data, horario, endereco from projeto order by 2;");
            bb.tabela = bb.comando.executeQuery();
            Banco.conexao.close();
            return (bb.tabela);
        } catch (Exception ex) {
            throw new Exception("Erro ao listar: " + ex.getMessage());
        }
    }
}
