import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private final List<Playlist> playlists;

    public Usuario(String nome) {
        this.nome = (nome == null || nome.isBlank()) ? "Convidado" : nome;
        this.playlists = new ArrayList<>();
    }

    public String getNome() { return nome; }

    public void criarPlaylist(String nomePlaylist) {
        playlists.add(new Playlist(nomePlaylist));
        System.out.println("✅ Playlist criada com sucesso!");
    }

    public void listarPlaylists() {
        System.out.println("\n👤 Playlists de " + nome + ":");
        if (playlists.isEmpty()) {
            System.out.println("   Nenhuma playlist encontrada.");
            return;
        }
        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            System.out.printf("  %d. %s (%d música(s))%n", (i + 1), p.getNome(), p.getTotalMusicas());
        }
    }

    public Playlist getPlaylist(int indice) {
        if (indice < 1 || indice > playlists.size()) {
            System.out.println("❌ Playlist inexistente!");
            return null;
        }
        return playlists.get(indice - 1);
    }

    public int getTotalPlaylists() { return playlists.size(); }
}
