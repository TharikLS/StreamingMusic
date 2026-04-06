public class Musica {
    private String titulo;
    private String artista;
    private int duracao; // em segundos
    private String genero;

    // Construtor Padrão: Usa this() para chamar o principal
    public Musica() {
        this("Sem Título", "Desconhecido", 0, "Geral");
    }

    // Sobrecarga: Apenas título e artista
    public Musica(String titulo, String artista) {
        this(titulo, artista, 0, "Geral");
    }

    // Construtor Parametrizado (O "Coração" da inicialização)
    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    // GETTERS E SETTERS COM VALIDAÇÃO
    public String getTitulo() { return titulo; }
    
    public void setTitulo(String titulo) {
        this.titulo = (titulo == null || titulo.isBlank()) ? "Título Inválido" : titulo;
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        this.artista = (artista == null || artista.isBlank()) ? "Artista Inválido" : artista;
    }

    public int getDuracao() { return duracao; }

    public void setDuracao(int duracao) {
        this.duracao = Math.max(0, duracao); // Garante que não seja negativo
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        this.genero = (genero == null || genero.isBlank()) ? "Geral" : genero;
    }

    public String getDuracaoFormatada() {
        return String.format("%d:%02d", duracao / 60, duracao % 60);
    }

    public void exibir(int numero) {
        System.out.printf("%d. %s | %s | %s | %s%n", 
            numero, titulo, artista, getDuracaoFormatada(), genero);
    }

    public boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}
