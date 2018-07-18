package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Рома on 30.04.2018.
 */
public class PatternForTemp {

    /*
    Getting 2 ints with only 2 numbs of String
     */
    private Pattern patternTwoNumbs = Pattern.compile("\\d{2}");
    private Pattern patternOneNumb = Pattern.compile("\\d{1}");


    public String getNumber(String date){
        Matcher matcher = null;
        try {
            if (date.length() >= 9){
             matcher = patternTwoNumbs.matcher(date);
            }else if (date.length() < 9){
                matcher = patternOneNumb.matcher(date);
            }
        if (matcher.find()){
                for (int i = 0; i < date.length(); i++){
                    if (date.charAt(i) == '+'){
                        return "+" + matcher.group();
                    }
                    else if (date.charAt(i) == '-'){
                        return "-" + matcher.group();
                    }
                }
        }
            //throw new Exception();
        } catch (Exception e) {
            System.out.println("Exception : " + e);
            e.printStackTrace();
        }
        return null;
    }
}
