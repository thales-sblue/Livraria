import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int opcaoDesejada;
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        do {        
            System.out.println("\n------ Bem vindo a livraria! ------");
            System.out.println("1 - Cadastrar novo livro");
            System.out.println("2 - Listar livros disponíveis");
            System.out.println("3 - Realizar empréstimo de um livro");
            System.out.println("4 - Listar livros emprestados");
            System.out.println("0 - Sair \n");            
            System.out.println("Selecione a opção desejada: ");
            opcaoDesejada = scanner.nextInt();
            switch (opcaoDesejada) {
                case 1:
                    scanner.nextLine();
                    System.out.println("\n------ Cadastrar novo livro! ------");
                    System.out.println("Informe o título do livro: ");  
                    String titulo = scanner.nextLine();
                    System.out.println("\nInforme o autor do livro: ");
                    String nomeAutor = scanner.nextLine();                                      
                    Autor autor = new Autor(nomeAutor,  new Date());
                    Livro livro = new Livro(titulo, autor, true,  new Date());                    
                    biblioteca.adicionarLivro(livro);
                    biblioteca.adicionarAutor(autor);
                    livro.setId(biblioteca.getLivros().size());
                    autor.setId(biblioteca.getAutores().size());
                    
                    System.out.println("\nLivro cadastrado com sucesso!");
                    System.out.println("ID: " + livro.getId());
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + livro.getAutor().getNome() + "\n");                    

                    break;
                case 2:

                    if(biblioteca.getLivros().isEmpty()){
                        System.out.println("\nNenhum livro disponível!");
                        break;
                    }
                    System.out.println("\n------ Livros disponíveis! ------");
                    biblioteca.getLivros().forEach(l -> {
                        if (l.isDisponivel()) {
                            System.out.println("ID: " + l.getId());
                            System.out.println("Título: " + l.getTitulo());
                            System.out.println("Autor: " + l.getAutor().getNome() + "\n");              
                        }
                    });      

                    break;
                case 3:
                    System.out.println("\n------ Realizar empréstimo de um livro! ------");
                    System.out.println("Informe o ID do livro que deseja emprestar: ");
                    int idLivroEmprestimo = scanner.nextInt();
                    if(idLivroEmprestimo > biblioteca.getLivros().size()){
                        System.out.println("Livro não encontrado! Tente novamente.\n");                        
                        break;
                    }

                    Livro livroEmprestimo = biblioteca.getLivros().get(idLivroEmprestimo - 1);
                    if(livroEmprestimo.isDisponivel() == false){
                        System.out.println("O livro de ID:" + idLivroEmprestimo + " está indisponível para emprestimo no momento!\n");
                        break;
                    }

                    scanner.nextLine();
                    System.out.println("\nInforme o seu nome completo: ");
                    String nomeUsuario = scanner.nextLine();
                    Emprestimo emprestimo = new Emprestimo(livroEmprestimo, nomeUsuario, new Date());
                    biblioteca.realizarEmprestimo(emprestimo);
                    System.out.println("\nEmprestimo registrado com sucesso!");

                    break;
                case 4:
                    if(biblioteca.getEmprestimos().isEmpty()){
                        System.out.println("\nNenhum livro emprestado!");
                        break;
                    }
                    System.out.println("\n------ Livros emprestados! ------");
                    biblioteca.getEmprestimos().forEach(e -> {
                        System.out.println("ID: " + e.getLivro().getId());
                        System.out.println("Título: " + e.getLivro().getTitulo());
                        System.out.println("Autor: " + e.getLivro().getAutor().getNome());
                        System.out.println("Usuário: " + e.getNomeUsuario());
                        System.out.println("Data do empréstimo: " + e.getDataEmprestimo() + "\n");
                    });    

                    break;
                case 0:
                    System.out.println("\n------ Programa finalizado. Obrigado! ------");
                    break;    
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }                   
           
        } while (opcaoDesejada != 0);

        scanner.close();        
    }
}
