package jdbc;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChecksDB {

    private Connection connection;
    private Statement statement;
    private ResultSet result;
    private PreparedStatement preparedStmt;

    /*
    Constructor to create JDBC connection & statement.
     */
    public ChecksDB(Connection connection) {
        try {
            this.connection = connection;
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("SQL Exception : " + e);
            e.printStackTrace();
        }
    }
    /*
   Method selectLastDate() - get last day record from DB and getTodayDate() - get today date and check in DB
   if this day was added to DB - show Massage
   if not to - adding this day and temperatures dates to DB.
    */
    public boolean checkDates() {
        try {
            if (selectLastDate().equals(getTodayDate())) {
                System.out.println("This day was added!");
                System.out.println("Adding only unique days!");
                System.out.println("Try at the next day =)");
                return false;
            } else {
                System.out.println("This day can be added to DataBase =)");
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        return true;
    }
    /*
    getTodayDate() - getting today date from system like "31/12/2018" and return date as String.
     */
    public String getTodayDate() {
        DateFormat date = new SimpleDateFormat("d/M/yyyy");
        Date year = Calendar.getInstance().getTime();
        String fullDate = date.format(year);
        System.out.println("Today`s date: " + fullDate + ".");
        return fullDate;
    }

    /*
    selectLastDate() - getting last date record from DataBase and return String of date like "31/12/2018".
     */
    public String selectLastDate() {
        try {
            result = statement.executeQuery(DBQueries.selectLastDay);
            // result.next(); - to get a record from DB
            result.next();
            String date = result.getString("Day_Date") + "/" + result.getString("Month_Date") + "/" + result.getString("Year");
            return date;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return null;
    }

    /*
    This method select a record from table 'temperature', column 'id_Dates_FK',
    where 'id_Dates_FK' = int id from method parameter.
    If DB has this ID - return true, if NOT - return false;
     */
    public boolean checkIDTemp(int id) {
        try {
            preparedStmt = connection.prepareStatement(DBQueries.selectIDTemp);
            preparedStmt.setInt(1, id);
            result = preparedStmt.executeQuery();
            if (result.next()) {
                System.out.println("Updating temperature...");
                return true;
            } else
                System.out.println("Data Base haven`t temperature with: " + id + " ID.");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }

    /*
    This method select a record from table 'dates', column 'id_Dates',
    where 'id_Dates' = int id from method parameter.
    If DB has this ID - return true, if NOT - return false;
     */
    public boolean checkIDDate(int id) {
        try {
            preparedStmt = connection.prepareStatement(DBQueries.selectIDDate);
            preparedStmt.setInt(1, id);
            result = preparedStmt.executeQuery();
            if (result.next()) {
                return true;
            } else
                System.out.println("Data Base haven`t day with: " + id + " ID.");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }


}
