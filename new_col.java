import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class new_col {

    private static final String ALTER_USERS_SQL = "ALTER TABLE usertest ADD COLUMN  INT(3)";

    public static void main(String[] argv) throws SQLException {
        update alterStatementExample = new update();
        alterStatementExample.updateRecord();
    }

    public void updateRecord() throws SQLException {
        System.out.println(ALTER_USERS_SQL);
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/assign?useSSL=false", "root", "1260");

                PreparedStatement preparedStatementALTER = connection.prepareStatement(ALTER_USERS_SQL)) {
            preparedStatementALTER.setString(1, "rollNo");
            System.out.println(preparedStatementALTER);

            preparedStatementALTER.executeUpdate();
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
