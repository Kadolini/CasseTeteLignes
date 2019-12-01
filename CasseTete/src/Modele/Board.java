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
 */
public class Board {
    public final int LARGEUR, HAUTEUR;
    private Case[][] cases;
    public Route[] routes;
    public Symbol[] Symboles;
    public int nbPts; /*Nombre de points qui servira peut-être à voir combien de chemin ont été validé...*/
    
    
    public Board(String[] Board) {
        LARGEUR = Board[0].length();
        HAUTEUR = Board.length;
        cases = new Case[LARGEUR][HAUTEUR];
        //nbPts = 0;
        
    }
    
}
