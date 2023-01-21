import javafx.scene.image.Image;

/**
 * Classe qui regroupe les donnees et methodes sur ces donnees 
 * pour ce qui concerne l'arriere plan du jeu.
 * Implemente l'interface updatable pour mettre a jour
 * l'arriere plan en fonction du temps ecoule.
 */
public class Background implements Updatable {

    private double windowWidth = GameData.getLARGEUR();
    private double x = windowWidth, x2 = 0;
    private Image background = new Image("/bg.png");
    
    /**
     * Met a jour la position et la vitesse des deux images 
     * de l'arriere plan pour les faire defiler
     * @param dt difference de temps depuis le dernier appel
     */
    @Override
    public void update(double dt) {
        x -= dt * GameData.getVitesse();
        x2 = (x - windowWidth); // fait dependre x2 de x pour pas qu il y ait de decalage entre a la jonction des 2 backgrounds.
        if(x <= -windowWidth) {
            x = windowWidth;
            x2 = 0;}
            if(x <= 0) x2 = x + windowWidth;    
        }

        public Image getImage() {
            return background;
        }
        
        public void setImage(Image background) {
            this.background = background;
        }
        
        public double getX() {
            return x;
        }
    
    
        public void setX(double x) {
            this.x = x;
        }
    
    
        public double getX2() {
            return x2;
        }
    
    
        public void setX2(double x2) {
            this.x2 = x2;
        }

        public double getWindowWidth() {
            return windowWidth;
        }

        public void setWindowWidth(double windowWidth) {
            this.windowWidth = windowWidth;
        }

        public Image getBackground() {
            return background;
        }

        public void setBackground(Image background) {
            this.background = background;
        }
}
