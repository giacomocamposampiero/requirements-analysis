import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MappaElaboratore {
    
    public final static boolean INIT_RIF_TEMP = true;

    public static void main(String[] args) {
        File mapFile = new File("mappa.txt");
        File pedFile = new File("pedine.txt");
        Scanner mapScan = null, pedScan = null;
        Mappa mappa = null;

        //apertura file
        try {
            mapScan = new Scanner(mapFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File mappa.txt non trovato!");
            System.exit(1);
        }
        try {
            pedScan = new Scanner(pedFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File pedine.txt non trovato!");
            System.exit(1);
        }

        //verifica struttura file
        int row = 0, col;
        if (!mapScan.hasNextLine()) {
            System.out.println("Il file mappa.txt non puo' essere vuoto!");
            System.exit(1);
        }
        row++;
        col = mapScan.nextLine().split(" ").length;
        while (mapScan.hasNext()) {
            row++;
            if (mapScan.nextLine().split(" ").length != col) {
                System.out.println("Il numero di colonne è errato! -- riga " + row + " file mappa.txt");
                System.exit(1);
            }
        }

        //creazione mappa
        char[][] map = new char[row][col];
        row = col = 0;
        try {
            mapScan = new Scanner(mapFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File mappa.txt non trovato!");
            System.exit(1);
        }
        while (mapScan.hasNext()) {
            String riga = mapScan.nextLine();
            for (String ch : riga.split(" ")) {
                char tmp = ch.charAt(0);
                if ((tmp != 'P' && tmp != 'B' && tmp != 'M') || ch.length() != 1) {
                    System.out.println("Codice di terreno non valido! -- riga " + (row + 1) + " file mappa.txt");
                    System.exit(1);
                }
                map[row][col++] = tmp;
            }
            row++;
            col = 0;
        }
        //mappa inizializzata con rifTemp = giorno
        mappa = new Mappa(map, INIT_RIF_TEMP);
        //riempimento mappa con pedine
        int i = 1;
        String errori = "";
        while (pedScan.hasNext()) {
            int xCoord = -1, yCoord=-1;
            char tipo;
            boolean insert = true;
            Pedina tmp = null;
            try {
                xCoord = Integer.parseInt(pedScan.nextLine());
            } catch (NumberFormatException e) {
                errori += "Parametro pedina non corretto! -- riga " + i + " file pedine.txt\n";
                insert = false;
            }
            if (pedScan.hasNext()) {
                i++;
                try {
                    yCoord = Integer.parseInt(pedScan.nextLine());
                } catch (NumberFormatException e) {
                    errori += "Parametro pedina non corretto! -- riga " + i + " file pedine.txt\n";
                    insert = false;
                }
            } else {
                errori += "Manca un parametro! -- riga " + i + " file pedine.txt\n";
                insert = false;
            }
            if (pedScan.hasNext()) {
                String next = pedScan.nextLine();
                i++;
                tipo = next.charAt(0);
                if ((tipo != 'E' && tipo != 'N' && tipo != 'O') || next.length() != 1) {
                    errori += "Parametro pedina non corretto! -- riga " + i + " file pedine.txt\n";
                    insert = false;
                }
                switch (tipo) {
                    case 'E':
                        tmp = new Elfo();
                        break;
                    case 'O':
                        tmp = new Orco();
                        break;
                    case 'N':
                        tmp = new Nano();
                        break;
                }
            } else {
                errori += "Manca un parametro! -- riga " + (i+1) + " file pedine.txt\n";
                insert = false;
            }
            if (insert) {
                try {
                    mappa.inserisciPedina(new Coord(xCoord, yCoord), tmp);
                } catch (IllegalArgumentException e) {
                    errori += "Pedina fuori dal bordo o casella piena! -- riga " + (i-2) + " file pedine.txt\n";
                }
            }
            i++;
        }
        if(errori.length() != 0) {
            System.out.println(errori);
            System.exit(1);
        }
        mapScan.close();
        pedScan.close();        
                
        //elaborazione e stampa a video dei risultati
        System.out.println("\nNumero di elfi presenti nella mappa: "+mappa.numeroTipologia(Elfo.class));
        System.out.println("Numero di orchi presenti nella mappa: "+mappa.numeroTipologia(Orco.class));
        System.out.println("Numero di nani presenti nella mappa: "+mappa.numeroTipologia(Nano.class));
        System.out.print("Casella con il maggior valore di attacco di giorno: ");
        for(Coord tmp : mappa.maxAttacco()) System.out.print(tmp+" ");
        System.out.print("\nCasella con il maggior valore di difesa di giorno: ");
        for(Coord tmp : mappa.maxDifesa()) System.out.print(tmp+" ");
        //switch rifTemp
        mappa.switchRifTemp();
        System.out.print("\nCasella con il maggior valore di attacco di notte: ");
        for(Coord tmp : mappa.maxAttacco()) System.out.print(tmp+" ");
        System.out.print("\nCasella con il maggior valore di difesa di notte: ");
        for(Coord tmp : mappa.maxDifesa()) System.out.print(tmp+" ");
        System.out.print("\nCasella con il maggior numero di pezzi dello stesso tipo: ");
        for(Coord tmp : mappa.maxPezziUguali()) System.out.print(tmp+" ");
        System.out.println("\n");
    }//main

}
