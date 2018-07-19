package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertsDB {

    private Connection connection;
    private PreparedStatement preparedStmt;
    private SelectsDB select;
    private ChecksDB db;

    /*
    Constructor to initialize connection and objects.
     */
    public InsertsDB(Connection connection, SelectsDB select, ChecksDB db) {
        this.connection = connection;
        this.select = select;
        this.db = db;
    }

    /*
    Inserting into database table "dates" dates of  : "Day_Name, Date, Month".
    Inserting only unique days.
     */
    public boolean insertDayDate(PageDayDates pageDates){
        try {
            if (db.checkDates()) {
                System.out.println("Inserting day dates...");
                preparedStmt = connection.prepareStatement(DBQueries.insertDay);
                preparedStmt.setString(1, pageDates.getDayName());
                preparedStmt.setInt(2, pageDates.getDayDate());
                preparedStmt.setString(3, pageDates.getMonthName());
                preparedStmt.setInt(4, pageDates.getMonthDate());
                preparedStmt.setInt(5, pageDates.getYear());

                // execute the prepared statement
                preparedStmt.execute();

                System.out.println("Day date successful added!");
                return true;
            }
            else System.out.println("Sorry, you cant add this day.");
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }

    /*
    Inserting day temperatures into database table "list".
    Data is: "maxTemperature, minTemperature, id_Dates_FK"
     */
    public void insertTemperatures(PageDayDates pageDates){
        try {
            // create the mysql insert prepared statement
            preparedStmt = connection.prepareStatement(DBQueries.insertTemperature);

            preparedStmt.setString (1,pageDates.getMaxTemp());
            preparedStmt.setString (2,pageDates.getMinTemp());
            preparedStmt.setString (3,select.selectLastDayID());

            // execute the prepared statement
            preparedStmt.execute();
            System.out.println("Temperatures info successfully added!");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

}
