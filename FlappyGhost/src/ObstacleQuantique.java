
/**
 * Classe qui herite de la classe Obstacle
 * redefinit la methode update pour que
 * l'obstacle chande de position de maniere aleatoire.
 */
public class ObstacleQuantique extends Obstacle{

    private double tempsPasse;

    /**
     * Constructeur sans parametre
     * initialise la valeure tempsPasse a 0.
     */
    public ObstacleQuantique() {
        tempsPasse = 0;
    }

    /**
     * redefinition de la methode update
     * fait appel a la methode quantique
     * chaque .2 secondes.
     */
    @Override
    public void update(double dt) {
        tempsPasse += dt;
        super.update(dt);
        if(tempsPasse>= 0.2){
            quantique();
            tempsPasse = 0; 
        }
    }

    /**
     * methode qui change la position d'un obstacle quantique
     * de + ou moins 30 pixels dans une direction aleatoire.
     */
    public void quantique(){
        double random = (Math.random()* 60)-30;
            int random2 = (int)(Math.random()*2);
            switch(random2){
                case 0:
                x +=random;
                return;
                case 1:
                y+= random;
                return;
    }
    }
}
