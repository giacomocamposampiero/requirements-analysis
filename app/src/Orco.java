public class Orco extends Pedina {

    public static final int NOTT_ATT_DIF_BONUS = 50;
    public static final int GIOR_ATT_DIF_BONUS = -50;
    public static final double ATT = 4;
    public static final double DIF = 4;

    /**
     * Costruttore non parametrico
     */
    public Orco () {
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
        if(rifTemp) return att + (att * GIOR_ATT_DIF_BONUS)/100;
        return att + (att * NOTT_ATT_DIF_BONUS)/100;
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
        if(rifTemp) return dif + (dif * GIOR_ATT_DIF_BONUS)/100;
        return dif + (dif * NOTT_ATT_DIF_BONUS)/100;
    }
    
}