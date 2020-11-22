package ProjetoCadastroTurma.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexaoFactory {

    //CONNECTION WITH DRIVERMANAGER
    public static Connection getConexao() {

        String url = "jdbc:mysql://localhost:3306/agencia?useTimezone=true&serverTimezone=UTC"; //string que permitir qual conector ele vai utilizar
        String user = "root"; //
        String password = "root123";

        try {
            // class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 

    //Devemos sempre fechar a conexao
    public static void close(Connection connection) {
        try {
            if (connection != null) 
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection connection, Statement stmt) {
        close(connection);
        try {
            if (stmt != null) 
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement stmt, ResultSet rs) {
        close(connection, stmt);
        try {
            if (rs != null) 
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     
}
