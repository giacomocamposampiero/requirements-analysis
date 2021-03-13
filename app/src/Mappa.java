import java.util.ArrayList;
import java.util.LinkedList;

public class Mappa {
    
    private char[][] map;
    private boolean rifTemp;                        //true->giorno, false->notte
    private LinkedList<Pedina>[][] pedine;

    /**
     * Costruttore parametrico
     * @param map       matrice di char, rappresenta le tipologie di terreno
     * @param rifTemp   riferimento temporale iniziale della mappa (true=giorno)
     */
    public Mappa(char[][] map, boolean rifTemp) {
        this.map = map;
        this.rifTemp = rifTemp;
        defaultPedineInit();
    }//costruttore
     
    /**
     * Costruttore parametrico, rifTemp=true di default
     * @param map   matrice di char, rappresenta le tipologie di terreno
     */
    public Mappa(char[][] map) {
        this(map, true);
    }//costruttore
    
    /**
     * Esegue lo switch giorno/notte
     */
    public void switchRifTemp() {
        rifTemp = !rifTemp;
    }//switchRifTemp
    
    /**
     * Metodo che restituisce il numero di pedine della tipologia specificata
     * @param classe    tipologia di pedina
     * @return numero di pedine della tipologia specificata
     */
    public int numeroTipologia(Class classe) {
        int tot = 0;
        for (LinkedList<Pedina>[] riga : pedine) 
            for (LinkedList<Pedina> casella : riga) 
                for(Pedina tmp : casella) if (tmp.getClass() == classe) tot++;
        return tot;
    }//numeroTipologia
    
    /**
     * Trova le caselle con il massimo valore di attacco nella mappa
     * @return caselle con il massimo valore di attacco nella mappa
     */
    public ArrayList<Coord> maxAttacco() {
        int max = 0;
        ArrayList<Coord> res = new ArrayList<>();
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                int casellaAtt = 0;
                for(Pedina tmp: pedine[i][j]) casellaAtt += tmp.attacco(map[i][j], rifTemp);
                if(casellaAtt > max) {
                    max = casellaAtt;
                    res.clear();
                    res.add(new Coord(j, i));
                } else if(casellaAtt == max) {
                    res.add(new Coord(j, i));
                }
            }
        }
        return res;
    }//maxAttacco
    
    /**
     * Trova le caselle con il massimo valore di difesa nella mappa
     * @return caselle con il massimo valore di difesa nella mappa
     */
    public ArrayList<Coord> maxDifesa() {
        int max = 0;
        ArrayList<Coord> res = new ArrayList<>();
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                int casellaDif = 0;
                for(Pedina tmp: pedine[i][j]) casellaDif += tmp.difesa(map[i][j], rifTemp);
                if(casellaDif > max) {
                    max = casellaDif;
                    res.clear();
                    res.add(new Coord(j, i));
                } else if(casellaDif == max) {
                    res.add(new Coord(j, i));
                }
            }
        }
        return res;
    }//maxDifesa
    
    /**
     * Restituisce il massimo numero di pezzi uguali in una cella
     * @return massimo numero di pezzi uguali in una cella
     */
    public ArrayList<Coord> maxPezziUguali() {
        int max = 0;
        ArrayList<Coord> res = new ArrayList<>();
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                int elfi = 0, nani = 0, orchi = 0;
                for(Pedina tmp: pedine[i][j]) {
                    if(tmp.getClass()==Elfo.class) elfi++;
                    else if(tmp.getClass()==Orco.class) orchi++;
                    else nani++;
                }
                int cellaMax = (orchi > elfi) ? orchi : elfi;
                cellaMax = (cellaMax > nani) ? cellaMax : nani;
                if(cellaMax > max) {
                    max = cellaMax;
                    res.clear();
                    res.add(new Coord(j, i));
                } else if(cellaMax == max) {
                    res.add(new Coord(j, i));
                }
            }
        }
        return res;
    }//maxPezziUguali
    
    /**
     * Metodo per inserire una pedina in una determinata cella della mappa
     * @param c posizione in cui inserire la pedina
     * @param p pedina da inserire
     * @throws IllegalArgumentException se la pedina viene inserita in una casella in cui ce ne sono già 5 o se c è fuori dalla mappa
     */
    public void inserisciPedina(Coord c, Pedina p) throws IllegalArgumentException {
        if(c.getY() >= map.length || c.getX() >= map[0].length) throw new IllegalArgumentException();
        if(pedine[c.getY()][c.getX()].size() > 4) throw new IllegalArgumentException();
        pedine[c.getY()][c.getX()].add(p);
    }//inserisciPedina

    /**
     * Metodoo privato, inizializza la matrice di liste
     */
    private void defaultPedineInit() {
        int rig = map.length;
        int col =  (rig == 0) ? 0 : map[0].length;
        this.pedine = new LinkedList[rig][col];
        for(int i=0; i<rig; i++) {
            for(int j=0; j<col; j++) pedine[i][j] = new LinkedList<>();
        }
    }//defaultPedineInit

    /**
     * Restituisce una stringa significativa sull'oggetto
     * @return stringa significativa
     */
    @Override
    public String toString() {
        String res="";
        for (char[] riga : map) {
            for (int j = 0; j<map[0].length; j++) {
                res += riga[j] + " ";
            }
            res +="\n";
        }
        return res;
    }//toString
    
}
