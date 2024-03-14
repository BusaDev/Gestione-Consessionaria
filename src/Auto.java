public class Auto {
    private String Marca;
    private String Modello;
    private String prezzo;

    public Auto(String marca, String modello, String prezzo) {
        this.Marca = marca;
        this.Modello = modello;
        this.prezzo = prezzo;
    }

    public String getMarca() {
        return Marca;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "Marca='" + Marca + '\'' +
                ", Modello='" + Modello + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
