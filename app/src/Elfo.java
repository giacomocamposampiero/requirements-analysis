public class Elfo extends Pedina {
    
    public static final int BOSC_DIF_BONUS = 100;
    public static final double ATT = 5;
    public static final double DIF = 2;
       
    /**
     * Costruttore non parametrico
     */
    public Elfo() {
        super(ATT, DIF);
    }

    /**
     * Restituisce il valore di attacco della pedina 
     * in date condizioni di terreno e riferimento temporale
     * @param terreno   tipologia di terreno
     * @param rifTemp   riferimento temporale
     * @return valore di attacco della pedina
     */
    @Override
    public double attacco(char terreno, boolean rifTemp) {
        return att;
    }

    /**
     * Restituisce il valore di difesa della pedina
     * in date condizioni di terreno e riferimento temporale
     * @param terreno   tipologia di terreno
     * @param rifTemp   riferimento temporale
     * @return valore di difesa della pedina
     */
    @Override
    public double difesa(char terreno, boolean rifTemp) {
         if(terreno == 'B') return dif + (dif * BOSC_DIF_BONUS)/100;
         return dif;
    }
    
}