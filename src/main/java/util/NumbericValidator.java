package util;

public final class NumbericValidator {

    public static boolean isNumeric(String str)
    {
        return str.matches("\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
