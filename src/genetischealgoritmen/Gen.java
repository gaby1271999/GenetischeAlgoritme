package genetischealgoritmen;

/**
 * Is een Gen deze houdt de stad en de volgnummer bij
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
public class Gen {

    private Stad stad;
    private int volgnr;

    /**
     * Maakt een nieuwe Gen aan
     * 
     * @param stad
     * @param volgnr 
     */
    public Gen(Stad stad, int volgnr) {
        this.stad = stad;
        this.volgnr = volgnr;
    }
    
    /**
     * Maakt een copy van een bestaande Gen aan
     * 
     * @param gen 
     */
     public Gen(Gen gen){
        this.stad = gen.stad;
        this.volgnr = gen.volgnr;
    }

    /**
     * @return van het Stad object van het Gen object
     */
    public Stad getStad() {
        return stad;
    }

    /**
     * @return van de volgnummer van het Gen object
     */
    public int getVolgnr() {
        return volgnr;
    }

    /**
     * past de volgnummer van het Gen object aan
     * 
     * @param volgnr 
     */
    public void setVolgnr(int volgnr) {
        this.volgnr = volgnr;
    }
    
    /**
     * Verandert het volgnummer met de waarde die zowel positief of negatief mag zijn.
     * 
     * @param waarde 
     */
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

    /**
     * @return de naam van het Stad object.
     */
    @Override
    public String toString() {
        return stad.getNaam();
    }
    
    
}