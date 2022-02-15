// public class delete_table_from_database {
    
// }
// public class create_table {

// }
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class delete_table_from_database {

    private static final String createTableSQL = "drop table information";

    public static void main(String[] argv) throws SQLException {
        delete_table_from_database createTableExample = new delete_table_from_database();
        createTableExample.DeleteTable();
    }

    public void DeleteTable() throws SQLException {

        System.out.println(createTableSQL);

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/assign?useSSL=false", "root", "1260");

                Statement statement = connection.createStatement();) {

            statement.execute(createTableSQL);
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
