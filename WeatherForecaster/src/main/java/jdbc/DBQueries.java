package jdbc;

public class DBQueries {


    /**********************************************************************************************************************************************
                                *SELECTS*
     ********************************************************************************************************************************************/

    public static final String selectLastDay = "select Day_Date, Month_Date, Year from dates\n" +
            " ORDER BY id_Dates DESC LIMIT 1;";

    public static final String selectLastDayID = "SELECT id_Dates from dates\n" +
            " ORDER BY id_Dates DESC LIMIT 1;";

    public static final String  selectOneDay = "SELECT * from dates dt\n" +
            "inner join temperature tr\n" +
            "on dt.id_Dates = tr.id_Dates_FK\n" +
            "where Day_Date = ? and Month_Date = ? and Year = ? ;";

    public static final String selectDaysRange = "SELECT * from dates dt\n" +
            "inner join temperature tr\n" +
            "on  dt.id_Dates = tr.id_Dates_FK\n" +
            "WHERE (Year, Month_Date, Day_Date) >= (?, ?, ?) \n" +
            "  and (Year, Month_Date, Day_Date) <= (?, ?, ?);";

    public static final String selectAllDays = "SELECT * FROM dates";

    public static final String selectAllDaysTemps = "SELECT * FROM dates d \n" +
            "INNER JOIN temperature t \n" +
            "ON d.id_Dates = t.id_Dates_FK \n" +
            "ORDER BY id_Dates;";

    public static final  String selectPassword = "SELECT password from passwords;";  //    public static final  String selectPassword = "SELECT password from passwords where password LIKE \"?\";";

    public static final String selectAllTemperatures = "SELECT * FROM temperature;";

    public static final String selectAllMaxTemps = "SELECT maxTemperature, id_Dates_FK FROM temperature";

    public static final String selectAllMinTemps = "SELECT minTemperature, id_Dates_FK FROM temperature";

    public static final String selectIDTemp = "SELECT * FROM temperature WHERE id_Dates_FK = ?;";

    public static final String selectIDDate = "SELECT * FROM dates WHERE id_Dates = ?;";


    /**********************************************************************************************************************************************
                                        *INSERTS*
     ********************************************************************************************************************************************/

    public static final String insertDay = " INSERT INTO dates (Day_Name, Day_Date, Month_Name, Month_Date, Year)"
            + " values (?, ?, ?, ?, ?)";

    public static final String insertTemperature = " INSERT INTO temperature ( maxTemperature, minTemperature, id_Dates_FK )"
            + " values (?, ?, ?)";

    /**********************************************************************************************************************************************
                                        *UPDATES*
     ********************************************************************************************************************************************/


    public static final String updateMinTemperature = "UPDATE temperature SET minTemperature = ? where id_Dates_FK = ?;";

    public static final String updateMaxTemperature = "UPDATE temperature SET maxTemperature = ? where id_Dates_FK = ?;";

    public static final String updateDay = "UPDATE dates SET Day_Date = ? where id_Dates = ?;";

    public static final String updateMonth = "UPDATE dates SET Month_Date = ? where id_Dates = ?;";

    public static final String updateYear = "UPDATE dates SET Year = ? where id_Dates = ?;";

    public static final String updatePassword = "UPDATE passwords SET password = ? WHERE password LIKE ?;";


}
