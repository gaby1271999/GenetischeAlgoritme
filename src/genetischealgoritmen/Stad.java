package genetischealgoritmen;

/**
 * Is een stad en houdt de naam van de stad en de coordinaten bij
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
public class Stad {
    
    private final String naam;
    private final int xCoord;
    private final int yCoord;
    
    /**
     * Maakt een Stad object aan
     * 
     * @param xCoord
     * @param yCoord
     * @param naam 
     */
    public Stad(int xCoord, int yCoord, String naam){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        
        this.naam = naam;
    }
    
    /**
     * @param stad
     * @return de afstand tussen deze Stad object en een andere Stad object
     */
    public double afstand(Stad stad) {
        return Math.sqrt((stad.xCoord - xCoord)*(stad.xCoord - xCoord) + (stad.yCoord - yCoord)*(stad.yCoord - yCoord));
    }
    
    /**
     * @return de  naam van deze Stad object
     */
    public String getNaam() {
        return naam;
    }
}
