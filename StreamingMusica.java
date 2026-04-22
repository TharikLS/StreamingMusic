import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> catalogo = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Usuario usuarioAtivo;

    public static void main(String[] args) {
        preencherCatalogo();

        System.out.println("=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.println("\nEscolha seu tipo de conta:");
        System.out.println("1. Free (Com anúncios)");
        System.out.println("2. Premium (Alta qualidade e Download)");
        int tipo = lerInteiro();

        if (tipo == 2) {
            System.out.println("Escolha o plano: 1.Mensal | 2.Anual | 3.Familiar");
            int p = lerInteiro();
            String planoEscolhido = (p == 2) ? "Anual" : (p == 3) ? "Familiar" : "Mensal";
            usuarioAtivo = new UsuarioPremium(nome, email, planoEscolhido);
            System.out.println("✅ Conta Premium criada!");
        } else {
            usuarioAtivo = new UsuarioFree(nome, email);
            System.out.println("✅ Conta Free criada!");
        }

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuAdaptado();
            opcao = lerInteiro();
            processarMenu(opcao);
        }

        System.out.println("Saindo... Volte sempre!");
    }

    private static void exibirMenuAdaptado() {
        System.out.println("\n--- MENU " + (usuarioAtivo instanceof UsuarioPremium ? "💎 PREMIUM" : "🆓 FREE") + " ---");
        System.out.println("1. Listar Catálogo e Ouvir");
        System.out.println("2. Ver meu Histórico");
        System.out.println("3. Criar Playlist");
        System.out.println("4. Listar Minhas Playlists");
        
        if (usuarioAtivo instanceof UsuarioPremium) {
            System.out.println("5. Baixar Música");
            System.out.println("6. Ver Músicas Baixadas");
        }
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static void processarMenu(int op) {
        switch (op) {
            case 1 -> {
                listarCatalogo();
                System.out.print("Escolha o número da música para tocar: ");
                int m = lerInteiro();
                if (m > 0 && m <= catalogo.size()) {
                    usuarioAtivo.reproduzirMusica(catalogo.get(m - 1));
                }
            }
            case 2 -> usuarioAtivo.exibirHistorico();
            case 3 -> {
                System.out.print("Nome da playlist: ");
                String nomeP = scanner.nextLine();
                usuarioAtivo.criarPlaylist(nomeP);
            }
            case 4 -> usuarioAtivo.listarPlaylists();
            case 5 -> {
                if (usuarioAtivo instanceof UsuarioPremium premium) {
                    listarCatalogo();
                    System.out.print("Número da música para baixar: ");
                    int b = lerInteiro();
                    premium.baixarMusica(catalogo.get(b - 1));
                }
            }
            case 6 -> {
                if (usuarioAtivo instanceof UsuarioPremium premium) premium.listarMusicasBaixadas();
            }
        }
    }

    private static void listarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE MÚSICAS ---");
        for (int i = 0; i < catalogo.size(); i++) {
            catalogo.get(i).exibir(i + 1);
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void preencherCatalogo() {
        catalogo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        catalogo.add(new Musica("Starboy", "The Weeknd", 230, "Pop"));
        catalogo.add(new Musica("Hotel California", "Eagles", 391, "Rock"));
    }
}
