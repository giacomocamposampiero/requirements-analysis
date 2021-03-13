public abstract class Pedina {
    
    protected final double att;
    protected final double dif;
    
    /**
     * Costruttore parametrico
     * @param att   valore attacco pedina
     * @param dif   valore difesa pedina
     */
    public Pedina(double att, double dif) {
        this.att = att;
        this.dif = dif;
    }
    
    //metodo astratto
    public abstract double attacco(char terreno, boolean rifTemp);
    //metodo astratto
    public abstract double difesa(char terreno, boolean rifTemp);
    
}