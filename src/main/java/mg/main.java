package mg;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        Amortissement amortissement = new Lineaire(
                25500,
                5,
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 15));

        System.out.println(amortissement);
    }
}
