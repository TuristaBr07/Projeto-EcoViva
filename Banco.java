
package Model;

import java.sql.*;

// Conexão com o banco
public class Banco {
    public static Connection conexao; // Static para não repetir conexao
    public PreparedStatement comando; // Envia sql para o banco
    public ResultSet tabela; // Armazena os dados que vem do banco

    public Banco() throws Exception {

        try {
            // Regitra o driver, informa que vai utilizá-lo
            Class.forName("com.mysql.jdbc.Driver"); // Para MySql
            if ((conexao == null) || (conexao.isClosed())) {
                conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lpb", "root", "");
            }

        } catch (Exception ex) {
            throw new Exception("Erro na conexão com o banco: " + ex.getMessage());
        }
    }

}
