package mg;

import java.time.LocalDate;
import java.util.List;

public abstract class Amortissement {
    private double valeurBien;
    private double anneeDeVie;
    private LocalDate dateAchat;
    private LocalDate dateMiseEnService;

    private double coefficient = 1;
    public static int NB_MOIS_ANNEE = 12;
    public static int NB_JOURS_MOIS = 30;
    public static int NB_JOURS_ANNEE = NB_MOIS_ANNEE * NB_JOURS_MOIS;

    public Amortissement(double valeurBien, double anneeDeVie, LocalDate dateAchat, LocalDate dateMiseEnService) {
        setValeurBien(valeurBien);
        setAnneeDeVie(anneeDeVie);
        setDateAchat(dateAchat);
        setDateMiseEnService(dateMiseEnService);
    }

    public abstract LigneAmortissement getLigneAmortissement(int annee);

    public List<LigneAmortissement> getLignesAmortissement() {
        List<LigneAmortissement> lignesAmortissement = new java.util.ArrayList<>();
        int annee = getDateMiseEnService().getYear();
        while (getLigneAmortissement(annee).getAmortissementCumuleFinAnnee() < getValeurBien()) {
            lignesAmortissement.add(getLigneAmortissement(annee));
            annee++;
        }
        lignesAmortissement.add(getLigneAmortissement(annee));
        return lignesAmortissement;
    }

    public double getTauxAmortissement() {
        return 100/getAnneeDeVie()*getCoefficient();
    }

    public abstract int getAnneeFinAmortissement();

    // Getters and setters
    public double getValeurBien() {
        return valeurBien;
    }

    public void setValeurBien(double valeurBien) {
        if (valeurBien < 0) {
            throw new IllegalArgumentException("La valeur doit être positive");
        }
        this.valeurBien = valeurBien;
    }

    public double getAnneeDeVie() {
        return anneeDeVie;
    }

    public void setAnneeDeVie(double anneeDeVie) {
        if (anneeDeVie < 0) {
            throw new IllegalArgumentException("L'année doit être positive");
        }
        this.anneeDeVie = anneeDeVie;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        if (dateAchat == null) {
            throw new IllegalArgumentException("La date ne doit pas être nulle");
        }
        this.dateAchat = dateAchat;
    }

    public LocalDate getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(LocalDate dateMiseEnService) {
        if (dateMiseEnService == null) {
            throw new IllegalArgumentException("La date ne doit pas être nulle");
        }
        if (dateMiseEnService.isBefore(getDateAchat())) {
            throw new IllegalArgumentException("La date de mise en service doit être après la date d'achat");
        }
        this.dateMiseEnService = dateMiseEnService;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        if (coefficient < 0) {
            throw new IllegalArgumentException("Le coefficient doit être positif");
        }
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        t.append("Année\t Valeur brute\t Taux d'amortissement\t Amortissement cumulé début année\t Dotation\t Amortissement cumulé fin année\t Valeur nette\n");
        for (int i = 0; i < getLignesAmortissement().size(); i++) {
            t.append(getLignesAmortissement().get(i).toString()).append("\n");
        }
        return t.toString();
    }
}
