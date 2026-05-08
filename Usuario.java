import model.Musicas;
import model.Playlist;

import java.util.ArrayList;

/**
 * Classe abstrata que representa um usuário genérico do sistema.
 * ABSTRAÇÃO: define o contrato que todas as subclasses devem seguir.
 * POLIMORFISMO: métodos abstratos são implementados de formas diferentes em cada subclasse.
 */
public abstract class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musicas> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    // ==================== MÉTODOS ABSTRATOS ====================
    // Obriga cada subclasse a implementar sua própria versão (Polimorfismo)

    /** Reproduz uma música. Cada tipo de usuário reproduz de forma diferente. */
    public abstract void reproduzirMusica(Musicas musica);

    /** Retorna o tipo do usuário (Free, Premium, etc.) */
    public abstract String getTipoUsuario();

    /** Exibe as informações detalhadas do usuário. */
    public abstract void exibirInfo();

    // ==================== MÉTODOS CONCRETOS ====================

    /**
     * Reproduz todas as músicas de uma playlist (POLIMORFISMO em ação!).
     * O mesmo método chama reproduzirMusica() de formas diferentes
     * dependendo do tipo real do usuário.
     */
    public void reproduzirPlaylist(Playlist playlist) {
        System.out.println("\n▶ Reproduzindo playlist: " + playlist.getPlayNome());
        System.out.println("Usuário: " + nome + " (" + getTipoUsuario() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        ArrayList<Musicas> musicas = playlist.getMusicas();

        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (Musicas m : musicas) {
            reproduzirMusica(m); // POLIMORFISMO: chama a versão correta
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Playlist finalizada! " + musicas.size() + " música(s) reproduzida(s).");
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE " + nome.toUpperCase() + " ---");

        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
            return;
        }

        for (int i = 0; i < historicoReproducao.size(); i++) {
            Musicas m = historicoReproducao.get(i);
            System.out.println((i + 1) + ". " + m.getTitulo() + " - " + m.getArtista());
        }

        System.out.println("Total: " + historicoReproducao.size() + " música(s).");
    }

    public void adicionarMusica(Musicas musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }

        Playlist playlist = null;

        for (Playlist p : playlists) {
            if (p.getPlayNome().equalsIgnoreCase(musica.getGenero())) {
                playlist = p;
                break;
            }
        }

        if (playlist == null) {
            playlist = criarPlaylistPorGenero(musica.getGenero());
            playlists.add(playlist);
        }

        playlist.adicionarMusicas(musica);
    }

    /** Subclasses podem sobrescrever para criar o tipo correto de playlist. */
    protected Playlist criarPlaylistPorGenero(String genero) {
        return new Playlist(genero);
    }

    public void listarMusicas() {
        if (playlists.isEmpty()) {
            System.out.println("Usuário não possui músicas.");
            return;
        }

        for (Playlist p : playlists) {
            System.out.println("\nGênero: " + p.getPlayNome());
            p.listarMusicas();
        }
    }

    // ==================== GETTERS / SETTERS ====================

    public String getNome()     { return nome; }
    public String getEmail()    { return email; }
    public ArrayList<Playlist> getPlaylists()           { return playlists; }
    public ArrayList<Musicas>  getHistoricoReproducao() { return historicoReproducao; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome.trim();
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email inválido.");
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return "👤 " + nome + " | " + getTipoUsuario() + " | " + email;
    }
}
