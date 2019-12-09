/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetischealgoritmen;

/**
 *
 * @author arvhoeck
 */
public class Stad {
    private String naam;
    private int xCoord;
    private int yCoord;
    
    
    public Stad(int xCoord, int yCoord, String naam){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.naam = naam;
    }
    
    public double afstand(Stad stad) {
        return Math.sqrt((stad.xCoord - xCoord)*(stad.xCoord - xCoord) + (stad.yCoord - yCoord)*(stad.yCoord - yCoord));
    }
    
    public String getNaam() {
        return naam;
    }
    
}
