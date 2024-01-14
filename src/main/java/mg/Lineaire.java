package mg;

import java.time.LocalDate;

public class Lineaire extends Amortissement {
    public Lineaire(double valeurBien, double anneeDeVie, LocalDate dateAchat, LocalDate dateMiseEnService) {
        super(valeurBien, anneeDeVie, dateAchat, dateMiseEnService);
    }

    public double getNbJourUtilisationPremiereAnnee() {
        return (NB_JOURS_MOIS - getDateMiseEnService().getDayOfMonth()) + (NB_MOIS_ANNEE - getDateMiseEnService().getMonthValue() - 1) * NB_JOURS_MOIS;
    }

    public double getDotation(int annee) {
        if (annee < getDateMiseEnService().getYear() || annee > getDateMiseEnService().getYear() + getAnneeDeVie()) {
            throw new IllegalArgumentException("L'année doit être comprise entre " + getDateMiseEnService().getYear() + " et " + (getDateMiseEnService().getYear() + getAnneeDeVie()));
        }

        if (annee == getDateMiseEnService().getYear()) {
            return getValeurBien() * getTauxAmortissement() / 100 * (getNbJourUtilisationPremiereAnnee() / NB_JOURS_ANNEE);
        } else if (annee == getDateMiseEnService().getYear() + getAnneeDeVie() ) {
            return getDotation(annee-1) - getDotation(getDateMiseEnService().getYear());
        } else {
            return getValeurBien() * getTauxAmortissement() / 100;
        }
    }

    @Override
    public LigneAmortissement getLigneAmortissement(int annee) {
        double amortissementCumuleDebutAnnee = 0;
        if (annee > getDateMiseEnService().getYear())
            amortissementCumuleDebutAnnee = getLigneAmortissement(annee-1).getAmortissementCumuleFinAnnee();
        double amortissementCumuleFinAnnee = amortissementCumuleDebutAnnee + getDotation(annee);

        return new LigneAmortissement(
                annee,
                getValeurBien(),
                getTauxAmortissement(),
                amortissementCumuleDebutAnnee,
                getDotation(annee),
                amortissementCumuleFinAnnee,
                getValeurBien() - amortissementCumuleFinAnnee);
    }

    @Override
    public LigneAmortissement[] getLignesAmortissement() {
        LigneAmortissement[] lignesAmortissement = new LigneAmortissement[(int) getAnneeDeVie() + 1];
        for (int i = getDateMiseEnService().getYear(); i <= getDateMiseEnService().getYear() + getAnneeDeVie(); i++) {
            lignesAmortissement[i - getDateMiseEnService().getYear()] = getLigneAmortissement(i);
        }
        return lignesAmortissement;
    }
}
