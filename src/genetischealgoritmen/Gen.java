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
    
    public void veranderVolgnr(int waarde) {
        /*this.volgnr += waarde;
        if (volgnr >= 140) {
            volgnr -= 2 * 140;

        } else if (volgnr <= -140) {
            volgnr += 2 * 140;
        }*/
        if (waarde > 0){
            volgnr = ((volgnr + waarde) + 139) % 279 - 139; 
        } else{
            volgnr = ((volgnr + waarde) - 139) % 279 + 139; 
        }
    }

    @Override
    public String toString() {
        return stad.getNaam();
    }
    
    
}