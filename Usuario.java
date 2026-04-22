import java.util.ArrayList;
import java.util.List;

public class Usuario {
    protected String nome;
    protected String email;
    protected List<Playlist> playlists; // protected para subclasses acessarem
    protected List<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        this.nome = (nome == null || nome.isBlank()) ? "Convidado" : nome;
        this.email = email;
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }
        for (int i = 0; i < historicoReproducao.size(); i++) {
            historicoReproducao.get(i).exibir(i + 1);
        }
    }

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

    public String getNome() { return nome; }
    public int getTotalPlaylists() { return playlists.size(); }
    public List<Playlist> getPlaylists() { return playlists; }
}
