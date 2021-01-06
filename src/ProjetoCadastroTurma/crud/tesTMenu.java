
package ProjetoCadastroTurma.crud;


import java.util.Scanner;


public class tesTMenu {
    
    private static Scanner teclado = new Scanner(System.in);// METODO SCANNER DADOS USANDO EXPRESSOES 

    public static void main(String[] args){

        int op;
        while (true) {
            menu();
            op = Integer.parseInt(teclado.nextLine());
            if (op == 0) {
                System.out.println("Saindo do Sistema");
                break;
            }
            if (op == 1) {
                menuProfessor();
                op = Integer.parseInt(teclado.nextLine());
                testProfessor.executar(op);
            }
            if (op == 2) {
                menuAluno();
                op = Integer.parseInt(teclado.nextLine());
                testAluno.executar(op);
            }
            if (op == 3) {
                menuTurma();
                op = Integer.parseInt(teclado.nextLine());
                testTurma.executar(op);
            }
        }
    }
    
    private static void menu() {
        System.out.println("Bem vindo ao ESCOLA EAD:");
        System.out.println("1. Professor");
        System.out.println("2. Aluno");
        System.out.println("3. Turma");
        System.out.println("0. Sair");
    }
    
    private static void menuProfessor() {
        System.out.println("Cadastro Professor");
        System.out.println("1: Inserir um novo Professor");
        System.out.println("2: Atualizar dados do Professor");
        System.out.println("3: Listar todos os Professores");
        System.out.println("4: Buscar Professor por Nome");
        System.out.println("5: Deletar");
        System.out.println("0: Voltar");
    }
    
    private static void menuAluno() {
        System.out.println("Cadastro Aluno");
        System.out.println("1: Inserir um novo Aluno");
        System.out.println("2: Atualizar dados do Aluno");
        System.out.println("3: Listar todos os Alunos");
        System.out.println("4: Buscar Aluno por Nome");
        System.out.println("5: Deletar");
        System.out.println("0: Voltar");
    }
    
    private static void menuTurma(){
        System.out.println("Menu Turma");
        System.out.println("1: Inserir uma nova Turma");
        System.out.println("2: Atualizar dados da Turma");
        System.out.println("3: Listas alunos e professor responsavel");
        
        
    }
    
}
