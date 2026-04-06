import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String nome;
    private final List<Musica> musicas;

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = (nome == null || nome.isBlank()) ? "Nova Playlist" : nome;
    }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            musicas.add(musica);
            System.out.println("✅ \"" + musica.getTitulo() + "\" adicionada!");
        }
    }

    public void removerMusica(int indice) {
        if (validarIndice(indice)) {
            Musica removida = musicas.remove(indice - 1);
            System.out.println("🗑️ \"" + removida.getTitulo() + "\" removida.");
        } else {
            System.out.println("❌ Índice inválido!");
        }
    }

    public void listarMusicas() {
        System.out.println("\n🎵 Playlist: " + nome);
        if (musicas.isEmpty()) {
            System.out.println("   (Vazia)");
            return;
        }
        for (int i = 0; i < musicas.size(); i++) {
            musicas.get(i).exibir(i + 1);
        }
    }

    public int getDuracaoTotal() {
        return musicas.stream().mapToInt(Musica::getDuracao).sum();
    }

    public int getTotalMusicas() { return musicas.size(); }

    public List<Musica> getMusicas() {
        return Collections.unmodifiableList(musicas);
    }

    private boolean validarIndice(int indice) {
        return indice >= 1 && indice <= musicas.size();
    }
}
