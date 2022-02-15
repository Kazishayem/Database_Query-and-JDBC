import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class fileRead {

    private static final String fileReadSQL = "bulk insert info_details from 'F:\task_01\sample.txt'\n" + 
    "with (rowterminator = '\n',fieldterminator  = ','\n" + 
    ")\n";

    public static void main(String[] argv) throws SQLException {
        fileRead fileReadExample = new fileRead();
        fileReadExample.AlterTable();
    }

    public void AlterTable() throws SQLException {

        System.out.println(fileReadSQL);

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/assign?useSSL=false", "root", "1260");

                Statement statement = connection.createStatement();) {

            statement.execute(fileReadSQL);
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
