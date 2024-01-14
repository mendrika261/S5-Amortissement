package mg;

import java.time.LocalDate;

public class Degressif extends Amortissement {
    public Degressif(double valeurBien, double anneeDeVie, LocalDate dateAchat, LocalDate dateMiseEnService) {
        super(valeurBien, anneeDeVie, dateAchat, dateMiseEnService);
    }

    public double getNbMoisUtilisationPremiereAnnee() {
        return 12 - getDateAchat().getMonthValue() + 1;
    }

    public int getTauxLineaire() {
        return (int) (100 / getTauxAmortissement());
    }

    @Override
    public int getAnneeFinAmortissement() {
        return (int) (getDateMiseEnService().getYear() + getAnneeDeVie()) - 1;
    }

    @Override
    public LigneAmortissement getLigneAmortissement(int annee) {
        if(annee < getDateMiseEnService().getYear() || annee > getAnneeFinAmortissement()) {
            throw new IllegalArgumentException("L'année doit être comprise entre " + getDateMiseEnService().getYear() + " et " + getAnneeFinAmortissement());
        }

        double amortissementCumuleDebutAnnee = 0;
        double dotation = getValeurBien() * getTauxAmortissement() / 100 * (getNbMoisUtilisationPremiereAnnee() / 12);
        if (annee > getDateMiseEnService().getYear()) {
            amortissementCumuleDebutAnnee = getLigneAmortissement(annee - 1).getAmortissementCumuleFinAnnee();
            if (annee > getAnneeFinAmortissement() - getTauxLineaire()) {
                dotation = getLigneAmortissement(getAnneeFinAmortissement() - getTauxLineaire()).getValeurNette() / getTauxLineaire();
            } else {
                dotation = getLigneAmortissement(annee-1).getValeurNette() * getTauxAmortissement() / 100;
            }
        }
        double amortissementCumuleFinAnnee = amortissementCumuleDebutAnnee + dotation;

        return new LigneAmortissement(
                annee,
                getValeurBien(),
                getTauxAmortissement(),
                amortissementCumuleDebutAnnee,
                dotation,
                amortissementCumuleFinAnnee,
                getValeurBien() - amortissementCumuleFinAnnee);
    }
}
