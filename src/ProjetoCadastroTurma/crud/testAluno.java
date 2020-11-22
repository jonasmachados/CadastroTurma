
package ProjetoCadastroTurma.crud;

import ProjetoCadastroTurma.bancoDados.AlunoDAO;
import java.util.List;
import java.util.Scanner;
import ProjetoCadastroTurma.classe.Aluno;


public class testAluno {
    
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
                listar();
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
        Aluno al = new Aluno();
        System.out.println("Nome: ");
        al.setNome(teclado.nextLine());
        AlunoDAO.save(al);
    }

    //METODO PARA ATUALIZAR
    private static void atualizar() {
        System.out.println("Selecione um dos alunos abaixo");
        List<Aluno> alunoList = listar();
        Aluno al = alunoList.get(Integer.parseInt(teclado.nextLine()));
        System.out.println("Novo nome ou enter para manter o mesmo");
        String nome = teclado.nextLine();
        if(!nome.isEmpty()){
            al.setNome(nome);
        }
            AlunoDAO.update(al);
    }

    //Metodo para listar todos os Compadores
    public static List<Aluno> listar() {
        List<Aluno> alunoList = AlunoDAO.selectAll();
        for (int i = 0; i < alunoList.size(); i++) {
            Aluno al = alunoList.get(i);
            System.out.println("(" + i + ")" + al.getNome());
        }
        return alunoList;
    }
    
    //
    private static void buscarPorNome(String nome){
        List<Aluno> alunoList = AlunoDAO.searchbyName(nome);
        for (int i = 0; i < alunoList.size(); i++) {
            Aluno al = alunoList.get(i);
            System.out.println("(" + i + ")" + al.getNome());
        }
        
    }
    
    public static void deletar(){
        System.out.println("Selecione um aluno a baixo para deletar");
        List<Aluno> alunolist = listar();
        int index = Integer.parseInt(teclado.nextLine());
        System.out.println("Tem certeza? s/n");
        String op = teclado.nextLine();
        if(op.startsWith("s"));
            AlunoDAO.delete(alunolist.get(index));
    }
    
}
