package genetischealgoritmen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Is een chromosoom en houd een mogelijke volgorde van de
 * steden bij.
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
public class Chromosoom {

    private List<Gen> genen;
    private double fitness;
    private double evaluatiewaarde;

    /**
     * Maakt een nieuwe chromosoom aan. Zonder parameters omdat
     * deze de ingeleze bestand gaat inlezen.
     */
    public Chromosoom() {
        List<Stad> steden = Main.beschikbareSteden;
        genen = new ArrayList<>(Consts.AANTAL_GENEN);

        int waarde = -5 * (steden.size() + 1);
        genen.add(new Gen(steden.get(0), waarde));
        
        for (int i = 1; i < steden.size(); i++) {
            waarde = Consts.r.nextInt() % (steden.size() + 1) * 3;  //waarde blijft kleiner dan +- 5*(28)
            genen.add(new Gen(steden.get(i), waarde));
        }
        
        waarde = 5 * (steden.size() + 1);
        genen.add(new Gen(steden.get(0), waarde));

        berekenEvaluatiewaarde();
    }

    /**
     * Maakt een nieuw chromossom aan met een bestaande lijst van genen.
     * 
     * @param genen 
     */
    public Chromosoom(List<Gen> genen) {
        this.genen = genen;

        berekenEvaluatiewaarde();
    }

    /**
     * @return fitnesswaarde van het object
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * @return evaluatiewaarde van het object
     */
    public double getEvaluatiewaarde() {
        return evaluatiewaarde;
    }

    /**
     * Berekent en verandert de fitnesswaarde van het object
     * 
     * @param gemiddeldeEvaluatiewaarde 
     */
    public void setFitness(double gemiddeldeEvaluatiewaarde) {
        fitness = evaluatiewaarde / gemiddeldeEvaluatiewaarde;
    }

    /**
     * @return lijst met genen van het object
     */
    public List<Gen> getGenen() {
        return genen;
    }

    /**
     * Berekent de evaluatiewaarde van het object
     */
    private void berekenEvaluatiewaarde() {
        //eerst sorteren 'genen'
        List<Gen> kopie = new ArrayList(genen);
        Collections.sort(kopie, new SortGenByNumber());

        evaluatiewaarde = 0;
        for (int i = 0; i < kopie.size() - 1; i++) {
            evaluatiewaarde += kopie.get(i).getStad().afstand(kopie.get(i + 1).getStad());
        }
    }

    /**
     * Een random gen wordt een mutatie gegeven.
     */
    public void mutatie() {
        int indexGen = Consts.r.nextInt(Consts.AANTAL_GENEN - 2) + 1;

        genen.get(indexGen).veranderVolgnr((-2));

        berekenEvaluatiewaarde();
    }

    /**
     * Sorteerd op grootte van volgnummer en maakt dan een string van het object. 
     * 
     * @return gesorteerde string van de genen.
     */
    @Override
    public String toString() {
        List<Gen> kopie = new ArrayList(genen);
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
