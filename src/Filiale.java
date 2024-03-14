import java.util.ArrayList;

public class Filiale {
    private String nomeFiliale;
    private String posizioneFiliale;
    private int postiAutoMax;
    ArrayList<Auto> autos = new ArrayList<Auto>();

    public Filiale(String nomeFiliale, String posizioneFiliale, int postiAutoMax) {
        this.nomeFiliale = nomeFiliale;
        this.posizioneFiliale = posizioneFiliale;
        this.postiAutoMax = postiAutoMax;
    }

    public String getNomeFiliale() {
        return nomeFiliale;
    }
    public ArrayList<Auto> getAutos() {
        return autos;
    }

    public String getPosizioneFiliale() {return posizioneFiliale;}

    public int getPostiAutoMax() {return postiAutoMax;}

    public void stampaAuto(){
        int i = 1;
        for(Auto auto:autos){
            System.out.println(i + " " + auto + "\n");
            i++;
        }
    }
    public void aggiungiAuto(Auto nuovaAuto){
        autos.add(nuovaAuto);
    }
    public void rimuoviAuto(Auto autoRimossa){
        autos.remove(autoRimossa);
        System.out.println("auto rimossa");
    }
    public void cercaAuto(String marcaDaCercare){
        for (Auto auto:autos){
            if(auto.getMarca().equalsIgnoreCase(marcaDaCercare)){
                System.out.println(auto);
            }
        }
    }
    public void numeroAutoInConcessionaria(){
        ArrayList<String> arrayMarche = new ArrayList<>();
        int c = 0;
        System.out.println("in concessionaria sono presenti : " + autos.size() + " auto");
        for(Auto auto:autos){
            if(!arrayMarche.contains(auto.getMarca())){
                arrayMarche.add(auto.getMarca());
            }
        }
        for (String marca : arrayMarche){
            c = 0;
            for (Auto auto: autos){
                if(auto.getMarca().equalsIgnoreCase(marca)){
                    c++;
                }
            }
            System.out.println("sono presenti "+ c + " auto della marca "+ marca);
        }
    }

    @Override
    public String toString() {
        return  ", Nome Filiale: " + nomeFiliale +
                ", Città: " + posizioneFiliale
                ;
    }
}
