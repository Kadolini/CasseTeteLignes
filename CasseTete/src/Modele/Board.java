/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Askia Abdel Kader
 * @author Fazul Nazar
 */
public class Board extends Observable{
    public final int LARGEUR, HAUTEUR;
    private Case[][] grid;
    private boolean boardIsFull; // Indique si toutes les cases sont remplies
    private boolean symbolsLinked; // Indique si tout les symboles ont été reliés à leur homonyme
    private SymbolCase[][] onlySymbols;
    private Parser parser;
    private File file;
    
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
        return grid[i][j];
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

    public SymbolCase[][] getOnlySymbols() {
        return onlySymbols;
    }

    public void startDD(int i, int j){
        if(tab[i][j].getClass().getName().equals("Modele.CaseSymbole") &&
           tab[i][j].getCaseVerrouillee()==false){
          CaseSymbole csymbole=(CaseSymbole) tab[i][j];
          if(csymbole.getSymbole() != Symbole.VIDE){
            chemin.ajouter(tab[i][j]); 
            }
        }
        else 
           System.out.println("Cliquez sur un Symbole pour tracer le chemin! ");
    }    

    public boolean voisin(Case precedente, Case source){
        return ((source.getX()+1) == precedente.getX())  || 
                ((source.getY()+1) == precedente.getY()) ||
                ((source.getX()-1) == precedente.getX()) ||
                ((source.getY()-1) == precedente.getY());
    }
    
    public void enterDD(int i, int j) {
        if(estLibre(tab[i][j]) && voisin(tab[i][j], chemin.dernierElement())
          && !(tab[i][j].getClass().getName().equals("Modele.CaseSymbole"))){
            System.out.println("J'entre dans la case suivante");
            
            if(chemin.getSize() >=2){
                changeCaseEnRail(chemin, tab[i][j]);
            }
            chemin.ajouter(tab[i][j]);
            
        } 
        else if(tab[i][j].getClass().getName().equals("Modele.CaseSymbole") 
                && voisin(tab[i][j], chemin.dernierElement())){
            CaseSymbole c1= (CaseSymbole)chemin.getListe().get(0);
            CaseSymbole c2= (CaseSymbole)tab[i][j];
            if(c1.getSymbole()== c2.getSymbole()){
            changeCaseEnRail(chemin, tab[i][j]);
            
            chemin.ajouter(tab[i][j]);
            }
            else{
                invalider();
            }
        }
    }
    
    public void valider(){
      setGrille();
    }
    
    public void invalider(){
        
        chemin.effacerPile();
    }
    
    public void stopDD(){
        if(chemin.getListe().get(0).getClass().getName().equals("Modele.CaseSymbole") &&
           chemin.dernierElement().getClass().getName().equals("Modele.CaseSymbole")){
            CaseSymbole c1 = (CaseSymbole)chemin.getListe().get(0);
            CaseSymbole c2 = (CaseSymbole)chemin.dernierElement();
            if(c1.getSymbole() != c2.getSymbole() || 
               (c1.getX()==c2.getX() && c1.getY()==c2.getY())||
                c2.getCaseVerrouillee()==true){
                invalider();
                
            }else{
                chemin.getListe().get(0).setCaseVerrouillee(true);
                chemin.dernierElement().setCaseVerrouillee(true);
                valider();
                chemin.effacerPile();
                
                setChanged();
                notifyObservers();
            }
        }else{
            invalider();
        }
    }
    
    public int getLongueur(){
        return longueur;
    }
    
    public int getLargeur(){
        return largeur;
    }
    
    public int getTailleGrille(){
        return longueur*largeur;
    }
    
    public void setCaseEnRail(int i, int j, CaseRails _case){
        tab[i][j] = _case;
    }
    
    public void setGrille(){
        for(int i = 1; i < chemin.getSize()-1 ; i++){
            setCaseEnRail(chemin.getListe().get(i).getX(),
                          chemin.getListe().get(i).getY(), 
                         (CaseRails)chemin.getListe().get(i));
        }
        
    }
    
}


