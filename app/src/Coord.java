public class Coord {

    private int x;
    private int y;

    /**
     * Costruttore parametrico
     * @param x coordinata x, di riga
     * @param y coordinata y, di colonna
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }//Coord
    
    /**
     * Restituisce la coordinata x
     * @return coordinata x
     */
    public int getX() {
        return x;
    }//getX
    
    /**
     * Resituisce la coordinata y
     * @return coordinata y
     */
    public int getY() {
        return y;
    }//getY

    /**
     * Resituisce una stringa significativa sull'oggetto
     * @return stringa significativa
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }//toString

}