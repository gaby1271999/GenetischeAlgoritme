/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    protected static List<Stad> beschikbareSteden;

    public static void main(String[] args) {
 
        try {
            
            leesSteden("steden.txt");
            Populatie p = new Populatie();
            
            System.out.println("Een fitnesswaarde < 1 is beter t.o.v. het gemiddelde.\n");


            System.out.println("Eerste (willekeurige) generatie heeft als beste chromosoom:\n" + p.getBesteChromosoom() + "\n");
           
            
            for (int i = 0; i < Consts.AANTAL_GENERATIES; i++) {
                p.maakNieuweGeneratie();
                //System.out.println("beste: " + p.getBesteChromosoom());
            }

            Chromosoom beste = p.getBesteChromosoom();
            System.out.println("\nHet beste chromosoom uit de eindpopulatie is:\n" + beste);

        } catch (FileNotFoundException err) {
            System.out.println("Fout. Geef nieuwe bestandnaam: ");
        }

    }


    private static void leesSteden(String bestandsnaam) throws FileNotFoundException {
        beschikbareSteden = new ArrayList<>();
        Scanner sc = new Scanner(new File(bestandsnaam));
        int aantalSteden = sc.nextInt();
        Consts.AANTAL_GENEN = aantalSteden + 1; // bekijk dit nog eens
        sc.nextLine(); // '\n' weg
        int teller = 0;
        while (sc.hasNext() && teller < aantalSteden) {
            String[] lijn = sc.nextLine().split(":");
            beschikbareSteden.add(new Stad(Integer.parseInt(lijn[0]), Integer.parseInt(lijn[1]), lijn[2]));
            teller++;
        }
        sc.close();
    }
}
