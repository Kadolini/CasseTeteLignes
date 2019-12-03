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
        
        //StackPane stackPane = new StackPane();
        BorderPane stackPane = new BorderPane();
        
        stackPane.getChildren().add(gridPane);
        //Scene scene = new Scene(stackPane, 486, 492, Color.LIGHTPINK);
        
        Scene scene = new Scene(stackPane,(board.LARGEUR * IMG_TAILLE), (board.HAUTEUR * IMG_TAILLE+50));
        
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
                
                board.startDD(gridPane.getRowIndex(obs), gridPane.getColumnIndex(obs));
                System.out.println("Symbole detecté");
                event.consume();
            });
            return obs;            
        }).map((obs) -> {
            obs.setOnDragEntered((DragEvent event) -> {
                event.acceptTransferModes(TransferMode.ANY);
                //---------------------------------------------------------
                final File file = new File("son/rail.mp3"); 
                final Media media = new Media(file.toURI().toString()); 
                final MediaPlayer mediaPlayer = new MediaPlayer(media); 
                mediaPlayer.play();
                //---------------------------------------------------------
                board.enterDD(this.gridPane.getRowIndex(obs), this.gridPane.getColumnIndex(obs));
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
                
               //---------------------------------------------------------
                final File file = new File("son/son.mp3"); 
                final Media media = new Media(file.toURI().toString()); 
                final MediaPlayer mediaPlayer = new MediaPlayer(media); 
                mediaPlayer.play();
                //---------------------------------------------------------
        
                
                board.stopDD();
                 
                System.out.println("Souris relachée");
                event.setDropCompleted(success);
                event.consume();
            });
        });
        
        /**********************************************************************************/
        
        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        board.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // TODO
            }
        });

        for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 5; row++) {

                final int fColumn = column;
                final int fRow = row;

                final Text t = new Text(" " + column + "-" + row + " ");
                tabText[column][row] = t;
                t.setFont(Font.font("Verdana", 25));
                
                
                t.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        Dragboard db = t.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();       
                        content.putString(""); // non utilisé actuellement
                        db.setContent(content);
                        event.consume();
                        m.startDD(fColumn, fRow);
                    }
                });

                t.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                        m.parcoursDD(fColumn, fRow);
                        event.consume();
                    }
                });
                
                t.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                        // attention, le setOnDragDone est déclenché par la source du Drag&Drop
                        
                        m.stopDD(fColumn, fRow);
                        
                    }
                });

                gPane.add(tabText[column][row], column, row);
            }
        }

        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        
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
    }
    
  /*  private synchronized void dessiner() {
        dessinerCarte();
        dessinerEntites();
    }
    
    private synchronized void dessinerCarte() {
        for (int x = 0; x < board.LARGEUR; x++) {
            for (int y = 0; y < board.HAUTEUR; y++) {
                if(board.getIndexOfGrid(x, y).getClass().getName() == "SymbolCase"){
                    switch (board.getIndexOfGrid(x, y)) {
                        case WALL:
                            images[x][y].setImage(imgMur);
                            break;
                        case STAR:
                            images[x][y].setImage(imgVide);
                            break;
                        case Symbol.TRIANGLE:
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
    }
    */
    /*
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
