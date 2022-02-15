// public class update {

// }

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class update {

    private static final String UPDATE_USERS_SQL = "update student_info set name = ? where rollNo = ?;";

    public static void main(String[] argv) throws SQLException {
        update updateStatementExample = new update();
        updateStatementExample.updateRecord();
    }

    public void updateRecord() throws SQLException {
        System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/assign?useSSL=false", "root", "1260");

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatementUPDATE = connection.prepareStatement(UPDATE_USERS_SQL)) {
                    preparedStatementUPDATE.setString(1, "Abrar mahmud"); 
                    preparedStatementUPDATE.setInt(2,1 ); // hre 2nd "1" is the "id"
            System.out.println(preparedStatementUPDATE);

            preparedStatementUPDATE.executeUpdate();
        } catch (SQLException e) {

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
