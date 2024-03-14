import java.util.ArrayList;
import java.util.InputMismatchException;

public class Concessionarie {
    private String nomeAzienda;
     ArrayList<Filiale> concessionarie = new ArrayList<>();

    public Concessionarie(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }

    public ArrayList<Filiale> getConcessionarie() {
        return concessionarie;
    }

    public void filialeCorrente(int filiale) {
        System.out.println("Ti trovi nella gestione della filiale " + concessionarie.get(filiale).getNomeFiliale() +" che si trova a " + concessionarie.get(filiale).getPosizioneFiliale());
    }
    public void stampaFiliale(){
        int i = 1;
        for(Filiale filiale: concessionarie){
            System.out.println("ID: " + i +" "+ filiale + "\n");
            i++;
        }
    }
    public void infoFiliale(int filiale){
        System.out.println("\nId Filiale: "+ (filiale+1) +
        "\nNome Filiale: "+ concessionarie.get(filiale).getNomeFiliale() +
        "\nCittà Filiale: "+ concessionarie.get(filiale).getPosizioneFiliale() +
        "\nNumero massimo posti auto: "+ concessionarie.get(filiale).getPostiAutoMax() +
        "\nPosti auto occupati nella Filiale: "+ concessionarie.get(filiale).getAutos().size() +
        "\nPosti auto vuoti nella Filiale: "+(concessionarie.get(filiale).getPostiAutoMax()-concessionarie.get(filiale).getAutos().size()) +"\n");

    }
    public void aggiungiFiliale(String nome, String luogo, int garage){
        Filiale tempFiliale = new Filiale(nome, luogo, garage);
        this.concessionarie.add(tempFiliale);
    }
    public void aggiungiAutoFiliale(int filiale, String marca, String modello, String prezzo){
        Auto nuovaAuto = new Auto(marca,modello,prezzo);
        concessionarie.get(filiale).aggiungiAuto(nuovaAuto);
    }
    public void rimuoviAutoFiliale(int filiale, int autoSelezionata) throws Exception {
        if(autoSelezionata > concessionarie.get(filiale).getAutos().size()){
            throw new Exception("Posizione selezionata non presente nell'elenco");
        }else if(autoSelezionata != 0){
            Auto autoTemp = concessionarie.get(filiale).getAutos().get(autoSelezionata);
            System.out.println("Auto eliminata: " + autoTemp + "\n");
            concessionarie.get(filiale).getAutos().remove(autoTemp);
        }
    }
    public void stampaAutoFiliale(int filiale){
        if(!concessionarie.get(filiale).getAutos().isEmpty()) {
            concessionarie.get(filiale).stampaAuto();
        }else{
            System.out.println("non sono ancora presenti delle automobili nella filiale selezionata");
        }
    }
    public void cercaAutoPerMarca(int filiale, String AutoDaCercare){
        int distDiLevenshtein = 1000;
        int tempInt = 0;
        int counter = 0;
        for (int i = 0; i<concessionarie.get(filiale).getAutos().size();i++) {
            String tempString = concessionarie.get(filiale).getAutos().get(i).getMarca();
            tempInt = levenshtein(AutoDaCercare,tempString,AutoDaCercare.length()-1,tempString.length()-1);
            if(tempInt<=distDiLevenshtein){
                counter = i;
                distDiLevenshtein = tempInt;
            }
        }
        String marcaTrovata = concessionarie.get(filiale).getAutos().get(counter).getMarca();
        System.out.println("\nNessuna marca trovata con: "+AutoDaCercare);
        System.out.println("la marca che si avvicina è :" + marcaTrovata);
        concessionarie.get(filiale).cercaAuto(marcaTrovata);
        System.out.println();
    }
    public int levenshtein(String AutoDaCercare,String s2, int m, int n){
        StringBuilder s1 = new StringBuilder(AutoDaCercare);
        int distDiLevenshtein = 0;
        for (m = m ; m >= 0; m--){
            if(s1.charAt(m) != s2.charAt(n) && m < n && m < s1.length()){
                s1.insert(m+1, s2.charAt(n));
                m++;
                n--;
                distDiLevenshtein++;
            }else if(s1.charAt(m) != s2.charAt(n) && m < n){
                s1.append(s2.charAt(n));
                m++;
                n--;
                distDiLevenshtein++;
            }else if (s1.charAt(m) != s2.charAt(n) && m > n){
                s1.deleteCharAt(m);
                distDiLevenshtein++;
            }else if(s1.charAt(m) != s2.charAt(n) && m == n){
                s1.setCharAt(m,s2.charAt(n));
                n--;
                distDiLevenshtein++;
            }else{
                n--;
            }
        }
        return distDiLevenshtein;
    }


}
