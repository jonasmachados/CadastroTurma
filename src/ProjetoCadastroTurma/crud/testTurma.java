package ProjetoCadastroTurma.crud;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import ProjetoCadastroTurma.bancoDados.TurmaDAO;
import ProjetoCadastroTurma.classe.Professor;
import ProjetoCadastroTurma.classe.Turma;

import java.text.ParseException;

public class testTurma {

    private static Scanner teclado = new Scanner(System.in);// METODO SCANNER DADOS USANDO EXPRESSOES 

    public static void executar(int op) throws ParseException {
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
            case 4://BUSCAR ALUNO POR NOME
                System.out.println("Digite o nome");
                buscarPorNome(teclado.nextLine());
                break;
            case 5://DELETAR
                deletar();
                break;
        }
    }

    private static void inserir() {
        try {
            Turma turma = new Turma();
            //SELECIONADO A SALA
            System.out.println("Digita a Sala: ");
            turma.setSala(teclado.nextLine());
            //SELECIONANDO O PROFESSOR
            System.out.println("Selecione um dos professores abaixo");
            List<Professor> professorList = testProfessor.listar();
            turma.setProfessor(professorList.get(Integer.parseInt(teclado.nextLine())));
            //ACRESCENTANDO A DATA
            System.out.println("Digita a data de Abertura: ");

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            turma.setDataAbertura(formato.parse(teclado.nextLine()));

            TurmaDAO.save(turma);
        } catch (ParseException e) {
            System.err.println("Digite uma data valida");
            teclado.nextLine();
        }

    }

    private static void atualizar() {
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

    private static void deletar() {
        System.out.println("Selecione uma das turmas a baixo para deletar");
        List<Turma> turmalist = relatorio();
        int index = Integer.parseInt(teclado.nextLine());
        System.out.println("Tem certeza? s/n");
        String op = teclado.nextLine();
        if (op.startsWith("s"));
        TurmaDAO.delete(turmalist.get(index));
    }

    private static void buscarPorNome(String sala){
        List<Turma> turmaList = TurmaDAO.searchbyName(sala);
        for (int i = 0; i < turmaList.size(); i++) {
            Turma turma = turmaList.get(i);
            System.out.println("(" + i + ")A Turma da Sala: " + turma.getSala()+ ", Data de Abertura: " + turma.getDataAbertura() + ", Data de Fechamento: " + turma.getDataFechamento() +  ", "+ turma.getProfessor());
        }
    }

}
