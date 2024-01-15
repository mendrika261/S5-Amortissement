package mg;

public class Utils {
    public static double round(double value, int nbDecimals) {
        double factor = Math.pow(10, nbDecimals);
        return Math.round(value * factor) / factor;
    }

    public static double round(double value) {
        return round(value, 2);
    }
}
