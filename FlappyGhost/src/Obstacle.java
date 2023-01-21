import javafx.scene.image.Image;

/**
 * Classe qui contient les donnees et methodes pour
 * mettre a jour les donnees concernant les obstacles du jeu.
 * Gere la dynamique de collision et redefini la methode 
 * update pour mettre a jour les donnees en fonction du temps ecoule.
 */
public class Obstacle extends Entitee {

    private boolean collision;
    private boolean holdsPoints;    

    /**
     * Constructeur
     * Genere une image au hazard pour l'obstacle cree
     * et lui attribut une position en x juste en dehors du canvas.
     * 
     */
    public Obstacle() {
        this.image = GameData.imagesObstacles[(int)(Math.random()*27)];
        // genere un rayon entre 10 et 45;
        this.r = ((Math.random() * 35) + 10);
        // cree l'obstacle juste en dehors de l'ecran
        this.x = GameData.getLARGEUR() + r;
        // pour que l'obstacle.y soit dans l'ecran
        this.y = Math.max((Math.min((Math.random() * (GameData.getHAUTEUR() - r)),  (GameData.getHAUTEUR() - r))), 0 + r);
        this.holdsPoints = true;
        this.collision = false;
        
    }

    /**
     * Mets a jour la vitesse et la position de l'obstacle
     * 
     * @param dt difference de temps depuis le dernier appel.
     */
    @Override
    public void update(double dt) {

        
        // mise a jour de la vitesse
        vx = -GameData.getVitesse();
        // calcul de la nouvelle position
        x += dt * vx;
                
        }
       
        
    
    /**
     * Verifie s'il y a collision avec l'entitee passe en parametre
     * @param other l'entitee a verifier.
     */
    public void intersects(Entitee other){
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dCarre = dx *dx + dy * dy;

        if(dCarre < (this.r + other.r) * (this.r + other.r))
            collision = true;
       
    }
    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isHoldsPoints() {
        return holdsPoints;
    }

    public void setHoldsPoints(boolean holdsPoints) {
        this.holdsPoints = holdsPoints;
    }

    
    
}
