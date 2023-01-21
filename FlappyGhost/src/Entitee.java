import javafx.scene.image.Image;

/**
 * Classe mere des objets qui sont affiches dans le jeu.
 * Implemente l'interface updatable qui met a jour les
 * attributs de l'objet.
 */
public abstract class Entitee implements Updatable {
    protected double x, y; // position
    protected double r; // rayon de l objet

    protected double vx = 0, vy = 0; // vitesse
    protected double ax = 0, ay = 0; // acceleration
    protected Image image;



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }
    
     
        
    
}
