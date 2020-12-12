import java.sql.*;

public class Jdbc {

    public static void main(String[] args) {
        // STEP 2: Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
