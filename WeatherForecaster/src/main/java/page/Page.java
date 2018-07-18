package page;

import access.Password;
import input.Input;
import jdbc.*;
import jdbcconnection.JDBCConnection;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static page.Constants.url_Page;

/**
 * Created by Рома on 29.04.2018.
 */
public class Page {

    private Connection connection;
    private Document document;
    private PageDayDates pageDayDates;
    private ChecksDB db;
    private UpdatesMethodsDB update;
    private InsertsMethodsDB insert;
    private SelectsMethodsDB select;
    private JDBCConnection jdbcConnectionObj ;

    private int day;
    private int month;
    private int year;

    private int dayEnd;
    private int monthEnd;
    private int yearEnd;

    private List list;
    private char[] password;

    /*
    setPage() - method to get a page-code from website.
    */
    public Page(){
        try {
            setPage();
            pageDayDates = new PageDayDates(getDocument());
            jdbcConnectionObj = new JDBCConnection();
            this.connection = jdbcConnectionObj.connectionJDBC();
            db = new ChecksDB(connection);
            update = new UpdatesMethodsDB(connection);
            select = new SelectsMethodsDB(connection, pageDayDates);
            insert = new InsertsMethodsDB(connection, select, db);
            list = new ArrayList();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Getter and Setter for document ( page code-element), getter for page title,
     getter for password.
     */
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getTitle() {
        return getDocument().title();
    }

    public Connection getConnection() {
        return connection;
    }

    /*
     db.selectPassword() - getting a password from DataBase and set password for
     this class attribute: private char[] password;
     */
    public char[] getPassword() {
        this.password = select.selectPassword();
        return password;
    }

    /*
    checkDay() - activate method - db.checkDates(); In ChecksDB class. Method
    checking today and last added day.
    */
    public void checkDay(){
        db.checkDates();
    }

    /************************************************************************************************************************
                                                *UPDATES*
     ************************************************************************************************************************/
    public void updateMinTemp(Input input){
        try {
            System.out.println("Enter day ID to update Min temperature: ");
            int id = input.inputNumber(0, 10000);
            System.out.println("Enter Min temperature ( -40 ... 40 ): ");
            int minTemp = input.inputNumber(-40, 40);

            if (db.checkIDTemp(id)) {
                update.updateMinTemp(minTemp, id);
                System.out.println("Min temperature has been updated.");
            }
            else System.out.println("Min temperature not updated!");
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public void updateMaxTemp(Input input){
        try {
            System.out.println("Enter day ID to update Max temperature: ");
            int id = input.inputNumber(0, 1000);
            System.out.println("Enter Max temperature ( -40 ... 40 ): ");
            int maxTemp = input.inputNumber(-40, 40);

            if (db.checkIDTemp(id)) {
                if (maxTemp > 0) {
                    update.updateMaxTemp("+" + maxTemp, id);
                    System.out.println("Max temperature updated.");
                } else
                    if (maxTemp < 0 ){
                    update.updateMaxTemp("-" + maxTemp, id);
                    System.out.println("Max temperature updated.");
                } else
                    if (maxTemp == 0 ){
                    update.updateMaxTemp("" + maxTemp, id);
                    System.out.println("Max temperature updated.");
                }
            } else System.out.println("Max temperature not updated!");
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public void updateBothTemps(Input input){
        updateMinTemp(input);
        updateMaxTemp(input);
    }

    public void updateDay(Input input){
        try {
            System.out.println("Enter how much days you wanna update ( 1 ... 100) : ");
            int number = input.inputNumber(0,100);
            for (int i = 0; i < number; i++) {
                System.out.println("Enter ID to update Day date (0 ... 1000) : ");
                int id = input.inputNumber(0, 1000);
                System.out.println("Enter day date (1 ... 31) : ");
                int day = input.inputNumber(1, 31);

                if (db.checkIDDate(id)) {
                    System.out.println("Updating day date...");
                    update.updateDay(day, id);
                    System.out.println("Day date has been updated.");
                }
            }
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public void updateMonth(Input input){
        try {
            System.out.println("Enter how much dates you wanna update (0 ... 100) : ");
            int number = input.inputNumber(0,100);
            for (int i = 0; i < number; i++) {
                System.out.println("Enter ID to update month number (0 ... 1000) : ");
                int id = input.inputNumber(0, 1000);
                System.out.println("Enter month number (1 ... 12) : ");
                int month = input.inputNumber(1, 12);

                if (db.checkIDDate(id)) {
                    System.out.println("Updating month date...");
                    update.updateMonth(month, id);
                    System.out.println("Month date has been updated.");
                }
            }
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public void updateYear(Input input){
        try {
            System.out.println("Enter how much years you wanna update (0 ... 100) : ");
            int number = input.inputNumber(0,100);
            for (int i = 0; i < number; i++) {
                System.out.println("Enter ID to update year number (0 ... 1000) : ");
                int id = input.inputNumber(0, 1000);
                System.out.println("Enter year number (0 ... 2018) : ");
                int year = input.inputNumber(1, 2018);

                if (db.checkIDDate(id)) {
                    System.out.println("Updating year date...");
                    update.updateYear(year, id);
                    System.out.println("Year date has been updated.");
                }
            }
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    public void updateAllDates(Input input){
        updateDay(input);
        updateMonth(input);
        updateYear(input);
    }

    public void updatePassword(Input input, Password password){
        update.updatePassword(input, password, select.selectPassword());
    }

    /**********************************************************************************************************************************************
                                        *GETTING DATES*
     ********************************************************************************************************************************************/

    public void getSelectedDay(Input number){
        System.out.println("Enter day ( 1 - 31 ): ");
        this.day = number.inputNumber(1, 31);
        System.out.println("Enter month ( 1 - 12 ):");
        this.month = number.inputNumber(1,12);
        System.out.println("Enter year ( 2000 - 2018 ): ");
        this.year = number.inputNumber(2000, 2018);
    }

    public void getSelectedRange(Input number){
        System.out.println("Enter first day ( 1 - 31 ): ");
        this.day = number.inputNumber(1, 31);
        System.out.println("Enter first month ( 1 - 12 ):");
        this.month = number.inputNumber(1,12);
        System.out.println("Enter first year ( 2000 - 2018 ): ");
        this.year = number.inputNumber(2000, 2018);

        System.out.println("Enter last day ( 1 - 31 ): ");
        this.dayEnd = number.inputNumber(1, 31);
        System.out.println("Enter last month ( 1 - 12 ):");
        this.monthEnd = number.inputNumber(1,12);
        System.out.println("Enter last year ( 2000 - 2018 ): ");
        this.yearEnd = number.inputNumber(2000, 2018);
    }

    /**********************************************************************************************************************************************/

    /*
    This method - insertDayDateIntoDB(), activate method insert.insertDayDate(pageDayDates) and check bool.
    If true, it`s men that this day is unique and can be added into DB, and activate method -
    insert.insertTemperatures(pageDayDates), which inserting data about temperature into DB.
     */
     public void insertDayDateIntoDB(){
        if (insert.insertDayDate(pageDayDates)) {
            System.out.println("Inserting temperatures...");
            insert.insertTemperatures(pageDayDates);
        }
    }

    /**********************************************************************************************************************************************
                                                    *PRINTS*
     ********************************************************************************************************************************************/

    public void printAllDays(){
        select.selectAllDays().forEach(element -> System.out.println(Constants.STARS + "\n" + element));
    }

    public void printAllMinTemps(){
        select.selectAllMinTemp().forEach(element -> System.out.println(Constants.STARS + "\n" + element));
    }

    public void printAllMaxTemps(){
        select.selectAllMaxTemp().forEach(element->System.out.println(Constants.STARS + "\n" + element));
    }

    public void printDaysTemps(){
        select.selectAllDaysWithTemp().forEach(element -> System.out.println(Constants.STARS + "\n" + element));
    }

    public void printAllTemps(){
        select.selectAllTemperatures().forEach(element->System.out.println(Constants.STARS + "\n" + element));
    }

    public void printSelectedDay(Input input){
        getSelectedDay(input);
        System.out.println(select.selectOneDayTemp(day, month, year));
    }

    public void printRangeDay(){
        select.selectRangeTemp(day, month, year, dayEnd, monthEnd, yearEnd)
        .forEach(day->System.out.println(Constants.STARS + "\n" + day));
    }

    public void printTodayWeather(){
       printTodayWeather(pageDayDates.getDayNameTEXT().get(0), pageDayDates.getDayDateTEXT().get(0),
               pageDayDates.getMonthTEXT().get(0), pageDayDates.getMaxTempTEXT().get(0), pageDayDates.getMinTempTEXT().get(0));
    }

    public void printWeekWeather(){
        printWeekWeather(pageDayDates.getDayNameTEXT(), pageDayDates.getDayDateTEXT(), pageDayDates.getMonthTEXT(),
                pageDayDates.getMaxTempTEXT(), pageDayDates.getMinTempTEXT());
    }

    public void printTitle(){
        System.out.println("Title: " + getTitle());
    }

    public void printWeekWeather(List<String> day, List<String> date, List<String> month, List<String> maxTemp, List<String> minTemp ){
        for (int i = 0; i < day.size() ; i++) {
            System.out.println(Constants.STARS);
            System.out.println(day.get(i) + " " + date.get(i) + " " + month.get(i));
            System.out.println(Constants.STARS);
            System.out.println("Температура:");
            System.out.println(Constants.STARS);
            System.out.println(maxTemp.get(i) + " " + minTemp.get(i));
        }
    }

    public void printTodayWeather(String day, String date, String month, String maxTemp, String minTemp){
        System.out.println(Constants.STARS);
        System.out.println(day + " " + date + " " + month);
        System.out.println(Constants.STARS);
        System.out.println("Температура:");
        System.out.println(Constants.STARS);
        System.out.println(maxTemp + " " + minTemp);
    }

    /*********************************************************************************************************************************************/
    /*
     Getting code-element of "sinoptic" page.
    */
    private void setPage(){
        try {
            System.out.println("Getting page code...");
            setDocument(Jsoup.parse(new URL(url_Page), 6000));
            System.out.println("Done!");
        }catch (IOException e){
            System.out.println("Sorry, can`t get page code of you`r website =(");
            System.out.println("Check internet connection.");
            System.out.println("Exception e: " + e);
        }
    }
    /*
    closeAll() - method which activate, closeAll(), method in ChecksDB class to close connection
    and statement after log out.
     */
    public void closeAll(){
       try{
           if (connection != null){
               connection.close();
           }
       }catch (Exception e){
           System.out.println("Exception: " + e);
       }
   }
    /**********************************************************************************************************************************************/

}