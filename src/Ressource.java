public class Ressource {
    boolean status;

    boolean estDisponible;

    public boolean getStatus() {
        return status;
    }

    public boolean estDisponible() {
        return estDisponible;
    }

    public Ressource(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }
}
