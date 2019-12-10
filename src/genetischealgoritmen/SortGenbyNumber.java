package genetischealgoritmen;

import java.util.Comparator;

/** 
 * Sorteert een arraylist met Genen volgens volgnummer
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
class SortGenByNumber implements Comparator<Gen> {

    @Override
    public int compare(Gen gen1, Gen gen2) {
        return gen1.getVolgnr() - gen2.getVolgnr();
    }
}
