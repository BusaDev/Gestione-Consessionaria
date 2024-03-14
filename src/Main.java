
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    public static void main(String[] args) {
        int filiale = 0;
        String scelta;
        System.out.println("inserire nome dell'azienda :");
        String nomeAzienda = scanner.next();
        Concessionarie aziendaPrincipale = new Concessionarie(nomeAzienda);
        System.out.println("Benvenuto alla "+nomeAzienda +" Boss!");
        System.out.println("Ancora non è presente nessuna Filiale \nCrea la tua prima Filiale!");
        if(aziendaPrincipale.getConcessionarie().isEmpty()) {
            creaFiliale(aziendaPrincipale);
        }
        do {
            aziendaPrincipale.filialeCorrente(filiale);
            System.out.println("Premi 1 per stampare le info della filiale");
            System.out.println("Premi 2 per aggiungere auto nella filiale");
            System.out.println("Premi 3 per rimuovere auto dalla filiale");
            System.out.println("Premi 4 per stampare auto presenti in filiale");
            System.out.println("Premi 5 per cambiare filiale");
            System.out.println("Premi 6 per creare nuova filiale");
            System.out.println("Premi 7 per uscire dal programma");
            scelta = scanner.next();
            switch(scelta){
                case "1":
                    aziendaPrincipale.infoFiliale(filiale);
                    break;
                case "2":
                    aggiungiAuto(aziendaPrincipale,filiale);
                    break;
                case "3":
                    aziendaPrincipale.stampaAutoFiliale(filiale);
                    rimuoviAuto(aziendaPrincipale,filiale);
                    break;
                case "4":
                    aziendaPrincipale.stampaAutoFiliale(filiale);
                    break;
                case "5":
                    System.out.println("Lista Filiali: ");
                    aziendaPrincipale.stampaFiliale();
                    filiale = cambiaFiliale(aziendaPrincipale, filiale);
                    break;
                case "6":
                    System.out.println("Crea una nuova Filiale");
                    creaFiliale(aziendaPrincipale);
                    break;
                default:
                    System.out.println("input errato riprova\n");
                    break;
            }
        }while(!scelta.equalsIgnoreCase("7"));

    }
    public static void creaFiliale(Concessionarie aziendaPrincipale){
        int done = 0;
        do {
            try {
                System.out.print("inserisci il nome della Filiale: ");
                String nome = scanner.next();
                if (nome.isEmpty()) {
                    throw new Exception("ERRORE la Filiale deve avere un nome!\n");
                }
                System.out.print("inserisci la città in cui si trova la Filiale: ");
                String citta = scanner.next();
                if (citta.isEmpty()) {
                    throw new Exception("ERRORE la Filiale deve trovarsi da qualche parte!\n");
                }
                System.out.print("inserisci il numero massimo di posti auto della Filiale: ");
                int garage = scanner.nextInt();
                aziendaPrincipale.aggiungiFiliale(nome, citta, garage);
                System.out.println("Filiale " + nome + " a " + citta + " creata!\n");
                done = 1;
            } catch (InputMismatchException e) {
                System.out.println("ERRORE inserisci un numero intero valido nel campo posti auto\n");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(done == 0);
    }
    public static void aggiungiAuto(Concessionarie aziendaPrincipale, int filiale){
        int done = 0;
        do {
            try {
                System.out.print("inserisci la marca dell'automobile: ");
                String marca = scanner.next();
                if (marca.isEmpty()) {
                    throw new Exception("ERRORE l' automobile deve avere un marca!\n");
                }
                System.out.print("inserisci il modello dell'automobile: ");
                String modello = scanner.next();
                if (modello.isEmpty()) {
                    throw new Exception("ERRORE l'automobile deve avere un modello'!\n");
                }
                System.out.print("inserisci il prezzo dell'automobile: ");
                String prezzo = scanner.next();
                if (prezzo.isEmpty()) {
                    throw new Exception("ERRORE l'automobile deve avere un prezzo'!\n");
                }
                aziendaPrincipale.aggiungiAutoFiliale(filiale, marca, modello, prezzo);
                System.out.println("Automobile " + marca + " " + modello + " aggiunta!\n");
                done = 1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(done == 0);

    }
    public static void rimuoviAuto(Concessionarie aziendaPrincipale, int filiale){
        int done = 0;
        do {
            try {
                System.out.println("Inserisci la posizione dell'auto da eliminare o premi 0 per tornare indietro: ");
                int autoSelezionata = scanner.nextInt();
                aziendaPrincipale.rimuoviAutoFiliale(filiale, autoSelezionata);
                done = 1;
            }catch (InputMismatchException e){
                System.out.println("Inserisci una posizione valido");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }while(done == 0);
    }
    public static int cambiaFiliale(Concessionarie aziendaPrincipale, int filiale){
        System.out.println("seleziona ID filiale desiderata o inserisci 0 se vuoi tornare indietro");
        try {
            int filialeTemp = scanner.nextInt();
            if(aziendaPrincipale.getConcessionarie().size()<filialeTemp){
                throw new Exception("Filiale non trovata");
            }
            return filialeTemp-1;
        }catch (InputMismatchException e){
            System.out.println("ID inserito errato");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return filiale;
    }
}