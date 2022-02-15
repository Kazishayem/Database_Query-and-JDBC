import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertPStatementExample {
    private static final String INSERT_USERS_SQL = "INSERT INTO usertest SELECT * FROM roll_no";

    public static void main(String[] argv) throws SQLException {
        InsertPStatementExample createTableExample = new InsertPStatementExample();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://127.0.0.1:3306/assign?useSSL=false", "root", "1260");

        
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            // preparedStatement.setInt(1, 1);
            // preparedStatement.setString(2, "kazi shayem");
            // preparedStatement.setString(3, "kazishayem@gmail.com");
            // preparedStatement.setString(4, "Chawkbazar");
        

            System.out.println(preparedStatement);
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        
            printSQLException(e);
        }

    
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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
