package helpers;

public class PriceFormatter {

    private static String reverseString(String value) {
        StringBuilder sb = new StringBuilder(value);
        sb.reverse();
        return sb.toString();
    }

    public static String addSpaces(String value) {
        value = reverseString(value);
        value = value.replaceAll("...", "$0 ");
        value = reverseString(value);
        return value;
    }
}