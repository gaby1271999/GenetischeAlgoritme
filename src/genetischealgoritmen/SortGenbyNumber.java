/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

import java.util.Comparator;

/**
 *
 * @author Felix Capon, Gabriel D'Hondt, Aäron Vanhoecke
 */
class SortGenByNumber implements Comparator<Gen> {

    @Override
    public int compare(Gen gen1, Gen gen2) {
        return gen1.getVolgnr() - gen2.getVolgnr();
    }
}
