package genetischealgoritmen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class van het programma
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
public class Main {

    protected static List<Stad> beschikbareSteden;

    /**
     * Start van het programma. Leest het bestand steden.txt in en geeft de meest 
     * toepasselijke Chromosoom weer.
     * 
     * @param args 
     */
    public static void main(String[] args) {
 
        try {
            
            leesSteden("steden.txt");
            Populatie p = new Populatie();
            
            System.out.println("Een fitnesswaarde < 1 is beter t.o.v. het gemiddelde.\n");
            System.out.println("Eerste (willekeurige) generatie heeft als beste chromosoom:\n" + p.getBesteChromosoom() + "\n");
            
            for (int i = 0; i < Consts.AANTAL_GENERATIES; i++) {
                p.maakNieuweGeneratie();
            }

            Chromosoom beste = p.getBesteChromosoom();
            System.out.println("\nHet beste chromosoom uit de eindpopulatie is:\n" + beste);
        } catch (FileNotFoundException err) {
            System.out.println("Fout. Geef nieuwe bestandnaam: ");
        }
    }

    /**
     * Leest een bestand in en maakt een lijst van de steden en locaties die zich in
     * het bestand bevinden 
     * 
     * @param bestandsnaam
     * @throws FileNotFoundException 
     */
    private static void leesSteden(String bestandsnaam) throws FileNotFoundException {
        beschikbareSteden = new ArrayList<>();
        Scanner sc = new Scanner(new File(bestandsnaam));
        
        int aantalSteden = sc.nextInt();
        Consts.AANTAL_GENEN = aantalSteden + 1;
        
        sc.nextLine();
        
        int teller = 0;
        while (sc.hasNext() && teller < aantalSteden) {
            String[] lijn = sc.nextLine().split(":");
            beschikbareSteden.add(new Stad(Integer.parseInt(lijn[0]), Integer.parseInt(lijn[1]), lijn[2]));
            
            teller++;
        }
        
        sc.close();
    }
}
