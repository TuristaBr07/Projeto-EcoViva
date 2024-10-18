package Controller;

import Model.Voluntario;
import Model.Banco;
import java.sql.ResultSet;

public class VoluntarioDAO {

    public int gravar(Voluntario obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement("Insert into voluntario(nome, email, senha, cpf) values(?, ?, ?, ?);");
            bb.comando.setString(1, obj.getNome());
            bb.comando.setString(2, obj.getEmail());
            bb.comando.setString(3, obj.getSenha());
            bb.comando.setString(4, obj.getCpf());

            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar: " + ex.getMessage());
        }
    }

    public int remover(Voluntario obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao.prepareStatement("Delete from voluntario where codigo = ?");
            bb.comando.setInt(1, obj.getCodigo());
            qtde = bb.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao remover: " + ex.getMessage());
        }
    }

    public int alterar(Voluntario obj) throws Exception {
        Banco bb;
        int qtde = 0;

        try {
            bb = new Banco();
            bb.comando = Banco.conexao
                    .prepareStatement("Update voluntario set nome=?, email=?, senha=?, cpf=? where codigo=?;");
            bb.comando.setString(1, obj.getNome());
            bb.comando.setString(2, obj.getEmail());
            bb.comando.setString(3, obj.getSenha());
            bb.comando.setString(4, obj.getCpf());
            bb.comando.setInt(5, obj.getCodigo());

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
                    .prepareStatement("Select codigo, nome, email, senha, cpf from voluntario order by 2;");
            bb.tabela = bb.comando.executeQuery();
            Banco.conexao.close();
            return (bb.tabela);
        } catch (Exception ex) {
            throw new Exception("Erro ao listar: " + ex.getMessage());
        }
    }
}
