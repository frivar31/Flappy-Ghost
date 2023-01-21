/*  Auteur: Louis-Pierre Bastien
    Matricule: p0886038
    Coequipier : Tarek Mekki Daouadji
    Matricule: 20174482

    Description:
    Jeu FlappyGhost developpe a l'aide de la librairie JavaFX.
    Cet application utilise l'architecture MVC
    Deroulement:
    Le joueur incarne un fantôme qui se promène dans un niveau rempli d’obstacles. Il n’y a pas d’objectif
    autre que d’avancer le plus longtemps possible dans le niveau sans toucher d’obstacle.
    Le déplacement horizontal du joueur se fait automatiquement vers la droite. Le seul déplacement
    possible est de faire “sauter” le fantôme en appuyant sur la barre espace.
    Pour rendre le jeu de plus en plus difficile à mesure que le score monte, pour chaque deux obstacles
    dépassés, la vitesse du fantôme accélère et la gravité augmente. L'application termine en appuyant sur la
    touche ESCAPE.

 */
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Vue de l'application
 * Gere l'affichage et la presentation de l'interface graphique
 * du jeu. Communique avec le controlleur pour lui acheminer
 * les differentes actions de l'utilisateur.
 */
public class FlappyGhost extends Application {


    private Controller gameController;
    public final int WIDTH = 640, HEIGHT = 440;
    Canvas canvas = new Canvas(WIDTH,HEIGHT-40);
    GraphicsContext context = canvas.getGraphicsContext2D();
 
    
    // Définition des boutons
    Button buttonPause = new Button("Pause");
    CheckBox debugCheckBox = new CheckBox("Mode Debug");
    Text texte1 = new Text(String.format("Score: 0"));

    /**
     * Methode utilise pour afficher un arriere plan qui defile. Cette methode
     * est appelee par le controleur de l'application periodiquement.
     * @param background image de l'arriere a afficher.
     * @param x1 position en x de l 1ere image dans le canvas
     * @param x2 position en x de l 2e image dans le canvas
     */
    public void afficheBackground(Image background, double x1, double x2){
        context.drawImage(background, x2, 0); // dessine le 1er background.
        context.drawImage(background, x1, 0);  // dessine le 2e background.
    }
    
    /**
     * Point d'entree du programe.
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        HBox menu = new HBox();
        primaryStage.getIcons().add(new Image("/ghost.png"));
       

        gameController = new Controller(this);

        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.setPadding(new Insets(7)); 
        
        menu.getChildren().addAll(buttonPause,debugCheckBox,texte1);
        
        // L’élément "Bottom" du BorderPane est le menu
        root.setBottom(menu);

        // empeche le boutton de prendre le focus quand on appuie sur SPACE
        root.getChildren().add(canvas);
        Platform.runLater(() -> {
            canvas.requestFocus();
            });

       
        // communication des actions de l'utilisateur sur les bouttons avec le controlleur.  
         
        buttonPause.setOnMouseClicked((event) -> {
            gameController.pause();
            buttonPause.setText("Resume");
            canvas.requestFocus();});
        

        debugCheckBox.setOnMouseClicked((event) -> {
            gameController.debug();
            canvas.requestFocus();});

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Flappy Ghost");
        primaryStage.show();



        AnimationTimer timer = new AnimationTimer() {
            
            private long lastTime = 0;
                     
        /**
         * Redifinition de handle
         * ce code serat appeler a chaque frame.
         */
            @Override
        public void handle(long now){
            // traitement du cas special quand lastTime == 0;
            if (lastTime == 0){
                lastTime = now;
                return;
            }

            double deltaTime = (now - lastTime) * 1e-9;
             
            gameController.refresh(deltaTime);
            
            /**
             *  communique les touches pesee avec le controlleur
             * */ 
            scene.setOnKeyPressed((value) ->{
                if(value.getCode() == KeyCode.SPACE || value.getCode() == KeyCode.ESCAPE){
                    gameController.react(value);
                }
            });
            
            lastTime = now;    

        }


        };
        timer.start();
        
    }

    
    
}
