public class Musica {
    private String titulo;
    private String artist;
    private int duracao; // em segundos
    private String genero;

    // Construtor Padrão (Sem argumentos)
    public Musica() {
        this("Sem Título", "Artista Desconhecido", 0, "Indefinido");
    }

    // Construtor com sobrecarga (Apenas título e artista)
    public Musica(String titulo, String artista) {
        this(titulo, artista, 0, "Pop"); // Chama o construtor parametrizado
    }

    // Construtor Parametrizado (Completo)
    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    // GETTERS E SETTERS COM VALIDAÇÃO
    public String getTitulo() { return titulo; }
    
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        } else {
            this.titulo = "Título Inválido";
        }
    }

    public String getArtista() { return artist; }

    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artist = artista;
        } else {
            this.artist = "Artista Inválido";
        }
    }

    public int getDuracao() { return duracao; }

    public void setDuracao(int duracao) {
        if (duracao >= 0) {
            this.duracao = duracao;
        } else {
            this.duracao = 0;
            System.out.println("⚠️ Duração não pode ser negativa. Definida como 0.");
        }
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        // A validação lógica de gênero pode ser feita aqui ou no StreamingMusica
        this.genero = (genero != null && !genero.isEmpty()) ? genero : "Geral";
    }

    public String getDuracaoFormatada() {
        return String.format("%d:%02d", duracao / 60, duracao % 60);
    }

    public boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artist.toLowerCase().contains(busca.toLowerCase());
    }

    public void exibir(int numero) {
        System.out.printf("%d. %s | %s | %s | %s%n",
                numero, titulo, artist, getDuracaoFormatada(), genero);
    }
}
