
import java.lang.annotation.Annotation;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

    public static void main(String[] args) {

        List<Personne> liste = new ArrayList<>();

        liste.add(new Personne("Lolo", 28));
        liste.add(new Personne("Zoizoi au choco", 27));
        liste.add(new Personne("Bigandi Assima", 30));
        liste.add(new Personne("Eric Ably", 23));

        List<Personne> listeFiltre = liste.stream()
                .filter(p -> p.getAge() >= 30)
                .collect(Collectors.toList());
        listeFiltre.stream().forEach(System.out::println);

        double ageMoyen = liste.stream().collect(Collectors.averagingInt(p -> p.getAge()));
        System.out.println("Age moyen : " + ageMoyen);

        Integer ageTotal = liste.stream().reduce(0, (somme, p) -> somme += p.getAge(), (s1, s2) -> s1 + s2);
        System.out.println("Age Total " + ageTotal);

        //parallèle stream
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println(pool.getParallelism());

        Arrays.asList("bibi", "bobo", "baba", "bebe", "tete", "tuitui")
                .parallelStream()
                .forEach(s -> System.out.println((s + " : " + Thread.currentThread().getName())));

        Traitement t1 = (Ressource r) -> r.getStatus();


        //Application des Predicate
        Predicate<Ressource> resssourceDispo = (Ressource r) -> r.estDisponible();

        Stream.of(new Ressource(true), new Ressource(false), new Ressource(true))
                .filter(resssourceDispo)
                .forEach(System.out::println);


        //Appliquer des functions avec Java 8
        Function<Integer, Double> calculVersFahrenheit = x -> new Double(x * 9 / 5 + 32);

        System.out.println(calculVersFahrenheit.apply(100));

        Function<List<Integer>, Integer> somme = listee -> {
            int total = 0;

            for (Integer i : listee) {
                total += i;
            }
            return total;
        };

        Integer valeur = somme.apply(Arrays.asList(3, 8, 7, 9, 10));
        System.out.println("Valeur de retour :" + valeur);

        // Les consommateurs et les fournisseurs
        Consumer consommateur = System.out::println;
        consommateur.accept("Fabien est un bon gar");
        consommateur.accept("Papa et maman");

        Stream.of("Fabien", "Xavier", "Dimitri").forEach(consommateur);

        //Fournisseur
        Stream.of(new Personne("Fabien", 45), new Personne("Xavier", 67))
                .forEach(p -> affichePersonne(() -> p.getAge()));

        //Les  Bifunctions
        BiFunction<String, String, Integer> longueur = (s1, s2) -> {
            return s1.length() + s2.length();
        };
        longueur.apply("Lolo", "Papa et maman");

        //BiPredicate
        BiPredicate<String, String> estPlusgand = (s1, s2) -> {
            return s1.length() > s2.length();
        };
        estPlusgand.test("Papa", "maman");

        // -- Les dates dans Java 8
        // -- Deux classe dates
        // -- ses classse ne contenait les même infos
        // -- il n'y avait pas de cohérence dans la classe date DateFormat
        // -- non thread safe
        // -- la gestiob des zones

        //Recuperer la date du jour
        LocalDate dt = LocalDate.now();
        System.out.println(dt);

        LocalTime dtt = LocalTime.now();
        System.out.println(dtt);

        LocalDateTime ddtt = LocalDateTime.now();
        System.out.println(ddtt);

        LocalDate zdt = LocalDate.now(ZoneId.of("Europe/Paris"));
        System.out.println(zdt);

        LocalTime zdtt = LocalTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(zdtt);

        ZonedDateTime zdttt = ZonedDateTime.of(ddtt, ZoneId.of("Asia/Tokyo"));
        System.out.println(zdttt);


        //Systèmes de temps qui ne sont pas iso
        Set<Chronology> chronos = Chronology.getAvailableChronologies();
        for (Chronology c : chronos) {
            ChronoLocalDate dts = c.dateNow();
            System.out.println("id " + c.getId() + " date " + dts.toString());
        }

        JapaneseDate jd = JapaneseDate.now();
        System.out.println("Japanese date " + jd);

        //Personnalisation du format d'affichage de la date
        LocalDate personDate = LocalDate.now();
        System.out.println("Affichage personnalisé " + personDate.format(DateTimeFormatter.ofPattern("d::MM::uuuu")));
        System.out.println("Affichage personnalisé " + personDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDate iiu = LocalDate.parse("2015-09-01");
        System.out.println(iiu);

        LocalDate uui = LocalDate.parse("3::août::2015", DateTimeFormatter.ofPattern("d::MM::uuuu"));
        System.out.println(uui);

        //Recuperée les annotation de la classe personne
        Annotation[] annotations = Personne.class.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }

        //Lambda stream functionnal interface date
        //reference de methode
        //Methode par défaut
        //annotation repetée
        //flux
        //Java FX permet de faire dans Java ce qu'on peut faire en android aussi

        //PersonneSupplier supplier = Personne::new;
        //Personne personne = supplier.creerInstance("nom1", "prenom1");
        //System.out.println(personne);
    }

    public static void affichePersonne(Supplier s) {
        System.out.println(s.get());
    }
}
