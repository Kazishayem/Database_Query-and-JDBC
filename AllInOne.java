// public class AllInOne {

// }

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllInOne {
    private static final String INSERT_USERS_SQL = "INSERT INTO usertest" +
            "  (id, name, email, country, password) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String UPDATE_USERS_SQL = "update usertest set name = ?,email = ? where id = ?;";
    // update
    private static final String DELETE_USERS_SQL = "delete from usertest where id = 3;";
    // delete

    public static void main(String[] argv) throws SQLException {
        AllInOne createTableExample = new AllInOne();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/assign?useSSL=false", "root", "1260");

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatementINSERT = connection.prepareStatement(INSERT_USERS_SQL)) { 
            preparedStatementINSERT.setInt(1, 3);
            preparedStatementINSERT.setString(2, "kaziassas");
            preparedStatementINSERT.setString(3, "kazisaas@gmail.com");
            preparedStatementINSERT.setString(4, "USAaSs");
            preparedStatementINSERT.setString(5, "123aSas4");
            System.out.println(preparedStatementINSERT);
            preparedStatementINSERT.executeUpdate();
        }
        
        catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
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
