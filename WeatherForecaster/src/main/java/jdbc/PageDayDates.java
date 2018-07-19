package jdbc;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pattern.PatternForTemp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Рома on 30.04.2018.
 */
public class PageDayDates {

    private Document document;
    private PatternForTemp pattern;

    private int dayDate;
    private int monthDate;
    private int year;
    private String maxTemp;
    private String minTemp;
    private String dayName;
    private String monthName;

    /*
    PageDayDates(Document document) - this is constructor to initialize document and
    object of PatternForTemp class. Method - setDates(), set all class attributes.
     */
    public PageDayDates(Document document){
        this.document = document;
        pattern = new PatternForTemp();
        setDates();
    }

    /*
    Getters and Setters for temps & date.
     */

    public Document getDocument() {
        return document;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public int getDayDate() {
        return dayDate;
    }

    public void setDayDate(int dayDate) {
        this.dayDate = dayDate;
    }

    public String getMonthName() {

        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(int monthDate) {
        this.monthDate = monthDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*
    setMonthNumberMap(String month) - that is a method which create map of months, where
    month name ("января" - "декабря") is a key, month date (1 - 12) - is value.
    Return a number of month using key,return type: int.
     */
    public int setMonthNumberMap(String month){
        Map <String, Integer> map = new HashMap<>();
        map.put("января", 1);
        map.put("февральФевраля", 2);
        map.put("марта", 3);
        map.put("апреля", 4);
        map.put("мая", 5);
        map.put("июня", 6);
        map.put("июля", 7);
        map.put("августа", 8);
        map.put("сентября", 9);
        map.put("октября", 10);
        map.put("ноябрря", 11);
        map.put("декабря", 12);
        return map.get(month);
    }

    public int setYearNumber(){
        DateFormat date = new SimpleDateFormat("yyyy");
        java.util.Date today = Calendar.getInstance().getTime();
        return Integer.parseInt(date.format(today));
    }


    /**********************************************************************************************************************************************
     *GETTING PAGE DATE*
     ********************************************************************************************************************************************/

    /*
    Get a list of text name days from html code (for 7 days)
    like this:
    [Понедельник, Вторник, Среда, Четверг, Пятница, Суббота, Воскресенье]
     */
    public List<String> getDayNameTEXT() {
        List<String> days = new ArrayList<>();
        for (Element day : getDayNames()) {
            days.add(day.getElementsByClass("day-link").text());
        }
        return days;
    }

    /*
    The same one with classes "date" like at method "selectDayNamesTEXT()"
    to get a list of dates:
    [30, 01, 02, 03, 04, 05, 06]
    */
    public List<String> getDayDateTEXT() {
        List<String> dates = new ArrayList<>();
        for (Element date : getDayDates()) {
            dates.add(date.getElementsByClass("date").text());
        }
        return dates;
    }

    /*
    The same one with classes "date" like at method "selectDayNamesTEXT()"
    to get a list of month names:
    [апреля, мая, мая, мая, мая, мая, мая]
     */
    public List<String> getMonthTEXT() {
        List<String> months = new ArrayList<>();
        for (Element month : getMonth()) {
            months.add(month.getElementsByClass("month").text());
        }
        return months;
    }

    /*
    The same but for min temperature TXT.
     */
    public List<String> getMinTempTEXT() {
        List<String> temp = new ArrayList<>();
        for (Element min : getMinTemperature()) {
            temp.add(min.getElementsByClass("min").text());
        }
        return temp;
    }

    /*
    The same for max temperature TXT.
     */
    public List<String> getMaxTempTEXT() {
        List<String> temp = new ArrayList<>();
        for (Element max : getMaxTemperature()) {
            temp.add(max.getElementsByClass("max").text());
        }
        return temp;
    }

    /*
    ***Private Methods***
    */

    /*
    Getting elements of classes with name "day-link" ang than
    return code lines with day names (for 7 days)
    like this:
    <p class="day-link" data-link="//sinoptik.ua/погода-люблин/2018-04-30">Понедельник</p>
    */
    private Elements getDayNames() {
        Elements dayNames = getDocument().getElementsByClass("day-link");
        return dayNames;
    }

    /*
    The same one with classes "date" like at method "getDayNames()"
    at line 48 to get dates:
    <p class="date ">30</p>
    */
    private Elements getDayDates() {
        Elements dayDate;
        dayDate = getDocument().getElementsByClass("date");
        return dayDate;
    }

    /*
    The same one with classes "month" like at method "getDayNames()"
    at line 48. To get month for 7 days:
    <p class="month">апреля</p>
     */
    private Elements getMonth() {
        Elements month;
        month = getDocument().getElementsByClass("month");
        return month;
    }

    /*
    The same but for min temperature: class="min".
     */
    private Elements getMinTemperature() {
        Elements elements = getDocument().getElementsByClass("min");
        return elements;
    }

    /*
    The same for max temperature: class="max".
     */
    private Elements getMaxTemperature() {
        Elements elements = getDocument().getElementsByClass("max");
        return elements;
    }


    /**********************************************************************************************************************************************/

    /*
    From methods: getMaxTempTEXT().get(0) and getMinTempTEXT().get(0) which return a list of temps,
    I getting 1st elements and then using method - pattern.getNumber(), to get a number from String.
    setDayName(getDayNameTEXT().get(0)) & setMonthName(getMonthTEXT().get(0)) - sets day date like
    'понедельник' ... 'неделя', and month like 'января' - 'декабря'.
    setDayDate(Integer.parseInt(getDayDateTEXT().get(0))) & setMonthDate(setMonthNumberMap(getMonthTEXT().get(0))) -
    this methods setting day & month date like '1-31' & '1-12', but for month i using method setMonthNumberMap(...),
    to get from the map a month number (value) by month name (key).
    For temperatures as parameter
    */
    private void setDates(){
        try {
            setMaxTemp(pattern.getNumber(getMaxTempTEXT().get(0)));
            setMinTemp(pattern.getNumber(getMinTempTEXT().get(0) + " "));
            setDayDate(Integer.parseInt(getDayDateTEXT().get(0)));
            setDayName(getDayNameTEXT().get(0));
            setMonthDate(setMonthNumberMap(getMonthTEXT().get(0)));
            setMonthName(getMonthTEXT().get(0));
            setYear(setYearNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
