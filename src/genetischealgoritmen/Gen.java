/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

import java.util.Comparator;

/**
 *
 * @author arvhoeck
 */
public class Gen {

    private Stad stad;
    private int volgnr;

    public Gen(Stad stad, int volgnr) {
        this.stad = stad;
        this.volgnr = volgnr;

    }

    public Stad getStad() {
        return stad;
    }

    public int getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(int volgnr) {
        this.volgnr = volgnr;
    }
}

class SortGenByNumber implements Comparator<Gen> {

    @Override
    public int compare(Gen gen1, Gen gen2) {
        return gen1.getVolgnr() - gen2.getVolgnr();
    }
}
