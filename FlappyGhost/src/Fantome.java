import javafx.scene.image.Image;

/**
 * Classe qui fait partie du modele de l'architecture MVC,
 * herite de la classe Entitee pour faire le personnage principal
 * du jeu. Regroupe toutes les donnees et methodes sur ces donnees 
 * en qui concerne le fantome. 
 */
public class Fantome extends Entitee {
    
    

    /**
     * Constructeur du fantome. Initialise le rayon a 30 et l'aceleration en y(
     * gravitee) a 500.
     * @param x la position en x ou le fantome sera cree.
     * @param y la position en y ou le fantome sera cree.
     */
    public Fantome(double x, double y) {
        this.x = x;
        this.y = y;
        this.r = 30;
        this.ay = 500;
        this.image = new Image("/ghost.png");
        
       
        
    }
    /**
     * Met a jour la vitesse et la position du fantome
     * s'assure qu il est a l'interieur de bornes de l'ecran et
     * qu'il rebondit s'il leur touche.
     * 
     * @param dt difference de temps depuis le dernier appel
     */
    @Override
    public void update(double dt) {
       // mise a jour de la vitesse
       vx += dt * ax;
       vy += dt * ay;

       // vitesse de chute terminale
       if(vy > 300)
            vy = 300;    
       
       // calcul de la nouvelle position
       x += dt * vx;
       y += dt * vy;

       //fait rebondir le fatome s'il se cogne sur les parrois
       if (x + r > GameData.getLARGEUR() || x - r < 0) {
        vx *= -1;
        }
        if (y + r > GameData.getHAUTEUR() || y - r < 0) {
        vy *= -1;
        }

        // Force x et y à être dans les bornes
        x = Math.min(x, GameData.getLARGEUR() - r);
        x = Math.max(x, r);
        y = Math.min(y, GameData.getHAUTEUR() - r);
        y = Math.max(y, r);
    }
    
    /**
     * Methode qui fait sauter le fantome en changeant 
     * la vitesse verticale.
     */
    public void jump(){
        vy = -300;
    }

    

    
    /**
     * Verifie si l'obstacle passe en parametre a ete depasse 
     * dans le jeu.
     * @param other obstacle a comparer. Les obstacles passes
     * ici comme parametres sont filtres par le modele pour 
     * s'assurer que le joueur n'a pas deja obtenu de points pour
     * l'avoir depasse.
     * @return retourne true si le cote droit de l'obstacle est a une
     * position x plus petite que le cote gauche du fantome.
     */
    public boolean checkPoints(Obstacle other){
        if((other.getX() + other.r) < (this.x - this.r)){
            other.setHoldsPoints(false);
            return true;
        }
        return false;
    }
    
}
