public class Musicas {

    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    private static final String[] GENEROS_VALIDOS = {
            "Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"
    };


    public Musicas(String titulo, String artista, int duracao, String genero){
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    // Construtor secundário
    public Musicas(String titulo, String artista){
        this(titulo, artista, 100, "Pop"); // evita erro de validação
    }

    public void setTitulo(String titulo){
        if(titulo == null || titulo.trim().isEmpty()){
            throw new IllegalArgumentException("Titulo invalido.");
        }
        this.titulo = titulo.trim();
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()){
            throw new IllegalArgumentException("Artista invalido.");
        }
        this.artista = artista.trim();
    }

    public void setDuracao(int duracao) {
        if(duracao <= 0 || duracao >= 3600){
            throw new IllegalArgumentException("Duracao deve ser entre 1 e 3599 segundos.");
        }
        this.duracao = duracao;
    }

    public void setGenero(String genero){
        if(genero == null || genero.trim().isEmpty()){
            throw new IllegalArgumentException("Genero invalido.");
        }

        String generoFormatado = genero.trim();
        boolean valido = false;

        for(String g : GENEROS_VALIDOS){
            if(g.equalsIgnoreCase(generoFormatado)){
                this.genero = g; // padroniza
                valido = true;
                break;
            }
        }

        if(!valido){
            throw new IllegalArgumentException(
                    "Genero invalido. Use: Pop, Rock, Jazz, Eletrônica, Hip-Hop ou Clássica."
            );
        }
    }

    public String getTitulo(){
        return titulo;
    }

    public String getArtista(){
        return artista;
    }

    public int getDuracao(){
        return duracao;
    }

    public String getGenero(){
        return genero;
    }

    @Override
    public String toString() {
        return "Título: " + titulo +
                "\nArtista: " + artista +
                "\nDuração: " + formatarDuracao(duracao) +
                "\nGênero: " + genero;
    }

    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }
}
