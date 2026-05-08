package app;

import model.Musicas;
import model.Playlist;
import usuario.Usuario;
import usuario.UsuarioFree;
import usuario.UsuarioPremium;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusic {

    // POLIMORFISMO: lista genérica que pode conter Free e Premium
    private static final ArrayList<Usuario>  usuarios  = new ArrayList<>();
    private static final ArrayList<Playlist> playlists = new ArrayList<>();

    private static final Musicas[] colecao = new Musicas[1000];
    private static int totalMusicas = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Encerrando o sistema. Até mais!");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║  🎵 STREAMING MUSIC v5.0       ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║  --- MÚSICAS ---               ║");
        System.out.println("║  1. Cadastrar música           ║");
        System.out.println("║  2. Listar / Apagar músicas    ║");
        System.out.println("║  3. Buscar por título          ║");
        System.out.println("║  4. Listar por gênero          ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║  --- USUÁRIOS ---              ║");
        System.out.println("║  5. Cadastrar usuário          ║");
        System.out.println("║  6. Buscar usuário             ║");
        System.out.println("║  7. Info do usuário            ║");
        System.out.println("║  8. Listar todos os usuários   ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║  --- REPRODUÇÃO ---            ║");
        System.out.println("║  9. Reproduzir música          ║");
        System.out.println("║ 10. Reproduzir playlist        ║");
        System.out.println("║ 11. Ver histórico              ║");
        System.out.println("║ 12. Baixar música (Premium)    ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║  0. Sair                       ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1  -> cadastrarMusica();
            case 2  -> menuListarApagar();
            case 3  -> buscarPorTitulo();
            case 4  -> listarPorGenero();
            case 5  -> cadastrarUsuario();
            case 6  -> menuBuscarUsuario();
            case 7  -> menuInfoUsuario();
            case 8  -> listarTodosUsuarios();
            case 9  -> menuReproduzirMusica();
            case 10 -> menuReproduzirPlaylist();
            case 11 -> menuVerHistorico();
            case 12 -> menuBaixarMusica();
            case 0  -> {}
            default -> System.out.println("❌ Opção inválida.");
        }
    }

    // ==================== MÚSICAS ====================

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Nome do usuário (dono da música): ");
        String nomeUsuario = scanner.nextLine();

        Usuario usuario = buscarUsuario(nomeUsuario);
        if (usuario == null) {
            System.out.println("❌ Usuário não encontrado. Cadastre primeiro.");
            return;
        }

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração (segundos): ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Duração inválida.");
            return;
        }

        System.out.println("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): ");
        String genero = scanner.nextLine();

        Musicas musica;
        try {
            musica = new Musicas(titulo, artista, duracao, genero);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
            return;
        }

        if (totalMusicas < colecao.length) {
            colecao[totalMusicas++] = musica;
        }

        Playlist play = buscarPlaylistPorGenero(genero);
        if (play == null) {
            play = new Playlist(genero);
            playlists.add(play);
        }
        play.adicionarMusicas(musica);

        usuario.adicionarMusica(musica);

        System.out.println("✅ Música cadastrada com sucesso!");
    }

    public static void menuListarApagar() {
        listarMusicas();

        System.out.println("\nDeseja deletar alguma música? (1 = Sim | 0 = Não)");
        try {
            int opc = Integer.parseInt(scanner.nextLine());
            if (opc == 1) {
                System.out.print("Escolha o índice: ");
                int indice = Integer.parseInt(scanner.nextLine());
                deletarMusica(indice);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida.");
        }
    }

    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");

        if (totalMusicas == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < totalMusicas; i++) {
            System.out.println("────────────────────────");
            System.out.println("Índice: " + i);
            System.out.println(colecao[i]);
        }
    }

    public static void deletarMusica(int indice) {
        if (indice < 0 || indice >= totalMusicas) {
            System.out.println("❌ Índice inválido.");
            return;
        }

        System.out.println("✅ \"" + colecao[indice].getTitulo() + "\" removida.");

        for (int i = indice; i < totalMusicas - 1; i++) {
            colecao[i] = colecao[i + 1];
        }
        colecao[--totalMusicas] = null;
    }

    public static void buscarPorTitulo() {
        System.out.print("\nDigite o título: ");
        String busca = scanner.nextLine();

        for (int i = 0; i < totalMusicas; i++) {
            if (colecao[i].getTitulo().equalsIgnoreCase(busca)) {
                System.out.println(colecao[i]);
                return;
            }
        }
        System.out.println("❌ Música não encontrada.");
    }

    public static void listarPorGenero() {
        System.out.print("Digite o gênero: ");
        String genero = scanner.nextLine();

        Playlist p = buscarPlaylistPorGenero(genero);

        if (p != null) {
            System.out.println("\n--- MÚSICAS DO GÊNERO: " + genero.toUpperCase() + " ---");
            p.listarMusicas();
        } else {
            System.out.println("❌ Nenhuma música desse gênero.");
        }
    }

    // ==================== USUÁRIOS ====================

    public static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (buscarUsuario(nome) != null) {
            System.out.println("❌ Usuário já existe.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("  1 - Free");
        System.out.println("  2 - Premium");
        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida.");
            return;
        }

        // POLIMORFISMO: variável do tipo base (Usuario) referencia subclasses
        Usuario usuario;

        try {
            if (tipo == 1) {
                usuario = new UsuarioFree(nome, email);
            } else if (tipo == 2) {
                System.out.println("Plano (Mensal / Anual / Familiar): ");
                String plano = scanner.nextLine();
                usuario = new UsuarioPremium(nome, email, plano);
            } else {
                System.out.println("❌ Tipo inválido.");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
            return;
        }

        usuarios.add(usuario);
        System.out.println("✅ Usuário cadastrado com sucesso!");
        usuario.exibirInfo(); // POLIMORFISMO: chama exibirInfo() da subclasse correta
    }

    public static void menuBuscarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        u.listarMusicas();
    }

    public static void menuInfoUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        u.exibirInfo(); // POLIMORFISMO
    }

    public static void listarTodosUsuarios() {
        System.out.println("\n--- TODOS OS USUÁRIOS ---");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        // POLIMORFISMO: toString() de cada subclasse é chamado automaticamente
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i));
        }
    }

    // ==================== REPRODUÇÃO ====================

    public static void menuReproduzirMusica() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        System.out.print("Título da música: ");
        String titulo = scanner.nextLine();

        Musicas musica = buscarMusicaPorTitulo(titulo);
        if (musica == null) {
            System.out.println("❌ Música não encontrada. Cadastre-a primeiro.");
            return;
        }

        // POLIMORFISMO: chama a versão correta de reproduzirMusica()
        u.reproduzirMusica(musica);
    }

    public static void menuReproduzirPlaylist() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        System.out.print("Gênero da playlist: ");
        String genero = scanner.nextLine();

        Playlist p = buscarPlaylistPorGenero(genero);
        if (p == null) {
            System.out.println("❌ Playlist não encontrada.");
            return;
        }

        // POLIMORFISMO: reproduzirPlaylist() usa reproduzirMusica() da subclasse correta
        u.reproduzirPlaylist(p);
    }

    public static void menuVerHistorico() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        u.exibirHistorico();
    }

    public static void menuBaixarMusica() {
        System.out.print("Nome do usuário Premium: ");
        String nome = scanner.nextLine();

        Usuario u = buscarUsuario(nome);
        if (u == null) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }

        if (!(u instanceof UsuarioPremium)) {
            System.out.println("❌ Somente usuários Premium podem baixar músicas.");
            System.out.println("💡 Faça upgrade para Premium e aproveite downloads ilimitados!");
            return;
        }

        System.out.print("Título da música: ");
        String titulo = scanner.nextLine();

        Musicas musica = buscarMusicaPorTitulo(titulo);
        if (musica == null) {
            System.out.println("❌ Música não encontrada.");
            return;
        }

        // Cast seguro após verificação com instanceof
        UsuarioPremium premium = (UsuarioPremium) u;
        premium.baixarMusica(musica);
    }

    // ==================== HELPERS ====================

    public static Usuario buscarUsuario(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) return u;
        }
        return null;
    }

    public static Playlist buscarPlaylistPorGenero(String genero) {
        for (Playlist p : playlists) {
            if (p.getPlayNome().equalsIgnoreCase(genero)) return p;
        }
        return null;
    }

    public static Musicas buscarMusicaPorTitulo(String titulo) {
        for (int i = 0; i < totalMusicas; i++) {
            if (colecao[i].getTitulo().equalsIgnoreCase(titulo)) return colecao[i];
        }
        return null;
    }
}
