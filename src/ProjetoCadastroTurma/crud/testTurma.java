package ProjetoCadastroTurma.crud;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import ProjetoCadastroTurma.bancoDados.TurmaDAO;
import ProjetoCadastroTurma.classe.Professor;
import ProjetoCadastroTurma.classe.Turma;

import java.text.ParseException;
import java.util.Date;

public class testTurma {

    private static Scanner teclado = new Scanner(System.in);// METODO SCANNER DADOS USANDO EXPRESSOES 

    public static void executar(int op) {
        switch (op) {
            case 1:
                inserir();
                break;
            case 2:
                atualizar();
                break;
            case 3://LISTARS T TODOS OS ALUNOS
                relatorio();
                break;
//            case 4://BUSCAR ALUNO POR NOME
//                System.out.println("Digite o nome");
//                buscarPorNome(teclado.nextLine());
//                break;
//            case 5://DELETAR
//                deletar();
//                break;
        }
    }

    private static void inserir() {
        try {
            Turma turma = new Turma();
            System.out.println("Digita a Sala: ");
            turma.setSala(teclado.nextLine());
            System.out.println("Selecione um dos professores abaixo");
            List<Professor> professorList = testProfessor.listar();
            turma.setProfessor(professorList.get(Integer.parseInt(teclado.nextLine())));
            //ACRESCENTANDO A DATA
            System.out.println("Digita a data de Abertura: ");
            //Formatando a data e fazendo um cast SQL Date
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(teclado.next());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            TurmaDAO.save(turma);
        } catch (ParseException e) {
            System.err.println("Digite uma data valida");
            teclado.nextLine();
        }

    }

    private static void atualizar() {
//        Turma turma = new Turma();
//        System.out.println("Digita a Sala: ");
//        turma.setSala(teclado.nextLine());
//        System.out.println("Selecione um dos Professores Abaixo");
//        List<Professor> professorList = ProfessorDAO.listar();
//        c.setComprador(compradorList.get(Integer.parseInt(teclado.nextLine())));
//        TurmaDAO.save(c);
    }

    //Metodo para listar todos os Compadores
    public static List<Turma> relatorio() {
        List<Turma> turmaList = TurmaDAO.selectAll();
        System.out.println("Relatorio das Turmas");
        for (int i = 0; i < turmaList.size(); i++) {
            Turma turma = turmaList.get(i);
            System.out.println("(" + i + ") A Turma da Sala: " + turma.getSala() + ", Possui o Professor Responsavel " + turma.getProfessor().getNome());
        }
        return turmaList;
    }

}
