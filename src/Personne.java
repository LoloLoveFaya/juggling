
//Annotation repetée
@Modifiee(auteur = "Fabien")
@Modifiee(auteur = "Lolo")
public class Personne {
    private int age;
    private String nom;
    private String prenom;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom + " : " + age;
    }
}
