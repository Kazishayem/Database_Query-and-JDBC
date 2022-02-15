import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConn {

    private static final String TheTableSQL = "create table usertest(\r\n" + "  id  int(),\r\n" +
            "  FirstName varchar(50),\r\n" + "  LastName varchar(50),\r\n" + "  Address varchar(50),\r\n" + ");";

    public static void main(String[] args) throws SQLException {
        dbConn createTableConn = new dbConn();
        createTableConn.createTable();
    }

    public void createTable() throws SQLException {

        System.out.println(TheTableSQL);

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/assign?useSSL=false", "root", "1260");

                Statement statement = connection.createStatement();) {

            statement.execute(TheTableSQL);
            System.out.println("Connected");

        } catch (SQLException e) {

            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}