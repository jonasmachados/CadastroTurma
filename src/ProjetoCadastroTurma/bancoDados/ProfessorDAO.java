
package ProjetoCadastroTurma.bancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ProjetoCadastroTurma.classe.Professor;
import ProjetoCadastroTurma.conn.ConexaoFactory;


public class ProfessorDAO {
    
        public static void save(Professor professor) {
        String sql = "INSERT INTO `escola`.`professor` (nome, titulacao) VALUES (?, ?)";//Value esta em preparStatment

        //Esta criando a conexao
        try (Connection obtendoConexao = ConexaoFactory.getConexao();
                PreparedStatement ps = obtendoConexao.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getTitulacao());
            ps.executeUpdate();//PRESCISA SEMPRE EXECUTAR O EXECUTE
            System.out.println("Registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Professor professor) {
        if (professor == null || professor.getIdnome()== null) {//devo trata com if se comprdr for nulo ou Id for nullo
            System.out.println("Nao foi possivel excluir o registro ");//ira imprimir essa mensagem na tela
            return;
        }
        String sql = "DELETE FROM `escola`.`professor` WHERE `idnome`= ?";//objeto que executara o codigo
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, professor.getIdnome());
            ps.executeUpdate();//PRESCISA SMEPRE EXECUTAR O EXECUTE
            System.out.println("Registro excluido com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(Professor professor) {
        if (professor == null || professor.getIdnome()== null) {
            System.out.println("Nao foi possivel atualizar o registro ");
            return;
        }
        String sql = "UPDATE `escola`.`professor` SET nome = ?, titulacao = ? WHERE idnome = ?";

        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getTitulacao());
            ps.setInt(3, professor.getIdnome());
            ps.executeUpdate();
            System.out.println("Registro atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Professor> selectAll() {
        String sql = "SELECT idnome, nome, titulacao FROM escola.professor";
        List<Professor> professorList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                professorList.add(new Professor(rs.getInt("idnome"), rs.getString("nome"), rs.getString("titulacao")));
//professorList.add(new Professor(rs.getInt("id"),rs.getInt("cpf"), rs.getInt("nome")));

            }
            return professorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    //METODO PARA PROCURA NOME
    public static List<Professor> searchbyName(String nome) {
        String sql = "SELECT idnome, nome, titulacao FROM escola.professor WHERE nome like ? ";
        List<Professor> professorList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                professorList.add(new Professor(rs.getInt("idnome"), rs.getString("nome"), rs.getString("titulacao")));
//professorList.add(new Professor(rs.getInt("id"),rs.getInt("cpf"), rs.getInt("nome")));

            }
            ConexaoFactory.close(conn, ps, rs);
            return professorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    
    public static Professor searchById(Integer idnome) {
        String sql = "SELECT idnome, nome, titulacao FROM escola.professor WHERE idnome = ? ";
        Professor professor = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idnome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                professor = new Professor(rs.getInt("idnome"), rs.getString("nome"),rs.getString("titulacao"));
            }
            ConexaoFactory.close(conn, ps, rs);
            return professor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
    

