package ProjetoCadastroTurma.bancoDados;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ProjetoCadastroTurma.classe.Professor;
import ProjetoCadastroTurma.classe.Turma;
import ProjetoCadastroTurma.conn.ConexaoFactory;

public class TurmaDAO {

    public static void save(Turma turma) {//metdo esta salvando uma turma  
        String sql = "INSERT INTO `escola`.`turma` (sala, professorId, dataAbertura) VALUES (?, ?, ?)";//VAlue esta em preparStatment;

        //Esta criando a conexao
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            //Adicionar os valores na query
            ps.setString(1, turma.getSala());
            ps.setInt(2, turma.getProfessor().getIdnome());          
            ps.setDate(3, new java.sql.Date(turma.getDataAbertura().getTime()));
            
            //executando o comando Sql
            ps.executeUpdate();//PRESCISA SMEPRE EXECUTAR O EXECUTE
            //imprimindo na tela registro inserido com sucesso
            System.out.println("Registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
     public static void update(Turma turma) {
        if (turma == null || turma.getCodigo()== null) {
            System.out.println("Nao foi possivel atualizar o registro ");
            return;
        }
        String sql = "UPDATE `escola`.`professor` SET sala = ?, professorId = ?, dataAbertura = ? WHERE codigo = ?";

        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, turma.getSala());
            ps.setInt(2, turma.getProfessor().getIdnome());
            ps.setDate(3, new java.sql.Date(turma.getDataAbertura().getTime()));
            //ps.setDate(4, new java.sql.Date(turma.getDataFechamento().getTime()));
            ps.setInt(4, turma.getCodigo());
            ps.executeUpdate();
            System.out.println("Registro atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public static List<Turma> selectAll() {
        String sql = "SELECT codigo, sala, professorId, dataAbertura FROM escola.turma";
        List<Turma> turmaList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                Professor prof = ProfessorDAO.searchById(rs.getInt("professorId"));
                turmaList.add(new Turma(rs.getInt("codigo"), rs.getString("sala"), prof, rs.getDate("dataAbertura")));
            }
            return turmaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void delete(Turma turma) {
        if (turma == null || turma.getCodigo()== null) {//devo trata com if se comprdr for nulo ou Id for nullo
            System.out.println("Nao foi possivel excluir o registro ");//ira imprimir essa mensagem na tela
            return;
        }
        String sql = "DELETE FROM `escola`.`turma` WHERE `codigo`= ?";//objeto que executara o codigo
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, turma.getCodigo());
            ps.executeUpdate();//PRESCISA SMEPRE EXECUTAR O EXECUTE
            System.out.println("Registro excluido com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
}
