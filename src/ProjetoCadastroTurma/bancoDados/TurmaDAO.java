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
import java.sql.Date;

public class TurmaDAO {

    public static void save(Turma turma) {//metdo esta salvando uma turma  
        String sql = "INSERT INTO `escola`.`turma` (sala, professorId, dataAbertura) VALUES (?, ?, ?)";//VAlue esta em preparStatment;

        //Esta criando a conexao
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            //Adicionar os valores na query
            ps.setString(1, turma.getSala());
            ps.setInt(2, turma.getProfessor().getIdnome());
            ps.setDate(3, (Date) turma.getDataAbertura());
            //executando o comando Sql
            ps.executeUpdate();//PRESCISA SMEPRE EXECUTAR O EXECUTE
            //imprimindo na tela registro inserido com sucesso
            System.out.println("Registro inserido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static List<Turma> selectAll() {
        String sql = "SELECT codigo, sala, professorId FROM escola.turma";
        List<Turma> turmaList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {//ENQUANTO VARIVAEL RS RECEBE O OBJETO ELE VAI SELECIONAR 
                Professor prof = ProfessorDAO.searchById(rs.getInt("professorId"));
                turmaList.add(new Turma(rs.getInt("codigo"), rs.getString("sala"), prof));
            }
            return turmaList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
