/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author arvhoeck
 */
public class Chromosoom {

    private List<Gen> genen;
    private double fitness;

    private double evaluatiewaarde;    //totaal afgelegde afstand

    public Chromosoom() {
        genen = new ArrayList<>();
        //willekeurige bepaling volgnr
        int waarde = -5 * (Main.beschikbareSteden.size() + 1);
        genen.add(new Gen(Main.beschikbareSteden.get(0), waarde));
        for (int i = 1; i < Main.beschikbareSteden.size(); i++) { // of for each?
            waarde = Consts.r.nextInt() % (Main.beschikbareSteden.size() + 1) * 3;  //waarde blijft kleiner dan +- 5*(28)
            genen.add(new Gen(Main.beschikbareSteden.get(i), waarde));
        }
        waarde = 5 * (Main.beschikbareSteden.size() + 1);
        genen.add(new Gen(Main.beschikbareSteden.get(0), waarde));
        berekenEvaluatiewaarde();
    }
    
    
    public Chromosoom(List<Gen> genen) {
        this.genen = genen;
        
        berekenEvaluatiewaarde();
    }

    public double getFitness() {
        return fitness;
    }

    public double getEvaluatiewaarde() {
        return evaluatiewaarde;
    }

    public void setFitness(double gemiddeldeEvaluatiewaarde) {
        fitness = evaluatiewaarde / gemiddeldeEvaluatiewaarde;
    }

    public List<Gen> getGenen() {
        return genen;
    }

        private void berekenEvaluatiewaarde() {
        //eerst sorteren 'genen'
        List<Gen> kopie = new ArrayList(genen); // kopie nemen
        Collections.sort(kopie, new SortGenByNumber());

        //copie.forEach((g) -> System.out.println(copie.indexOf(g) + ", " + g.getVolgnr()));
        //genen.forEach((g) -> System.out.println(genen.indexOf(g) + ", " + g.getVolgnr()));
        evaluatiewaarde = 0;
        for (int i = 0; i < kopie.size() - 1; i++) {
            evaluatiewaarde += kopie.get(i).getStad().afstand(kopie.get(i + 1).getStad());
        }
    }
    
    
    public void mutatie() {
        int indexGen = Consts.r.nextInt(Consts.AANTAL_GENEN - 2) + 1;
        //genen.get(indexGen).veranderVolgnr(Consts.r.nextInt(279) - 139);

        genen.get(indexGen).veranderVolgnr((-2));

        berekenEvaluatiewaarde(); //moet opnieuw berekend worden

    }

    @Override
    public String toString() {
        List<Gen> kopie = new ArrayList(genen); // kopie nemen
        Collections.sort(kopie, new SortGenByNumber());

        StringBuilder sb = new StringBuilder();
        sb.append("[Chromosoom] afstand: ");
        sb.append(evaluatiewaarde);
        sb.append(" fitness: ");
        sb.append(fitness);
        sb.append("\t( ");
        for (Gen g : kopie) {
            sb.append(g.toString());
            sb.append(" ");
        }
        sb.append(")");

        return sb.toString();
    }

}
