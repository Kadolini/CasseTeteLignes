/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 *
 * @author Askia Abdel Kader
 * @author Fazul Nazar
 */
public class Board {
    private final int LARGEUR, HAUTEUR;
    private Case[][] grid;
    private Route[] routes;
    private Symbol[] Symboles;
    private int nbPts; /*Nombre de points qui servira peut-être à voir combien de chemin ont été validé...*/
    private boolean boardIsFull; // Indique si toutes les cases sont remplies
    private boolean symbolsLinked; // Indique si tout les symboles ont été reliés à leur homonyme
    
    public Board(int size) {
        LARGEUR = size;
        HAUTEUR = size;
        grid = new Case[LARGEUR][HAUTEUR];
        //nbPts = 0;
        boardIsFull = false;
        symbolsLinked = false;
        
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
    
    public void initBoard(int size){
        Board levelBoard = new Board(size);
    }
    
    public void startGame(int size){
        initBoard(size);
    }
    
    
    
    
    
}
