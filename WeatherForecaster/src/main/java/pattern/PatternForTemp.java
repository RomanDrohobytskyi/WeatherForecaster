package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Рома on 30.04.2018.
 */
public class PatternForTemp {

    /*
    patternTwoNumbs - pattern to get 2 numbers from text.
    patternOneNumb - to get only 1 number from text;
     */
    private Pattern patternTwoNumbs = Pattern.compile("\\d{2}");
    private Pattern patternOneNumb = Pattern.compile("\\d{1}");

    /*
    This method get int from text , and return a number as String with '+' or '-' at start.
    Text example: "макс. +25°" or "мин. +15° ".
     */
    public String getNumber(String data){
        Matcher matcher = null;
        try {
            // I get text(String data), if text length = 10 ("макс. +25°" length = 10)
            // I use  patternTwoNumbs because I have temp with 2 numbs.
            // But if text length = 9 ("макс. +5°" length = 9)
            // I use pattern patternOneNumb, because temp is with only 1 numb.
            if (data.length() > 9){
             matcher = patternTwoNumbs.matcher(data);
            }else if (data.length() <= 9){
                matcher = patternOneNumb.matcher(data);
            }
        if (matcher.find()){
                for (int i = 0; i < data.length(); i++){
                    // Check a temp symbol '+' or '-', return symbol with temp ("+25", "-5").
                    if (data.charAt(i) == '+'){
                        return "+" + matcher.group();
                    }
                    else if (data.charAt(i) == '-'){
                        return "-" + matcher.group();
                    }
                }
        }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
            e.printStackTrace();
        }
        return null;
    }
}
