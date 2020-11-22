
package ProjetoCadastroTurma.crud;

import java.util.List;
import java.util.Scanner;
import ProjetoCadastroTurma.bancoDados.ProfessorDAO;
import ProjetoCadastroTurma.classe.Professor;

public class testProfessor {
     private static Scanner teclado = new Scanner(System.in);// METODO SCANNER DADOS USANDO EXPRESSOES 
      public static void executar(int op) {
        switch (op) {
            case 1:
                inserir();
                break;
            case 2:
                atualizar();
                break;
            case 3:
                listar();
                break;
            case 4:
                System.out.println("Digite o nome");
                buscarPorNome(teclado.nextLine());
                break;
            case 5:
                deletar();
                break;
        }
    }
      
    private static void inserir() {
        Professor prof = new Professor();
        System.out.println("Nome: ");
        prof.setNome(teclado.nextLine());
        System.out.println("Titulacao: ");
        prof.setTitulacao(teclado.nextLine());
        ProfessorDAO.save(prof);
    }

    //Metodo
    private static void atualizar() {
        System.out.println("Selecione um dos professores abaixo");
        List<Professor> professorList = listar();
        Professor prof = professorList.get(Integer.parseInt(teclado.nextLine()));
        System.out.println("Novo nome ou enter para manter o mesmo");
        String nome = teclado.nextLine();
        System.out.println("Nova Titulacao ou enter para manter o mesmo");
        String titulacao = teclado.nextLine();
        if(!nome.isEmpty()){
            prof.setNome(nome);
        }
        if(!titulacao.isEmpty()){
            prof.setTitulacao(titulacao);
        }
            ProfessorDAO.update(prof);
    }

    //Metodo para listar todos os Compadores
    public static List<Professor> listar() {
        List<Professor> professorList = ProfessorDAO.selectAll();
        for (int i = 0; i < professorList.size(); i++) {
            Professor prof = professorList.get(i);
            System.out.println("(" + i + ")" + prof.getNome() + ", " + prof.getTitulacao());
        }
        return professorList;
    }
    
    //
    private static void buscarPorNome(String nome){
        List<Professor> professorList = ProfessorDAO.searchbyName(nome);
        for (int i = 0; i < professorList.size(); i++) {
            Professor prof = professorList.get(i);
            System.out.println("(" + i + ")" + prof.getNome() + ", " + prof.getTitulacao());
        }
        
    }
    
    public static void deletar(){
        System.out.println("Selecione um professor a baixo para deletar");
        List<Professor> professorlist = listar();
        int index = Integer.parseInt(teclado.nextLine());
        System.out.println("Tem certeza? s/n");
        String op = teclado.nextLine();
        if(op.startsWith("s"));
            ProfessorDAO.delete(professorlist.get(index));

    }
}
