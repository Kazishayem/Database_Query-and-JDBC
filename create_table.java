// public class create_table {

// }
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class create_table {

    private static final String createTableSQL = "create table information(\r\n" + "  ID int(3) primary key,\r\n"
            +"name varchar(20),\r\n" + "email varchar(20),\r\n" + "address varchar(20)\r\n" + ");";
            

    public static void main(String[] argv) throws SQLException {
        create_table createTableExample = new create_table();
        createTableExample.createTable();
    }

    public void createTable() throws SQLException {

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