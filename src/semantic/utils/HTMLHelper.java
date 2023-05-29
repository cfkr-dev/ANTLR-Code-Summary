package semantic.utils;

public class HTMLHelper {
    public static String genTabs(int indentationLevel) {
        return "\t".repeat(Math.max(0, indentationLevel));
    }

    public static String genH(int hLevel, String text) {
        return "<h" + hLevel + ">" + text + "</h" + hLevel + ">";
    }

    public static String genSpan(String spanClass, String text) {
        return "<span class=\"" + spanClass + "\">" + text + "</span>";
    }

    public static String genA(String name, String text) {
        return "<a name=\"" + name + "\">" + text + "</a>";
    }

    public static String genA(String name) {
        return "<a name=\"" + name + "\"></a>";
    }

    public static String genAHref(String href, String text) {
        return "<a href=\"#" + href + "\">" + text + "</a>";
    }

    public static String genAHref(String href) {
        return "<a href=\"#" + href + "\"></a>";
    }

    public static String genBr(String tabs) {
        return "\n\n" + tabs + "<br/>\n\n";
    }

    public static String genBr(String newLineStart, String tabs, String newLineEnd) {
        return newLineStart + tabs + "<br/>" + newLineEnd;
    }

    public static String genHr(String tabs) {
        return "\n\n" + tabs + "<hr/>\n\n";
    }

    public static String genHr(String newLineStart, String tabs, String newLineEnd) {
        return newLineStart + tabs + "<hr/>" + newLineEnd;
    }
}
