import java.util.ArrayList;

public class Playlist {

    private ArrayList<Musicas> musicas;
    private String nome;

    private static final int MAX_MUSICAS = 100;

    public Playlist() {
        this("Sem nome");
    }

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusicas(Musicas m) {
        if (m == null) {
            System.out.println("Música inválida.");
            return;
        }

        if (musicas.size() >= MAX_MUSICAS) {
            System.out.println("❌ Limite de " + MAX_MUSICAS + " músicas atingido nesta playlist.");
            return;
        }

        for (Musicas musica : musicas) {
            if (musica.getTitulo().equalsIgnoreCase(m.getTitulo())
                    && musica.getArtista().equalsIgnoreCase(m.getArtista())) {
                System.out.println("⚠ \"" + m.getTitulo() + "\" já está na playlist.");
                return;
            }
        }

        musicas.add(m);
    }

    public void removerMusica(String titulo) {
        boolean removido = musicas.removeIf(m -> m.getTitulo().equalsIgnoreCase(titulo));

        if (!removido) {
            System.out.println("Música \"" + titulo + "\" não encontrada na playlist.");
        } else {
            System.out.println("✅ \"" + titulo + "\" removida da playlist.");
        }
    }

    public void removerMusica(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        musicas.remove(indice);
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (Musicas m : musicas) {
            System.out.println("  ─────────────────────────");
            System.out.println(m);
        }
    }

    // ==================== GETTERS / SETTERS ====================

    public String getPlayNome() { return nome; }

    /** Retorna cópia da lista de músicas (para iteração segura). */
    public ArrayList<Musicas> getMusicas() {
        return new ArrayList<>(musicas);
    }

    public int getTotalMusicas() { return musicas.size(); }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome da Playlist inválido.");
        this.nome = nome.trim();
    }

    @Override
    public String toString() {
        return "🎵 Playlist: " + nome + " (" + musicas.size() + " música(s))";
    }
}
