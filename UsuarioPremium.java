import java.util.ArrayList;
import java.util.List;

public class UsuarioPremium extends Usuario {
    private String plano;
    private List<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("💎 [ÁUDIO HI-FI] Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ \"" + musica.getTitulo() + "\" baixada para ouvir offline!");
        } else {
            System.out.println("ℹ️ Esta música já foi baixada.");
        }
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- 📂 MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música offline.");
            return;
        }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            musicasBaixadas.get(i).exibir(i + 1);
        }
    }
}
