package mg;

public class LigneAmortissement {
    private int annee;
    private double valeurBrute;
    private double tauxAmortissement;
    private double amortissementCumuleDebutAnnee;
    private double dotation;
    private double amortissementCumuleFinAnnee;
    private double valeurNette;

    public LigneAmortissement(int annee, double valeurBrute, double tauxAmortissement, double amortissementCumuleDebutAnnee, double dotation, double amortissementCumuleFinAnnee, double valeurNette) {
        setAnnee(annee);
        setValeurBrute(valeurBrute);
        setTauxAmortissement(tauxAmortissement);
        setAmortissementCumuleDebutAnnee(amortissementCumuleDebutAnnee);
        setDotation(dotation);
        setAmortissementCumuleFinAnnee(amortissementCumuleFinAnnee);
        setValeurNette(valeurNette);
    }

    // Getters and setters
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        if (annee < 0) {
            throw new IllegalArgumentException("L'année doit être positive");
        }
        this.annee = annee;
    }

    public double getValeurBrute() {
        return valeurBrute;
    }

    public void setValeurBrute(double valeurBrute) {
        if (valeurBrute < 0) {
            throw new IllegalArgumentException("La valeur doit être positive");
        }
        this.valeurBrute = valeurBrute;
    }

    public double getTauxAmortissement() {
        return tauxAmortissement;
    }

    public void setTauxAmortissement(double tauxAmortissement) {
        if (tauxAmortissement < 0) {
            throw new IllegalArgumentException("Le taux doit être positif");
        }
        this.tauxAmortissement = tauxAmortissement;
    }

    public double getAmortissementCumuleDebutAnnee() {
        return amortissementCumuleDebutAnnee;
    }

    public void setAmortissementCumuleDebutAnnee(double amortissementCumuleDebutAnnee) {
        if (amortissementCumuleDebutAnnee < 0) {
            throw new IllegalArgumentException("L'amortissement cumulé doit être positif");
        }
        this.amortissementCumuleDebutAnnee = amortissementCumuleDebutAnnee;
    }

    public double getDotation() {
        return dotation;
    }

    public void setDotation(double dotation) {
        if (dotation < 0) {
            throw new IllegalArgumentException("La dotation doit être positive");
        }
        this.dotation = dotation;
    }

    public double getAmortissementCumuleFinAnnee() {
        return amortissementCumuleFinAnnee;
    }

    public void setAmortissementCumuleFinAnnee(double amortissementCumuleFinAnnee) {
        if (amortissementCumuleFinAnnee < 0) {
            throw new IllegalArgumentException("L'amortissement cumulé doit être positif");
        }
        this.amortissementCumuleFinAnnee = amortissementCumuleFinAnnee;
    }

    public double getValeurNette() {
        return valeurNette;
    }

    public void setValeurNette(double valeurNette) {
        if (valeurNette < 0) {
            throw new IllegalArgumentException("La valeur doit être positive");
        }
        this.valeurNette = valeurNette;
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%.2f\t\t%.2f\t\t\t\t%.2f\t\t\t\t\t\t\t\t%.2f\t\t\t%.2f\t\t\t\t\t\t\t%.2f",
                annee, valeurBrute, tauxAmortissement, amortissementCumuleDebutAnnee, dotation, amortissementCumuleFinAnnee, valeurNette);
    }
}
