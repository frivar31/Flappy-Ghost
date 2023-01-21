import javafx.scene.image.Image;

/**
 * Classe qui regroupe les donnees et instances d objets du jeu.
 * Constitue le modele principal de l'achitecture MVC pour cette application.
 * implemente l'interface updatable pour mettre a jour les donnees du jeu en
 * fonction du temps ecoule.
 */
public class GameData implements Updatable {

    private static final int LARGEUR = 640, HAUTEUR = 400;
    private static int vitesse;
    private int score, obstacleCounter, obstaclePasse;
    private double tempsEcoule;
    private Boolean debug, pause;

    Obstacle [] obstacleArray;
    public static Image[] imagesObstacles = new Image[27];
    Background arrierePlan;
    Fantome ghost;
    
    /**
     * Constructeur du modele principal
     * initialise les donnees et cree les instances d'objets necessaire pour 
     * faire une nouvelle parite.
     */
    public GameData() {
        this.score = 0;
        this.obstacleCounter = 0;
        this.obstaclePasse =0;
        this.tempsEcoule = 0;
        GameData.vitesse = 120;
        this.obstacleArray = new Obstacle [3];
        this.arrierePlan = new Background();
        this.ghost = new Fantome(LARGEUR / 2, HAUTEUR / 2);
        this.debug = false;
        this.pause = false;
        for(int i = 0; i < 27; i++){
            GameData.imagesObstacles[i] = new Image("/" + i +".png");
        }
    }

    /**
     * Met a jour l'arriereplan, le fantome et les obstacles
     * en fonction du temps ecoule. Genere de nouveaux obstacles
     * aux 3 secondes.Verifie les collisions avec les obstacles
     * et met a jour le pointage du jeu.
     * @param dt temps ecoule depuis le dernier appel de la methode.
     */
    public void update(double dt){
        tempsEcoule += dt ;
        // cree un nouvel obstacle chaque 3 secondes ou tout de suite s'il n'y en a pas.
        if(tempsEcoule >= 3 || obstacleCounter == 0){

            int obstacleCounter2 = obstacleCounter % obstacleArray.length;
            int type = (int)(Math.random()*3);
            switch(type){
                case 0:
                    obstacleArray[obstacleCounter2] = new Obstacle();
                    break;
                case 1:
                    obstacleArray[obstacleCounter2] = new ObstacleSin();
                    break;
                case 2:
                    obstacleArray[obstacleCounter2] = new ObstacleQuantique();
                    break;
                

            }
            
            obstacleCounter++;
            tempsEcoule = 0;}

        
        arrierePlan.update(dt);
        ghost.update(dt);

        // update la position des obstacles et update le score, la vitesse du jeu
        // et la gravitee.
        for(int i = 0; i < Math.min(obstacleCounter, obstacleArray.length); i++){
            obstacleArray[i].update(dt);
            obstacleArray[i].intersects(ghost);
            if(obstacleArray[i].isHoldsPoints()){
                if(ghost.checkPoints(obstacleArray[i])){
                    score += 5;
                    obstaclePasse++;
                    if(obstaclePasse == 2){
                        GameData.vitesse += 15;
                        ghost.setAy(ghost.getAy() + 15);
                        obstaclePasse = 0;
                    }
                }
            }

        }
    }

    public static int getLARGEUR() {
        return LARGEUR;
    }

    public static int getHAUTEUR() {
        return HAUTEUR;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getObstacleCounter() {
        return obstacleCounter;
    }

    public void setObstacleCounter(int obstacleCounter) {
        this.obstacleCounter = obstacleCounter;
    }

    public static int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        GameData.vitesse = vitesse;
    }

    public double getTempsEcoule() {
        return tempsEcoule;
    }

    public void setTempsEcoule(double tempsEcoule) {
        this.tempsEcoule = tempsEcoule;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getPause() {
        return pause;
    }

    public void setPause(Boolean pause) {
        this.pause = pause;
    }

    public Obstacle[] getObstacleArray() {
        return obstacleArray;
    }

    public void setObstacleArray(Obstacle[] obstacleArray) {
        this.obstacleArray = obstacleArray;
    }

    public Background getArrierePlan() {
        return arrierePlan;
    }

    public void setArrierePlan(Background arrierePlan) {
        this.arrierePlan = arrierePlan;
    }

    public Fantome getGhost() {
        return ghost;
    }

    public void setGhost(Fantome ghost) {
        this.ghost = ghost;
    }

    public static int getLargeur() {
        return LARGEUR;
    }

    public static int getHauteur() {
        return HAUTEUR;
    }

    public int getObstaclePasse() {
        return obstaclePasse;
    }

    public void setObstaclePasse(int obstaclePasse) {
        this.obstaclePasse = obstaclePasse;
    }

    public static Image[] getImagesObstacles() {
        return imagesObstacles;
    }

    public static void setImagesObstacles(Image[] imagesObstacles) {
        GameData.imagesObstacles = imagesObstacles;
    }
}
