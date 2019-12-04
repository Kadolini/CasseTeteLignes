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
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


/**
 *
 * @author Askia Abdel Kader
 */
public class CasseTete extends Application {
   
    private static final int IMG_TAILLE = 32;

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
    private Game game;

    @Override
    public void start(Stage stage) throws IOException {
        game = new Game();
        //board = new Board(3,3,"../levels/level1.txt");
        imgUP = new Image("img/VERTICALE.png");
        imgLEFT = new Image("img/HORIZONTALE.png");
        imgDOWNLEFT = new Image("img/HAUTGAUCHE.png");
        imgUPLEFT = new Image("img/BASGAUCHE.png");
        imgUPRIGHT = new Image("img/BASDROIT.png");
        imgDOWNRIGHT = new Image("img/HAUTDROIT.png");
        imgEMPTY = new Image("img/VIDE.png");
        imgSquare = new Image("img/CARRE.png");
        imgTriangle = new Image("img/TRIANGLE.png");
        imgSpiral = new Image("img/SPIRALE.png");
        imgWall = new Image("img/MUR.png");
        imgStar = new Image("img/ETOILE.png");
        images = new ImageView[game.getBoard().getLargeur()][game.getBoard().getHauteur()];
        
        GridPane gridPane = new GridPane();
        
        gridPane.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        
        for (int x = 0; x < game.getBoard().getLargeur(); x++) {
            for (int y = 0; y < game.getBoard().getHauteur(); y++) {
                images[x][y] = new ImageView();
                gridPane.add(images[x][y], x, y);
                gridPane.setStyle("-fx-border-color: white;");
                
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
        
        //StackPane stackPane = new StackPane();
        BorderPane stackPane = new BorderPane();
        
        stackPane.getChildren().add(gridPane);
        //Scene scene = new Scene(stackPane, 486, 492, Color.LIGHTPINK);
        
        
        Scene scene = new Scene(stackPane);
        
        stage.setTitle("CASSE_TETE_LIGNES !");
        stage.setScene(scene);
        stage.show(); 
        /*********************************************************************/
        
         // retourne une liste unidimensionnelle
        ObservableList<Node> observables = gridPane.getChildren(); 
        //On associe à chaque noeud des événements.
        observables.stream().map((obs) -> {
            
            
            obs.setOnDragDetected((MouseEvent event) -> {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = obs.startDragAndDrop(TransferMode.ANY);
                
                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
                
                game.startDD(gridPane.getRowIndex(obs), gridPane.getColumnIndex(obs));
                System.out.println("Symbole detecté");
                event.consume();
            });
            return obs;            
        }).map((obs) -> {
            obs.setOnDragEntered((DragEvent event) -> {
                event.acceptTransferModes(TransferMode.ANY);
                game.enterDD(gridPane.getRowIndex(obs),gridPane.getColumnIndex(obs));
                System.out.println("Vous etes dans une nouvelle case");
                event.consume();
            });
            return obs;            
        }).map((obs) -> {
            obs.setOnDragOver((DragEvent event) -> {
                // System.out.println("on glisse dans le noeud");
                event.acceptTransferModes(TransferMode.ANY);
                
                event.consume();
            });
            return obs;
        }).forEachOrdered((obs) -> {
           
            
            obs.setOnDragDropped((DragEvent event) -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                game.stopDD();
                System.out.println("Souris relachée");
                event.setDropCompleted(success);
                event.consume();
            });
        });
        
        /**********************************************************************************/
        
        
        /**********************************************************************************/
        gridPane.requestFocus();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        //game.lancerJeu();
        draw();
    }
    
    private synchronized void draw() {
        drawGrid();
       // drawRoute();
    }
    
    private synchronized void drawGrid() {
        for (int x = 0; x < game.getBoard().getLargeur(); x++) {
            for (int y = 0; y < game.getBoard().getHauteur(); y++) {
                if(game.getBoard().getOnlySymbols()[x][y] != null){
                    switch (game.getBoard().getOnlySymbols()[x][y].getSymbol()) {
                        case WALL:
                            images[x][y].setImage(imgWall);
                            break;
                        case STAR:
                            images[x][y].setImage(imgStar);
                            break;
                        case TRIANGLE:
                            images[x][y].setImage(imgTriangle);
                            break;
                        case SQUARE:
                            images[x][y].setImage(imgSquare);
                            break;
                        case SPIRAL:
                            images[x][y].setImage(imgSpiral);
                            break;
                        default :
                            break;
                    }

                }
                else {
                    images[x][y].setImage(imgEMPTY);
                }
            }
        }
    }
    
    /*
    private synchronized void drawRoute() {
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
