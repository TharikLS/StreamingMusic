public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes = 0;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de 3 playlists atingido!");
            System.out.println("💎 Assine Premium para criar playlists ilimitadas!");
            return;
        }
        super.criarPlaylist(nome);
    }

    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("📢 ANÚNCIO: Assine o PREMIUM!");
        System.out.println("=".repeat(30));
    }
}
