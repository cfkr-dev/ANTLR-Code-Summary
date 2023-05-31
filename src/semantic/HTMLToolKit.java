package semantic;

import semantic.element.element_interfaces.ProgramElement;

import java.util.List;

public class HTMLToolKit {

    public static String identRefMaker (String ref, String ident) { // para el uso (donde se pulsa)

        return HTMLToolKit.refLinker(ref, ident);

    }

    public static String identRefGenerator (String ref, String ident) { // de las declaraciones de variables, donde se ancla

        return HTMLToolKit.refGenerator(ref, HTMLToolKit.identMaker(ident));

    }

    public static String identMaker (String ident) { // lo demas que ni crean ancla ni se pulsan

        return "<SPAN CLASS=\"ident\">" + ident + "</SPAN>";

    }

    public static String cteMaker (String cte) {

        return "<SPAN CLASS=\"cte\">" + cte + "</SPAN>";

    }

    public static String palresMaker (String palres) {

        return "<SPAN CLASS=\"palres\">" + palres + "</SPAN>";

    }

    public static String refGenerator (String ref, String text) {

        return "<A NAME=\"" + ref + "\">" + text + "</A>";

    }

    public static String refLinker (String ref, String text) {

        return "<A HREF=\"#" + ref + "\">" + text + "</A>";

    }

    public static <E extends ProgramElement> String betweenBrakets (List<E> inside) {

        StringBuilder HTMLAux = new StringBuilder("{<br>\n|&emsp;\t");

        for (E line : inside) {

            String aux = line.toHTMLLong().replace("\n", "\n|&emsp;\t");

            HTMLAux.append(aux);

        }

        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.deleteCharAt(HTMLAux.length()-1);
        HTMLAux.append("}");

        return HTMLAux.toString();

    }

}
