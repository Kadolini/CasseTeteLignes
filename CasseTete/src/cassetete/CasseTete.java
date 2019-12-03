 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassetete;


import Modele.Board;
import Modele.Case;
import Modele.Game;
import Modele.Route;
import Modele.Position;
import Modele.RouteCase;
import Modele.Symbol;
import Modele.SymbolCase;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;


/**
 *
 * @author Askia Abdel Kader
 */
public class CasseTete extends Application {
   
    private static final int IMG_TAILLE = 32;
    private Board board; 
    private ImageView[][] images;
    private Image image;
    private Image imgCase;
    private Image imgRoute;
    //LeftUP = DOWNRIGHT etc...
    //UPLEFT = RIGHTDOWN
    private Image imgUP;
    private Image imgLEFT;
    private Image imgDOWNLEFT;
    private Image imgUPLEFT;
    private Image imgUPRIGHT;
    private Image imgDOWNRIGHT;
    private Image imgEMPTY;
    private Image imgSymbol;
    private Image imgSquare; // On peut juste utiliser imgSymbol mais pour des raisons de lecture, pref chaque symbole son image.
    private Image imgTriangle;
    private Image imgSpiral;
    private Image imgWall;
    private Image imgStar;

    
    @Override
    public void start(Stage stage) {
        int s1 = 3,s2 = 3;
        board = new Board(s1,s2);
        imgUP = new Image("img/VERTICALE.png");
        imgLEFT = new Image("img/HORIZONTALE.png");
        imgDOWNLEFT = new Image("img/HAUTGAUCHE.png");
        imgUPLEFT = new Image("img/BASGAUCHE.png");
        imgUPRIGHT = new Image("img/BASDROIT.png");
        imgDOWNRIGHT = new Image("img/HAUTDROIT.png");
        imgEMPTY = new Image("img/VIDE.jpg");
        imgSquare = new Image("img/CARRE.png");
        imgTriangle = new Image("img/TRIANGLE.jpg");
        imgSpiral = new Image("img/SPIRALE.jpg");
        imgWall = new Image("img/MUR.png");
        imgStar = new Image("img/ETOILE.jpg");
        images = new ImageView[board.LARGEUR][board.HAUTEUR];
        
        GridPane gridPane = new GridPane();
        
        for (int x = 0; x < board.LARGEUR; x++) {
            for (int y = 0; y < board.HAUTEUR; y++) {
                images[x][y] = new ImageView();
                gridPane.add(images[x][y], x, y);
            }
        }
        
        /*
        Observer observateurPacman = new Observer() {
            @Override
            public void update(Observable entite, Object _arg) {
                dessiner();
            }
        };
        */
        /*
        for (Case unecase : board.listeEntites()) {
            entite.addObserver(observateurPacman);
        }
        */
        
        StackPane stackPane = new StackPane();
        //BorderPane border = new BorderPane();
        
        stackPane.getChildren().add(gridPane);
        Scene scene = new Scene(stackPane, 486, 492, Color.LIGHTPINK);
        
       // Scene scene = new Scene(stackPane,(board.LARGEUR * IMG_TAILLE), (board.HAUTEUR * IMG_TAILLE+50));
        
        stage.setTitle("CASSE_TETE_LIGNES !");
        stage.setScene(scene);
        stage.show();
        
        /*
        stackPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (null != code) switch (code) {
                    case Z:
                        grille.joueur.deciderDirection(Direction.HAUT);
                        break;
                    case S:
                        grille.joueur.deciderDirection(Direction.BAS);
                        break;
                    case Q:
                        grille.joueur.deciderDirection(Direction.GAUCHE);
                        break;
                    case D:
                        grille.joueur.deciderDirection(Direction.DROITE);
                        break;
                    case I:
                        grille.fanjoueur.deciderDirection(Direction.HAUT);
                        break;
                    case K:
                        grille.fanjoueur.deciderDirection(Direction.BAS);
                        break;
                    case J:
                        grille.fanjoueur.deciderDirection(Direction.GAUCHE);
                        break;
                    case L:
                        grille.fanjoueur.deciderDirection(Direction.DROITE);
                        break;
                    default:
                        break;
                      
                }
            }
        });
        */
        
        //gridPane.requestFocus();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        //board.lancerJeu();
    }
    
    /*
    private synchronized void dessiner() {
        dessinerCarte();
        dessinerEntites();
    }
    
    private synchronized void dessinerCarte() {
        for (int x = 0; x < board.LARGEUR; x++) {
            for (int y = 0; y < board.HAUTEUR; y++) {
                switch (board.caseEn(x, y)) {
                    case WALL:
                        images[x][y].setImage(imgMur);
                        break;
                    case VIDE:
                        images[x][y].setImage(imgVide);
                        break;
                    case GOMME:
                        images[x][y].setImage(imgGomme);
                        break;
                    case SUPER_GOMME:
                        images[x][y].setImage(imgSuperGomme);
                        break;
                    default:
                        images[x][y].setImage(imgVide);
                        break;
                }
                images[x][y].setRotate(0);
            }
        }
    }
    
    private synchronized void dessinerEntites() {
        for (Entite entite : grille.listeEntites()) {
            if (entite.estVivant() && entite instanceof Pacman) {
                images[entite.getX()][entite.getY()]
                        .setImage(imgPacman);
                images[entite.getX()][entite.getY()]
                        .setRotate(entite.getDirection().angleDegres());
            } else if (entite.estVivant() && entite instanceof Fantome) {
                images[entite.getX()][entite.getY()]
                        .setImage((grille.joueur.getSuper()) ?
                                imgFantomeSuper : imgFantome);
            } else if (entite.estVivant() && entite instanceof FantomeJoueur) {
                images[entite.getX()][entite.getY()]
                        .setImage((grille.joueur.getSuper()) ?
                                imgFantomeSuper : imgFantome);
            }
        } 
        
    }
    */
        
        
    public static void main(String[] args) {
        launch(args);
    }
}
