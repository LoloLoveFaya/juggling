@FunctionalInterface
public interface PersonneSupplier {
    Personne createInstance(String nom, String prenom);
}
