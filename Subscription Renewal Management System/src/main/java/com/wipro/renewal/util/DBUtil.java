package com.wipro.renewal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getDBConnection() {
        
        try {
            // ✅ Correct Oracle driver
            Class.forName("oracle.jdbc.OracleDriver");

            // ✅ Correct service-name based URL (Oracle XE)
            String url = "jdbc:oracle:thin:@localhost:1521:XE";

            // ⚠️ Use normal DB user (NOT SYSTEM)
            String username = "SYSTEM";
            String password = "Poorvasri07*";

            Connection con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
