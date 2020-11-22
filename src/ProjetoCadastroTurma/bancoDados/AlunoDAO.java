
package ProjetoCadastroTurma.bancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ProjetoCadastroTurma.classe.Aluno;
import ProjetoCadastroTurma.conn.ConexaoFactory;


public class AlunoDAO {
    
    public static void save(Aluno aluno) {
        String sql = "INSERT INTO `escola`.`aluno` (nome) VALUES (?)";//Value esta em preparStatment
        //Esta criando a conexao
        try (Connection obtendoConexao = ConexaoFactory.getConexao();
                PreparedStatement ps = obtendoConexao.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.executeUpdate();//PRESCISA SEMPRE EXECUTAR O EXECUTE
            System.out.println("Registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Aluno aluno) {
        if (aluno == null || aluno.getMatricula()== null) {//devo trata com if se comprdr for nulo ou Id for nullo
            System.out.println("Nao foi possivel excluir o registro ");//ira imprimir essa mensagem na tela
            return;
        }
        String sql = "DELETE FROM `escola`.`aluno` WHERE `matricula`= ?";//objeto que executara o codigo
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, aluno.getMatricula());
            ps.executeUpdate();//PRESCISA SMEPRE EXECUTAR O EXECUTE
            System.out.println("Registro excluido com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(Aluno aluno) {
        if (aluno == null || aluno.getMatricula()== null) {
            System.out.println("Nao foi possivel atualizar o registro ");
            return;
        }
        String sql = "UPDATE `escola`.`aluno` SET nome = ? WHERE matricula = ?";

        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getMatricula());
            ps.executeUpdate();//ESSA
            System.out.println("Registro atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Aluno> selectAll() {
        String sql = "SELECT matricula, nome FROM escola.aluno";
        List<Aluno> alunoList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                alunoList.add(new Aluno(rs.getInt("matricula"), rs.getString("nome")));
          }
            return alunoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    //METODO PARA PROCURA NOME
    public static List<Aluno> searchbyName(String nome) {
        String sql = "SELECT matricula, nome FROM escola.aluno WHERE nome like ? ";
        List<Aluno> alunoList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                alunoList.add(new Aluno(rs.getInt("matricula"), rs.getString("nome")));
            }
            ConexaoFactory.close(conn, ps, rs);
            return alunoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    
    public static Aluno searchById(Integer matricula) {
        String sql = "SELECT matricula,nome FROM escola.aluno WHERE idnome = ? ";
        Aluno aluno = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                aluno = new Aluno(rs.getInt("matricula"), rs.getString("nome"));
            }
            ConexaoFactory.close(conn, ps, rs);
            return aluno;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    
    
}
