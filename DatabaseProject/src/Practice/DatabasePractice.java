package Practice;

import java.sql.*;

public class DatabasePractice {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Problem in loading the driver");
            ex.printStackTrace();
        }

        try {
            String dbName = "Employee.accdb";
            String dbURL = "jdbc:ucanaccess://" + dbName;
            conn = DriverManager.getConnection(dbURL);
            stat = conn.createStatement();

            // Inserting a row
            String insertQuery = "INSERT INTO EMP (EName, Salary) VALUES ('ABC', 100000)";
            stat.executeUpdate(insertQuery);

            // Updating the Salary for the existing row
            String updateQuery = "UPDATE EMP SET Salary = 12000 WHERE EName = 'ABC'";
            stat.executeUpdate(updateQuery);

            // Retrieving and printing all rows
            rs = stat.executeQuery("SELECT * FROM EMP"); // Assuming emp is the correct table name
            int id;
            String name;
            double sal;
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                sal = rs.getDouble(3);
                System.out.println("id " + id + " name " + name + " Salary " + sal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    rs.close();
                    stat.close();
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
