package Modele;

/**
 *
 * @author Fajr
 */
public class Game {
    private Board board;
    private int levels;
    
    
    public Game(){
        board = new Board(3);
    }
    
    public void startGame(){
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
    
}
