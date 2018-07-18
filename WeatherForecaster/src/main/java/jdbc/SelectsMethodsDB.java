package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectsMethodsDB {

    private Connection connection;
    private ResultSet result;
    private Statement statement;
    private PreparedStatement preparedStmt;
    private PageDayDates pageDates;
    private List list;

    public SelectsMethodsDB(Connection connection, PageDayDates pageDates) {
        try {
            this.connection = connection;
            this.pageDates = pageDates;
            statement = connection.createStatement();
            list = new ArrayList();
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    /************************************************************************************************************************
     *SELECTS*
     ************************************************************************************************************************/

    public List<String> selectAllDays(){
        try {
            list.clear();
            result = statement.executeQuery(DBQueries.selectAllDays);
            while (result.next()) {
                list.add("Day ID: " + result.getString("id_Dates"));
                list.add("Day: " + result.getString("Day_Date"));
                list.add("Month: " + result.getString("Month_Date"));
                list.add("Year: " + result.getString("Year"));
            }
            return list;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /**************************************************************************************************************************/
    /*
    selectLastDayID() - getting last ID (id_Dates) of last inserted day from DataBase.
    ---- result.next(); - to get a record from DB ----
     */
    public String selectLastDayID(){
        try {
            result = statement.executeQuery(DBQueries.selectLastDayID);
            result.next();
            return result.getString("id_Dates");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /***********************************************************************************************/
    /*
    selectOneDayTemp(int day, int month, int year) - getting a day from DataBase.
    This day date (day, month, year) is inputted by user with keyboard.
    And then return String with all info about day - day date, temperatures.
     */
    public String selectOneDayTemp(int day, int month, int year){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.selectOneDay);

            preparedStmt.setInt (1, day);
            preparedStmt.setInt (2, month);
            preparedStmt.setInt (3, year);

            result = preparedStmt.executeQuery();
            result.next();

            return "ID: " + result.getString("id_Dates") + "\n"
                    +"At day: " + day + "." + month + "." + year + "\n"
                    + "Max temp: " +  result.getString("maxTemperature") + ". \n"
                    + "Min temp: " +  result.getString("minTemperature") + ". ";
        } catch (SQLException e) {
            System.out.println("DataBase haven`t this date... try again!");
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /***********************************************************************************************/
    /*
    selectRangeTemp(...) - getting range of dates from DataBase.
    This dates (dayStart ... yearEnd) is inputted by user.
    And then return String with all info about days - days date, temperatures.
     */
    public List<String> selectRangeTemp(int dayStart, int monthStart, int yearStart, int dayEnd, int monthEnd, int yearEnd){
        try {
            list.clear();
            preparedStmt = connection.prepareStatement(DBQueries.selectDaysRange);

            preparedStmt.setInt (1, yearStart);
            preparedStmt.setInt (2, monthStart);
            preparedStmt.setInt (3, dayStart);

            preparedStmt.setInt (4, yearEnd);
            preparedStmt.setInt (5, monthEnd);
            preparedStmt.setInt (6, dayEnd);

            result = preparedStmt.executeQuery();

            while (result.next()) {
                list.add("ID: " + result.getString("id_Dates") + ".\n"
                        + "At day: " + result.getString("Day_Date") + "."
                        + result.getString("Month_Date") + "."
                        + result.getString("Year") + "\n"
                        + "Max temp: " + result.getString("maxTemperature") + "\n"
                        + "Min temp: " + result.getString("minTemperature"));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("DataBase haven`t this date... try again!");
            System.out.println("Exception: " + e);
        }
        return null;
    }

    /***********************************************************************************************/
    /*

     */
    public List<String> selectAllTemperatures(){
        try{
            list.clear();
            result = statement.executeQuery(DBQueries.selectAllTemperatures);

            while (result.next()){
                list.add("ID: " + result.getString("id_Dates_FK"));
                list.add("Max temp: " + result.getString("maxTemperature"));
                list.add("Min temp: " + result.getString("minTemperature"));
            }
            return list;
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /***********************************************************************************************/
    /*

     */
    public List<String> selectAllMaxTemp(){
        try{
            list.clear();
            result = statement.executeQuery(DBQueries.selectAllMaxTemps);
            while (result.next()){
                list.add("ID: " + result.getString("id_Dates_FK"));
                list.add("Max temp: " + result.getString("maxTemperature"));
            }
            return list;
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /***********************************************************************************************/
    /*

     */
    public List<String> selectAllMinTemp(){
        try{
            list.clear();
            result = statement.executeQuery(DBQueries.selectAllMinTemps);
            while (result.next()){
                list.add("ID: " + result.getString("id_Dates_FK"));
                list.add("Min temp: " + result.getString("minTemperature"));
            }
            return list;
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /**************************************************************************************************************************/
    /*
     selectAllDaysWithTemp() - select all days and all temperatures from DB
     by
     INNER JOIN
     ON  d.id_Dates = t.id_Dates_FK.
     Adding all records to list and return this list.
     */
    public List selectAllDaysWithTemp(){
        try{
            list.clear();
            result = statement.executeQuery(DBQueries.selectAllDaysTemps);

            while (result.next()){
                list.add(result.getString("Day_Date") + "/"
                        + result.getString("Month_Date") + "/"
                        + result.getString("Year"));
                list.add("ID : " + result.getString("id_Dates"));
                list.add("Max temp: " + result.getString("maxTemperature"));
                list.add("Min temp: " + result.getString("minTemperature"));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    /*
    selectPassword() - getting a password, from DataBase, like inputted by user.
    And return char array of this password.
     */
    public char[] selectPassword() {
        try {
            result = statement.executeQuery(DBQueries.selectPassword);
            result.next();
            String password = result.getString("password");
            return password.toCharArray();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }


}
