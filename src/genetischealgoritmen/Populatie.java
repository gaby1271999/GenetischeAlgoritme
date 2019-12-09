/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author arvhoeck
 */
public class Populatie {

    public static List<Chromosoom> chromosomen;  //nog te bekijken welke collectie?
    private double gemiddeldeEvaluatiewaarde;

    public Populatie() {
        //initialisatie: willekeurig
        //leesSteden(bestandsnaam); AL GEDAAN IN MAIN class
        chromosomen = new ArrayList<>(Consts.AANTAL_CHROMOSOMEN);
        for (int i = 0; i < Consts.AANTAL_CHROMOSOMEN; i++) {
            chromosomen.add(new Chromosoom());
        }

        berekenGemiddeldeEvaluatiewaarde();
        berekenFitnesswaarden();

    }

    public void maakNieuweGeneratie() {
        bepaalKinderen(bepaalOuders());

    }

    public Chromosoom getBesteChromosoom() {
        double besteFitness = 0;
        Chromosoom beste = null;
        for (Chromosoom c : chromosomen) {
            if (beste == null || c.getFitness() < besteFitness) {
                beste = c;
                besteFitness = c.getFitness();
            }
        }
        return beste;
    }

    private List<Chromosoom> bepaalOuders() {
        /*een list even groot als chromosomenlist.
        Op de overeenkomstige index bewaren we de gewichtsfactor.
        Gewichtsfactor berekening:
        eerste element waarde 100 geven. Vervolgens verhouding tov volgende elementen berekenen.
        Som van alle gewichtsfactoren is lengte van denkbeeldige rij.
        Rijgrootte delen door aantal chromosomen.
         */

        double fitnessWaardeFirst = chromosomen.get(0).getFitness();
        double gewichtsfactorFirst = 1;
        double totaalGewichtsfactor = 0;

        List<Double> roulettewiel = new ArrayList<>();

        for (Chromosoom chromosoom : chromosomen) {
            double gewichtsfactor = gewichtsfactorFirst * fitnessWaardeFirst / chromosoom.getFitness();
            roulettewiel.add(gewichtsfactor);

            totaalGewichtsfactor += gewichtsfactor;
        }

        double eerstePijl = Consts.r.nextDouble() * totaalGewichtsfactor;
        double deltaInterval = totaalGewichtsfactor / chromosomen.size();
        List<Chromosoom> ouders = new ArrayList<>();

        double huidigePijl = eerstePijl;
        int index = 0;
        double som = 0;

        for (int counter = 0; counter < chromosomen.size(); counter++) {
            while (!(som <= huidigePijl && som + roulettewiel.get(index) > huidigePijl)) {
                som += roulettewiel.get(index);
                som %= totaalGewichtsfactor;
                index = (index + 1) % roulettewiel.size();
            }

            ouders.add(chromosomen.get(index));
            huidigePijl += deltaInterval;
            huidigePijl %= totaalGewichtsfactor;
        }

        return ouders;
    }

//    private void berekenKinderen() {
//        for (int i = 0; i < Consts.AANTAL_ITTERATIES; i++) {
//            this.chromosomen = bepaalOuders();
//            /*
//            
//            TODO
//            Ik hou nu de orginele lijst nog en zelfs kinderen worden hergebruikt. 
//             */
//            for (int y = 0; y < Consts.AANTAL_CHROMOSOMEN; y++) {
//                Chromosoom chrom1 = chromosomen.get(Consts.r.nextInt(chromosomen.size()));
//                chromosomen.remove(chrom1);
//                Chromosoom chrom2 = chromosomen.get(Consts.r.nextInt(chromosomen.size()));
//                chromosomen.remove(chrom2);
//
//                Chromosoom kind = bepaalKinderen(chrom1, chrom2);
//                chromosomen.add(kind);
//
//                chromosomen.add(Arrays.asList(chrom1, chrom2).get(Consts.r.nextInt(2)));
//
//                //Eventuele toevoeging van mutatie.
//                controleerOpMutatie();
//            }
//        }
//    }
//
//    private Chromosoom bepaalKinderen(Chromosoom chrom1, Chromosoom chrom2) {
//        //Alles voor zonder de grens erbij en alles erna met grens erbij.
//        int scheidingsIndex = 2 + Consts.r.nextInt(chrom1.getGenen().size() - 3);
//
//        Chromosoom child1, child2;
//
//        List<Gen> gen1 = new ArrayList<>();
//        gen1.add(chrom1.getGenen().get(0));
//        gen1.add(chrom1.getGenen().get(1));
//        List<Gen> gen2 = new ArrayList<>();
//        gen2.add(chrom2.getGenen().get(0));
//        gen2.add(chrom2.getGenen().get(1));
//
//        /*
//        TODO
//        
//        de de lijn waarop gesplitst wordt komt van het kotje met index aan de rechter kant.
//        Voor uitleg vraag aan gabriel.
//        
//        Ik heb gekozen om van index 0 tem 1 en van index n-2 tot n-1 met n als grootte van de list niet 
//        erbij te nemen omdat het geen nut heeft om die erbij te nemen omdat je exact dezelfde kinderen krijgt
//        als de ouders.
//         */
//        for (int i = 2; i < chrom1.getGenen().size(); i++) {
//            if (i < scheidingsIndex) {
//                gen1.add(chrom1.getGenen().get(i));
//                gen2.add(chrom2.getGenen().get(i));
//            } else {
//                gen1.add(chrom2.getGenen().get(i));
//                gen2.add(chrom1.getGenen().get(i));
//            }
//        }
//
//        Chromosoom c1 = new Chromosoom(gen1);
//        Chromosoom c2 = new Chromosoom(gen2);
//
//        return Arrays.asList(c1, c2).get(Consts.r.nextInt(2));
//    }

    /*private void controleerOpMutatie() {
        int kans = Consts.r.nextInt(101);

        if (kans <= Consts.MUTATIEKANS) {
            Chromosoom chrom = chromosomen.get(Consts.r.nextInt(chromosomen.size()));

            Gen gen = chrom.getGenen().get(Consts.r.nextInt(chrom.getGenen().size()));


            //In de uitleg staat vermeerderen of verminderen maar ik denk dat we miss een check moeten uitvoeren om
            //ervoor te zorgen dat we niet uit de boundries geraken.
             
            int randomWaarde = Consts.r.nextInt() % (Main.beschikbareSteden.size() + 1);
            gen.setVolgnr(gen.getVolgnr() + randomWaarde);
        }
    }*/

    private void bepaalKinderen(List<Chromosoom> ouders) {
        List<Chromosoom> kinderen = new ArrayList<>(Consts.AANTAL_CHROMOSOMEN);
        for (int k = 0; k < Consts.AANTAL_CHROMOSOMEN; k++) {
            Chromosoom ouder1 = ouders.get(Consts.r.nextInt(ouders.size()));
            ouders.remove(ouder1);
            Chromosoom ouder2 = ouders.get(Consts.r.nextInt(ouders.size()));
            ouders.add(ouder1);
            
            //kans dat getallen gelijk zijn zeer klein. Zekerheid dat ouders uniek zijn.
            //Chromosoom ouder1 = ouders.get(o1);
            //Chromosoom ouder2 = ouders.get(o2);
            int scheidingsLijn = Consts.r.nextInt((Consts.AANTAL_GENEN - 3)) + 1;
            //scheidingslijn geeft rechtergrens aan. vb: scheidingslijn = 1 => enkel 2e element wordt overgeërfd.
            Chromosoom kind1, kind2;
           
            
            List<Gen> gen1 = new ArrayList<>();
            List<Gen> gen2 = new ArrayList<>();


            for (int i = 0; i < Consts.AANTAL_GENEN; i++) {
                if (i <= scheidingsLijn) {
                    gen1.add(ouder1.getGenen().get(i));
                    gen2.add(ouder2.getGenen().get(i));
                } else {
                    gen1.add(ouder1.getGenen().get(i));
                    gen2.add(ouder2.getGenen().get(i));
                }
            }
            kind1 = new Chromosoom(gen1);
            kind2 = new Chromosoom(gen2);
            
            Chromosoom kind;
            
            if (Consts.r.nextBoolean()) {
                kind = kind1;
            } else {
                kind = kind2;
            }
            
            //mutatie
            if (Consts.r.nextDouble() < Consts.MUTATIEKANS) {
                kind.mutatie();
            }

            kinderen.add(kind);
        }

        chromosomen = kinderen;
        berekenGemiddeldeEvaluatiewaarde();
        berekenFitnesswaarden();
    }

    private void berekenGemiddeldeEvaluatiewaarde() {
        double som = 0;
        for (Chromosoom c : chromosomen) {
            som += c.getEvaluatiewaarde();
        }
        gemiddeldeEvaluatiewaarde = som / chromosomen.size();
    }

    private void berekenFitnesswaarden() {
        for (Chromosoom chromosoom : chromosomen) {
            chromosoom.setFitness(gemiddeldeEvaluatiewaarde);
        }
    }
}
