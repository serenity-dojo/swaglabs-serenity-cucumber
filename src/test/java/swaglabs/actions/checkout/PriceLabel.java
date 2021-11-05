package swaglabs.actions.checkout;

public class PriceLabel {
    public static String extractPrice(String label) {
        return label.substring(label.indexOf("$"));
    }
}
