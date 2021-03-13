public class Nano extends Pedina {

    public static final int MONT_ATT_BONUS = 100;
    public static final double ATT = 2;
    public static final double DIF = 5;
    
    /**
     * Costruttore non parametrico
     */
    public Nano() {
        super(ATT, DIF);
    }//costruttore

    /**
     * Restituisce il valore di attacco della pedina 
     * in date condizioni di terreno e riferimento temporale
     * @param terreno   tipologia di terreno
     * @param rifTemp   riferimento temporale
     * @return valore di attacco della pedina
     */
    @Override
    public double attacco(char terreno, boolean rifTemp) {
        if(terreno == 'M') return att + (att * MONT_ATT_BONUS)/100;
        return att;
    }//attacco

    /**
     * Restituisce il valore di difesa della pedina
     * in date condizioni di terreno e riferimento temporale
     * @param terreno   tipologia di terreno
     * @param rifTemp   riferimento temporale
     * @return valore di difesa della pedina
     */
    @Override
    public double difesa(char terreno, boolean rifTemp) {
         return dif;
    }//difesa
    
}