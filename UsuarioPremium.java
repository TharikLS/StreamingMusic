import model.Musicas;
import model.Playlist;

import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano;
    private ArrayList<Musicas> musicasBaixadas;

    private static final String[] PLANOS_VALIDOS = {"Mensal", "Anual", "Familiar"};

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        setPlano(plano);
        this.musicasBaixadas = new ArrayList<>();
    }

    // ==================== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS ====================

    @Override
    public void reproduzirMusica(Musicas musica) {
        System.out.println("🎵 [ALTA QUALIDADE 320kbps] " + musica.getTitulo()
                + " - " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    @Override
    public String getTipoUsuario() {
        return "Premium (" + plano + ")";
    }

    @Override
    public void exibirInfo() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║    USUÁRIO PREMIUM ⭐         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ Nome:     " + nome);
        System.out.println("║ Email:    " + email);
        System.out.println("║ Plano:    " + plano);
        System.out.println("║ Playlists: " + playlists.size() + " (ilimitadas)");
        System.out.println("║ Músicas baixadas: " + musicasBaixadas.size());
        System.out.println("║ Músicas ouvidas: " + historicoReproducao.size());
        System.out.println("║ ✅ Download disponível");
        System.out.println("║ ✅ Alta qualidade 320kbps");
        System.out.println("║ ✅ Playlists ilimitadas");
        System.out.println("║ ✅ Sem anúncios");
        System.out.println("╚══════════════════════════════╝");
    }

    // ==================== COMPORTAMENTOS EXCLUSIVOS PREMIUM ====================

    public void baixarMusica(Musicas musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }

        if (musicasBaixadas.contains(musica)) {
            System.out.println("⚠ \"" + musica.getTitulo() + "\" já está baixada.");
            return;
        }

        musicasBaixadas.add(musica);
        System.out.println("⬇ \"" + musica.getTitulo() + "\" baixada com sucesso!");
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");

        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada ainda.");
            return;
        }

        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.println((i + 1) + ". " + musicasBaixadas.get(i).getTitulo()
                    + " - " + musicasBaixadas.get(i).getArtista());
        }
    }

    /** Cria uma playlist sem limite. */
    public void criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
        System.out.println("✅ Playlist \"" + nome + "\" criada!");
    }

    // ==================== GETTERS / SETTERS ====================

    public String getPlano() { return plano; }

    public void setPlano(String plano) {
        if (plano == null || plano.trim().isEmpty())
            throw new IllegalArgumentException("Plano inválido.");

        String planoFormatado = plano.trim();
        for (String p : PLANOS_VALIDOS) {
            if (p.equalsIgnoreCase(planoFormatado)) {
                this.plano = p;
                return;
            }
        }
        throw new IllegalArgumentException("Plano inválido. Use: Mensal, Anual ou Familiar.");
    }

    public ArrayList<Musicas> getMusicasBaixadas() { return musicasBaixadas; }
}
