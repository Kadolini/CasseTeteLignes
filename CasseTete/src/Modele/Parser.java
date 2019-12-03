package Modele;

/**
 *
 * @author Fazul Nazar
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Parser {

    //Attributs du Parser
    int[] boardSize; //Tableau d'entiers qui stockent la taille du plateau à l'index 0(largeur) et 1 (longueur)
    Case[][] casing; //Tableau representant le plateau

    public Parser(){

    }


    private BufferedReader getBuffer(String fileName) throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8.name());
        return new BufferedReader(isr);

    }

    public void parse(String fileName) throws IOException {

        BufferedReader br = getBuffer(fileName);
        String firstLine = br.readLine();                   //on lit la 1ere ligne du fichier
        String[] sizer = firstLine.split(" ");              //on eclate la chaine dans un tableau avec le regex "espace"
        boardSize = [Integer.parseInt(sizer[0], Integer.parseInt(sizer[1])]);   // on initialise le tableau en fonction de la taille
        for (int i = 0; i <boardSize[0]; i++){
            for (int j = 0; j < boardSize[1] ; j++){
                casing[i][j] = new RouteCase(EMPTY,i,j)                 //on initialise toute ses cases à vide
            }
        }

        String line;
        while ((line = br.readLine()) !=  null) {
            String[] symboler = line.split(" ");
            switch (symboler[0]){
                case "SP" :
                     casing[Integer.parseInt(symboler[1])][Integer.parseInt(symboler[2])] = new SymbolCase(SPIRAL, Integer.parseInt(symboler[1]), Integer.parseInt(symboler[2]));
                    break;
                case "ST" :
                    casing[Integer.parseInt(symboler[1])][Integer.parseInt(symboler[2])] = new SymbolCase(STAR, Integer.parseInt(symboler[1]), Integer.parseInt(symboler[2]));
                    break;
                case "TR" :
                    casing[Integer.parseInt(symboler[1])][Integer.parseInt(symboler[2])] = new SymbolCase(TRIANGLE, Integer.parseInt(symboler[1]), Integer.parseInt(symboler[2]));
                    break;
                case "SQ" :
                    casing[Integer.parseInt(symboler[1])][Integer.parseInt(symboler[2])] = new SymbolCase(SQUARE, Integer.parseInt(symboler[1]), Integer.parseInt(symboler[2]));
                    break;
                case "W" :
                    casing[Integer.parseInt(symboler[1])][Integer.parseInt(symboler[2])] = new SymbolCase(WALL, Integer.parseInt(symboler[1]), Integer.parseInt(symboler[2]));
                    break;
                default: break;
            }

        }

    }

    public int[] getBoardSize() {
        return this.boardSize;

    }


    public Case[][] getCasing() {
        return casing;
    }


}



