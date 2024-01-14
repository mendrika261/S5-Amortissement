package mg;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        Lineaire lineaire = new Lineaire(
                25500,
                5,
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 15));

        /*
            Ireto fonction anakiroa ireto mi retourne ligneAmortissement:
            System.out.println(lineaire.getLigneAmortissement(2024));
            System.out.println(lineaire.getLignesAmortissement());
        */
        System.out.println(lineaire);

        Degressif degressif = new Degressif(
                45000,
                5,
                LocalDate.of(2024, 6, 29),
                LocalDate.of(2024, 7, 1));
        degressif.setCoefficient(1.75); // variable prédéfinie apetraka arakaraky ny durée de vie

        System.out.println(degressif);
    }
}
