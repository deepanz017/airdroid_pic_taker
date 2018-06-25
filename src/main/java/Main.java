import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String style = "position: absolute; width: 500px; height: 650px; left: 390px; top: 38.5px; display: block; z-index: 9;";
        String patternHeight = "height: (.+?)px";
        String patternWidth = "width: (.+?)px";
        Pattern patternH = Pattern.compile(patternHeight);
        Pattern patternW = Pattern.compile(patternWidth);
        Matcher matcherHeight = patternH.matcher(style);
        Matcher matcherWidth = patternW.matcher(style);
        boolean heightMatches = matcherHeight.find();
        boolean widthMatches = matcherWidth.find();
        String height = matcherHeight.group(2);
        String width = matcherWidth.group(2);
    }
}
