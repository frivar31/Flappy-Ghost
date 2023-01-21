import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**Controlleur pricipal du jeu, fait le
 * pont entre le modele et la vue du jeu.
 * Appele les differentes methodes pour mettre a 
 * jour les donnees et faire afficher les objets.
 */
public class Controller {

    private GameData modele;
    private FlappyGhost vue;
    
    /**
     * Constructeur du controlleur, prends une vue
     * en parametre et soccupe de generer un modele.
     * @param flappyGhost la vue de l'application
     */
    public Controller(FlappyGhost flappyGhost) {
        this.vue = flappyGhost;
        this.modele = new GameData();
    }

    /**
     * Fait les appelles necessaires au modele pour mettre les
     * donnees du jeu a jour et ensuite fait appele a la vue pour 
     * faire l'affichage. reagit aussi aux inputs de l'utilisateur
     * transmit par la vue de l'application.
     * @param dt le temps ecoule depuis le dernier appel de la methode.
     */
    public void refresh(double dt){
        if(!modele.getPause()){
            vue.buttonPause.setText("Pause");
            modele.update(dt);
            vue.texte1.setText("Score: " + modele.getScore());
            clearOld();
    
            int loopLimit = Math.min(modele.getObstacleCounter(), modele.obstacleArray.length);
            
            if(!modele.getDebug()){
                afficheEntitee(modele.ghost);
                for(int i = 0; i < loopLimit; i++){
                    if(modele.obstacleArray[i].isCollision()){
                        reInitialisation();
                        return;
                    }
                    afficheEntitee(modele.obstacleArray[i]);
                }
                
            }
            else{

                for(int i = 0; i < loopLimit; i++){
                    Color laCouleur;
                    if(modele.obstacleArray[i].isCollision())
                        laCouleur = Color.RED;
                    else{
                        laCouleur = Color.YELLOW;// couleur des obstacles en debug
                    }
                    afficheRondCouleur(modele.obstacleArray[i], laCouleur);
                    modele.obstacleArray[i].setCollision(false);//reset le test de collision pour le prochain frame
                }
                  
                afficheRondCouleur(modele.ghost, Color.BLACK); // rond du fantome
            }
        }
    }
    
    /**
     * Methode qui fait fait sauter le fantome(SPACE) ou qui termine
     * l'application(ESCAPE) dependament du keyEvent recu en parametre. 
     * @param keyPressed la touche pesee par l'utilisateur 
     */
    public void react(KeyEvent keyPressed){

        KeyCode key = keyPressed.getCode();

        switch (key){
            case SPACE :
                modele.ghost.jump();
                return;
            case ESCAPE :
                Platform.exit();
                return;
            default :
                return; 
        }
        
    }
    
    /**
     * Reagit a l'input de l'utilisateur pour mettre le modele en mode
     * pause ou resume la partie.
     */
    public void pause() {
        if(!modele.getPause()){
            modele.setPause(true);
        }
        else{
            modele.setPause(false);
        }
    }
    
    /**
     * Reagit a l'input de l'utilisateur pour mettre le modele en mode
     * debug ou en mode normal. Fait les appels a la vue pour l'affichage 
     * des ronds en deebug ou des images en mode normal.
     */
    public void debug() {
        if(!modele.getDebug()){

            clearOld();
            for(int i= 0; i <Math.min(modele.getObstacleCounter(), modele.obstacleArray.length); i++){
                afficheRondCouleur(modele.obstacleArray[i], Color.YELLOW);
            }
            afficheRondCouleur(modele.ghost, Color.BLACK); // rond du fantome
            modele.setDebug(true);
        }
        else{
            clearOld();
            for(int i= 0; i <Math.min(modele.getObstacleCounter(), modele.obstacleArray.length); i++)
                afficheEntitee(modele.obstacleArray[i]);
                afficheEntitee(modele.ghost);
                modele.setDebug(false);
            }
        }
        /**
         * Reinitialise le jeu a 0.Debut d'une nouvelle partie.
         */
        public void reInitialisation(){
            modele.setScore(0);
            modele.setVitesse(120);
            modele.ghost.setAy(500);
            modele.ghost.setY(GameData.getHAUTEUR()/2);
            modele.arrierePlan.setX(modele.arrierePlan.getWindowWidth());
            modele.arrierePlan.setX2(0);
            modele.setObstacleCounter(0);
            modele.setTempsEcoule(0);
            modele.obstacleArray = new Obstacle[3];
            modele.setObstaclePasse(0);
        }
    /**
     * Methode qui efface l'ancien frame et qui affiche l'arriere plan 
     * */    
    public void clearOld(){
        vue.context.clearRect(0,0, vue.WIDTH, vue.HEIGHT);
        vue.afficheBackground(modele.arrierePlan.getImage(), modele.arrierePlan.getX(), modele.arrierePlan.getX2());
    }
    /**
     * Methode pour l'affichage des entitees du jeu en mode debug.
     * @param entitee l'entitee a afficher
     * @param couleur la couleur du rond de l'entitee en mode debug
     */
    public void afficheRondCouleur(Entitee entitee, Color couleur){
        vue.context.setFill(couleur);
        vue.context.fillOval(entitee.getX() -entitee.getR(), entitee.getY() - entitee.getR(), entitee.getR() * 2, entitee.getR() * 2);
    }
    /**
     * Methode pour l'affichage des entitees du jeu avec leur image.
     * @param entitee l'entitee a afficher.
     */
    public void afficheEntitee(Entitee entitee){
        vue.context.drawImage(entitee.getImage(), entitee.getX() -entitee.getR(), entitee.getY() - entitee.getR(), entitee.getR() * 2, entitee.getR() * 2);
    }
    
}
