import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Musica> listaDeMusicas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println(" Bem-vindo ao Sistema de Streaming v2 (POO) ");

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> cadastrarMusica(scanner);
                    case 2 -> listarMusicas();
                    case 3 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        } while (opcao != 3);
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n1. Cadastrar | 2. Listar | 3. Sair");
    }

    public static void cadastrarMusica(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        
        System.out.print("Duração (minutos, ex 3,5): ");
        double duracaoMin = scanner.nextDouble();
        scanner.nextLine();
        int duracaoSeg = (int) (duracaoMin * 60);

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        // INSTANCIAÇÃO DO OBJETO: Criando a música e adicionando à lista by:tharik
        Musica novaMusica = new Musica(titulo, artista, duracaoSeg, genero);
        listaDeMusicas.add(novaMusica);

        System.out.println("Música cadastrada com sucesso!");
    }

    public static void listarMusicas() {
        if (listaDeMusicas.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }

        for (int i = 0; i < listaDeMusicas.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + listaDeMusicas.get(i).exibirDados());
        }
    }
}
