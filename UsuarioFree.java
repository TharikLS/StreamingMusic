import model.Musicas;
import model.Playlist;

/**
 * Usuário Free: playlists limitadas, anúncios a cada 3 músicas, sem downloads.
 * Implementa os métodos abstratos de Usuario de forma específica (Polimorfismo).
 */
public class UsuarioFree extends Usuario {

    private int contadorReproducoes;
    private static final int MAX_PLAYLISTS = 3;
    private static final int MAX_MUSICAS_PLAYLIST = 50;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    // ==================== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS ====================

    @Override
    public void reproduzirMusica(Musicas musica) {
        contadorReproducoes++;

        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        System.out.println("🎵 Reproduzindo (qualidade padrão): " + musica.getTitulo()
                + " - " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    @Override
    public String getTipoUsuario() {
        return "Free";
    }

    @Override
    public void exibirInfo() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      USUÁRIO FREE            ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ Nome:     " + nome);
        System.out.println("║ Email:    " + email);
        System.out.println("║ Playlists: " + playlists.size() + "/" + MAX_PLAYLISTS);
        System.out.println("║ Músicas ouvidas: " + historicoReproducao.size());
        System.out.println("║ ❌ Sem download");
        System.out.println("║ ❌ Qualidade padrão");
        System.out.println("║ ✅ Até " + MAX_PLAYLISTS + " playlists");
        System.out.println("╚══════════════════════════════╝");
    }

    // ==================== COMPORTAMENTOS EXCLUSIVOS FREE ====================

    /** Cria uma playlist manualmente (limitado a MAX_PLAYLISTS). */
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de " + MAX_PLAYLISTS + " playlists atingido! Faça upgrade para Premium.");
            return;
        }

        Playlist p = new Playlist(nome);
        playlists.add(p);
        System.out.println("✅ Playlist \"" + nome + "\" criada!");
    }

    @Override
    protected Playlist criarPlaylistPorGenero(String genero) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            // Reutiliza a última playlist se atingiu o limite
            System.out.println("⚠ Limite de playlists atingido. Adicionando à primeira playlist disponível.");
            return playlists.get(0);
        }
        return new Playlist(genero);
    }

    private void exibirAnuncio() {
        System.out.println("📢 ──────────────────────────────────────");
        System.out.println("   ANÚNCIO: Assine o Premium e ouça");
        System.out.println("   sem interrupções em ALTA QUALIDADE!");
        System.out.println("📢 ──────────────────────────────────────");
    }

    public int getContadorReproducoes() { return contadorReproducoes; }
}
