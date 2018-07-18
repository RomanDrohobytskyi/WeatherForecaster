package jdbc;

import access.Password;
import input.Input;

import java.sql.*;
import java.util.Arrays;

public class UpdatesMethodsDB {

    private Connection connection;
    private PreparedStatement preparedStmt;

    public UpdatesMethodsDB(Connection connection) {
        this.connection = connection;
    }

    /************************************************************************************************************************
                                                *UPDATES*
     ************************************************************************************************************************/

    public void updateMinTemp(int minTemp, int ID){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.updateMinTemperature);
            preparedStmt.setInt (1, minTemp);
            preparedStmt.setInt (2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    /**************************************************************************************************************************/

    public void updateMaxTemp(String maxTemp, int ID){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.updateMaxTemperature);
            preparedStmt.setString (1, maxTemp);
            preparedStmt.setInt (2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    /**************************************************************************************************************************/

    public void updateDay(int day, int ID){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.updateDay);
            preparedStmt.setInt (1, day);
            preparedStmt.setInt (2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    /**************************************************************************************************************************/

    public void updateMonth(int month, int ID){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.updateMonth);
            preparedStmt.setInt (1, month);
            preparedStmt.setInt (2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    /**************************************************************************************************************************/

    public void updateYear(int year, int ID){
        try {
            preparedStmt = connection.prepareStatement(DBQueries.updateYear);
            preparedStmt.setInt (1, year);
            preparedStmt.setInt (2, ID);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    /**************************************************************************************************************************/

    public void updatePassword(Input input, Password password, char[] passwordArray){
        try {
            if (Arrays.equals(password.enterPassword(input), passwordArray)) {
                System.out.println("Enter new password: ");
                char[] newPassword = input.inputChar();
                preparedStmt = connection.prepareStatement(DBQueries.updatePassword);
                preparedStmt.setString(1, String.valueOf(newPassword));
                preparedStmt.setString(2, String.valueOf(passwordArray));
                preparedStmt.executeUpdate();
                System.out.println("Password has been updated!");
            }
            else System.out.println("Wrong password!");
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }

    }

}
