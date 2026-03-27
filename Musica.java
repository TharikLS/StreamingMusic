public class Musica {
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        this.titulo = titulo; 
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.genero = genero;
    }

    public String exibirDados() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        String tempoFormatado = String.format("%02d:%02d", minutos, segundos);
        
        return String.format("%s - %s | %s | %s", titulo, artista, tempoFormatado, genero);
    }
}
