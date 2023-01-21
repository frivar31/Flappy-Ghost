/**
 * Classe derivee de la classe Obstacle
 * Redefinie la methode update pour que
 * la position en y de l'obstacle varie de
 * maniere sinusoidale.
 */
public class ObstacleSin extends Obstacle {
    private double tempsPasse;

    /**
     * Constructeur sans parametre
     * initialise la valeure tempsPasse a 0.
     */
    public ObstacleSin() {
        tempsPasse = 0;
    }

    /**
     * Redefinition de la methode update pour avoir un
     * movement sinusoidale
     * @param dt la variation de temps depuis le dernier appel
     * de la methode.
     */
    @Override
    public void update(double dt) {
        tempsPasse += dt;
        super.update(dt);
        y +=  0.8 * Math.sin(tempsPasse*Math.PI*2);
    }
}
