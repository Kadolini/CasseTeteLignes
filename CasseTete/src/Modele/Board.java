/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Askia Abdel Kader
 * @author Fazul Nazar
 */
public class Board {
    public final int LARGEUR, HAUTEUR;
    private Case[][] grid;
    private Route[] routes;
    private Symbol[] Symboles;
    private int nbPts; /*Nombre de points qui servira peut-être à voir combien de chemin ont été validé...*/
    private boolean boardIsFull; // Indique si toutes les cases sont remplies
    private boolean symbolsLinked; // Indique si tout les symboles ont été reliés à leur homonyme
    private SymbolCase[][] onlySymbols;
    private Parser parser;
    
    public Board(int s1, int s2, String fileName) throws IOException {
        LARGEUR = s1;
        HAUTEUR = s2;
        grid = new Case[LARGEUR][HAUTEUR];
        //nbPts = 0;
        boardIsFull = false;
        symbolsLinked = false;
        parser = new Parser(fileName);


        
    }
    
    public SymbolCase[][] buildOnlySymbols() {
        for (int i = 0; i< LARGEUR ;i++){
            for (int j = 0; j< HAUTEUR; j++){
                if(parser.getSymbolcasing()[i][j] != null){
                    onlySymbols[i][j] = parser.getSymbolcasing()[i][j];
                }
            }
        }

        return onlySymbols;
    }


    public void setIndexOfGrid(int i, int j, Case newCase){
        this.grid[i][j] = newCase;
    }
    
    public Case getIndexOfGrid(int i, int j){
        return this.grid[i][j];
    }
    
    public boolean getBoardIsFull(){
        return this.boardIsFull;
    }
    
    public void setBoardIsFull(boolean fullOrNot){
        this.boardIsFull = fullOrNot;
    }
    
    public boolean getSymbolsLinked(){
        return this.symbolsLinked;
    }
    
    public void setSymbolsLinked(boolean linked){
        symbolsLinked = linked;
    }
    
    public void initBoard(int s1, int s2){
        for (int i = 0; i< LARGEUR ;i++){
            for (int j = 0; j< HAUTEUR; j++){
                grid[i][j] = parser.getCasing()[i][j];
            }
        }
    }
}


