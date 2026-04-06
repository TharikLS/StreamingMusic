import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    // Catálogo geral de músicas
    static ArrayList<Musica> catalogo = new ArrayList<>();
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    static Scanner scanner = new Scanner(System.in);
    static Usuario usuarioAtivo = null;

    public static void main(String[] args) {
        adicionarMusicasTeste();

        // Cria um usuário e algumas playlists de exemplo
        usuarioAtivo = new Usuario("Estudante");
        usuarioAtivo.criarPlaylist("Favoritas");
        usuarioAtivo.criarPlaylist("Para Treinar");

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming! Até logo! 🎵");
        scanner.close();
    }

    // --- menu ---

    public static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎵 SISTEMA DE STREAMING DE MÚSICA 🎵");
        System.out.println("=".repeat(50));
        System.out.println("-- CATÁLOGO --");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("-- PLAYLISTS --");
        System.out.println("7. Criar playlist");
        System.out.println("8. Listar minhas playlists");
        System.out.println("9. Adicionar música a uma playlist");
        System.out.println("10. Remover música de uma playlist");
        System.out.println("11. Ver músicas de uma playlist");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:  cadastrarMusica(); break;
            case 2:  listarMusicas(); break;
            case 3:  buscarPorTitulo(); break;
            case 4:  buscarPorArtista(); break;
            case 5:  buscarPorGenero(); break;
            case 6:  exibirEstatisticas(); break;
            case 7:  criarPlaylist(); break;
            case 8:  usuarioAtivo.listarPlaylists(); break;
            case 9:  adicionarMusicaPlaylist(); break;
            case 10: removerMusicaPlaylist(); break;
            case 11: verPlaylist(); break;
            case 0:  break;
            default: System.out.println("❌ Opção inválida!");
        }
    }

    // --- catálogo ---

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) { System.out.println("❌ Título vazio!"); return; }

        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        if (artista.isEmpty()) { System.out.println("❌ Artista vazio!"); return; }

        System.out.print("Duração (segundos): ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine().trim());
            if (duracao <= 0) throw new Exception();
        } catch (Exception e) {
            System.out.println("❌ Duração inválida!"); return;
        }

        System.out.println("Gêneros: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica");
        System.out.print("Digite o gênero: ");
        String genero = scanner.nextLine().trim();

        if (validarGenero(genero)) {
            catalogo.add(new Musica(titulo, artista, duracao, genero));
            System.out.println("✅ Música cadastrada com sucesso!");
        } else {
            System.out.println("❌ Gênero inválido!");
        }
    }

    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        if (catalogo.isEmpty()) { System.out.println("Nenhuma música cadastrada."); return; }

        for (int i = 0; i < catalogo.size(); i++) {
            catalogo.get(i).exibir(i + 1);
        }
    }

    public static void buscarPorTitulo() {
        System.out.print("\nDigite o título: ");
        String busca = scanner.nextLine().trim();
        boolean encontrou = false;

        for (Musica m : catalogo) {
            if (m.contemTitulo(busca)) {
                System.out.println("🔍 " + m.getTitulo() + " - " + m.getArtista());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("❌ Nada encontrado.");
    }

    public static void buscarPorArtista() {
        System.out.print("\nDigite o artista: ");
        String busca = scanner.nextLine().trim();
        boolean encontrou = false;

        for (Musica m : catalogo) {
            if (m.contemArtista(busca)) {
                System.out.println("🎤 " + m.getTitulo() + " [" + m.getArtista() + "]");
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("❌ Artista não encontrado.");
    }

    public static void buscarPorGenero() {
        System.out.print("\nDigite o gênero: ");
        String busca = scanner.nextLine().trim();
        if (!validarGenero(busca)) { System.out.println("❌ Gênero inexistente."); return; }

        boolean encontrou = false;
        for (Musica m : catalogo) {
            if (m.getGenero().equalsIgnoreCase(busca)) {
                System.out.println("📻 " + m.getTitulo() + " (" + m.getGenero() + ")");
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("❌ Nenhuma música encontrada nesse gênero.");
    }

    // --- playlists ---

    public static void criarPlaylist() {
        System.out.print("\nNome da nova playlist: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) { System.out.println("❌ Nome vazio!"); return; }
        usuarioAtivo.criarPlaylist(nome);
    }

    public static void adicionarMusicaPlaylist() {
        usuarioAtivo.listarPlaylists();
        if (usuarioAtivo.getTotalPlaylists() == 0) return;

        System.out.print("Escolha o número da playlist: ");
        int numPlaylist = lerOpcao();
        Playlist playlist = usuarioAtivo.getPlaylist(numPlaylist);
        if (playlist == null) return;

        listarMusicas();
        if (catalogo.isEmpty()) return;

        System.out.print("Escolha o número da música: ");
        int numMusica = lerOpcao();
        if (numMusica < 1 || numMusica > catalogo.size()) {
            System.out.println("❌ Música inválida!"); return;
        }
        playlist.adicionarMusica(catalogo.get(numMusica - 1));
    }

    public static void removerMusicaPlaylist() {
        usuarioAtivo.listarPlaylists();
        if (usuarioAtivo.getTotalPlaylists() == 0) return;

        System.out.print("Escolha o número da playlist: ");
        int numPlaylist = lerOpcao();
        Playlist playlist = usuarioAtivo.getPlaylist(numPlaylist);
        if (playlist == null) return;

        playlist.listarMusicas();
        if (playlist.getTotalMusicas() == 0) return;

        System.out.print("Escolha o número da música para remover: ");
        int numMusica = lerOpcao();
        playlist.removerMusica(numMusica);
    }

    public static void verPlaylist() {
        usuarioAtivo.listarPlaylists();
        if (usuarioAtivo.getTotalPlaylists() == 0) return;

        System.out.print("Escolha o número da playlist: ");
        int num = lerOpcao();
        Playlist playlist = usuarioAtivo.getPlaylist(num);
        if (playlist == null) return;

        playlist.listarMusicas();

        if (playlist.getTotalMusicas() > 0) {
            System.out.println("⏱️ Duração total: " + formatarDuracao(playlist.getDuracaoTotal()));
        }
    }

    // --- utilitários ---

    public static void exibirEstatisticas() {
        if (catalogo.isEmpty()) { System.out.println("Sem dados."); return; }

        int totalMusicas = catalogo.size();
        int duracaoTotal = 0;
        for (Musica m : catalogo) duracaoTotal += m.getDuracao();
        int duracaoMedia = duracaoTotal / totalMusicas;

        System.out.println("\n--- ESTATÍSTICAS ---");
        System.out.println("Total de músicas: " + totalMusicas);
        System.out.println("Duração total: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média: " + formatarDuracao(duracaoMedia));
        System.out.println("Gênero predominante: " + generoMaisComum());
        System.out.println("Total de playlists do usuário: " + usuarioAtivo.getTotalPlaylists());
    }

    public static String formatarDuracao(int segundos) {
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }

    public static boolean validarGenero(String genero) {
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero)) return true;
        }
        return false;
    }

    public static String generoMaisComum() {
        int[] contadores = new int[GENEROS_VALIDOS.length];
        for (Musica m : catalogo) {
            for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
                if (m.getGenero().equals(GENEROS_VALIDOS[i])) {
                    contadores[i]++;
                    break;
                }
            }
        }
        int indiceMaior = 0;
        for (int i = 1; i < contadores.length; i++) {
            if (contadores[i] > contadores[indiceMaior]) indiceMaior = i;
        }
        return GENEROS_VALIDOS[indiceMaior];
    }

    public static void adicionarMusicasTeste() {
        catalogo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        catalogo.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        catalogo.add(new Musica("Smells Like Teen Spirit", "Nirvana", 301, "Rock"));
    }
}
