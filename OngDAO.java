package Controller;

import Model.Ong;
import Model.Banco;
import java.sql.ResultSet;

public class OngDAO {

    public int gravar(Ong obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement(
                            "Insert into ong(nomeOng, cnpj, emailOng, senhaOng, telefone, enderecoOng) values(?, ?, ?, ?, ?, ?);");
            bb.comando.setString(1, obj.getNomeOng());
            bb.comando.setString(2, obj.getCnpj());
            bb.comando.setString(3, obj.getEmailOng());
            bb.comando.setString(4, obj.getSenhaOng());
            bb.comando.setString(5, obj.getTelefone());
            bb.comando.setString(6, obj.getEnderecoOng());

            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar: " + ex.getMessage());
        }
    }

    public int remover(Ong obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao.prepareStatement("Delete from ong where codigo = ?");
            bb.comando.setInt(1, obj.getCodigo());

            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao remover: " + ex.getMessage());
        }
    }

    public int alterar(Ong obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement(
                            "Update ong set nomeOng=?, cnpj=?, emailOng=?, senhaOng=?, telefone=?, enderecoOng=? where codigo=?;");
            bb.comando.setString(1, obj.getNomeOng());
            bb.comando.setString(2, obj.getCnpj());
            bb.comando.setString(3, obj.getEmailOng());
            bb.comando.setString(4, obj.getSenhaOng());
            bb.comando.setString(5, obj.getTelefone());
            bb.comando.setString(6, obj.getEnderecoOng());
            bb.comando.setInt(7, obj.getCodigo());

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
                            "Select codigo, nomeOng, cnpj, emailOng, senhaOng, telefone, enderecoOng from ong order by 2;");
            bb.tabela = bb.comando.executeQuery();
            Banco.conexao.close();
            return (bb.tabela);
        } catch (Exception ex) {
            throw new Exception("Erro ao listar: " + ex.getMessage());
        }
    }
}
