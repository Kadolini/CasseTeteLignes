package Modele;

import java.io.IOException;
import java.util.Observable;
/**
 * @author Askia Abdel Kader
 * @author Fazul Nazar
 */
public class Game{
    private Board board;
    private int levels;
    private Parser parser;
    
    public Game() throws IOException{
        parser = new Parser("src/Levels/level2.txt");
        parser.parse("src/Levels/level2.txt");
        
       
        board = new Board(parser.getBoardSize()[0],parser.getBoardSize()[1], parser.getfileName());
        board.buildOnlySymbols();
    }
    
    public void startGame(){
        board.initBoard(parser.getBoardSize()[0],parser.getBoardSize()[0]);
        while(!board.getBoardIsFull() && !board.getSymbolsLinked()){
            // on fait tourner le jeu ici
            if(board.getBoardIsFull() && !board.getSymbolsLinked()){
                System.out.println("Oups! Les chemins ne sont pas bons.");
            }
            if(!board.getBoardIsFull() && board.getSymbolsLinked()){
                System.out.println("Oups! Vous n'avez pas utiliser toutes les cases.");
            }
        }
        System.out.println("Félicitations vous avez résolu le puzzle.");
        
    }

    public Board getBoard() {
        return board;
    }

    public void startDD(Integer rowIndex, Integer columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void enterDD(Integer rowIndex, Integer columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void stopDD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
