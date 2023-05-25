package semantic.utils;

public class HTMLHelper {
    public static String generateTabulations(int indentationLevel) {
        return "\t".repeat(Math.max(0, indentationLevel));
    }
}
