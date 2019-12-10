package genetischealgoritmen;

import java.util.Random;

/**
 * Houdt constante waarden bij
 * 
 * @author Aäron Vanhoecke, Felix Capon en Gabriel D'Hondt
 * @version 1.0
 * @since 1.0
 */
public class Consts {
    public static Random r = new Random();
    public static int AANTAL_GENEN; //aantal genen per chromosoom
    public static final double MUTATIEKANS = 0.5; //van 0 tot 1
    public static final int AANTAL_CHROMOSOMEN = 2500; //2500
    public static final int AANTAL_GENERATIES = 750;
    
}
