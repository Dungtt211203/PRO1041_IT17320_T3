/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NamNguyenTien
 */
public class DBContext2 {
    private static final String USERNAME = "NAMNTPH25455";
    private static final String PASSWORD = "555999";
    private static final String SERVER = "NAMNGUYENTIEN\\SQLEXPRESS";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "DBDUAN1_NHOM3";
    private static final boolean USING_SSL = false;
    
    private static String CONNECT_STRING;
        
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        com.microsoft.sqlserver.jdbc.SQLServerDriver
            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";");
            if (USING_SSL) {
                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            }
            CONNECT_STRING = connectStringBuilder.toString();

            System.out.println(
                    "Connect String có dạng: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection GetConnection() throws SQLException{
        return DriverManager.getConnection(CONNECT_STRING);
    }
    public static void main(String[] args) throws SQLException {
        Connection conn = GetConnection();
        DatabaseMetaData dbmt = conn.getMetaData();
        System.out.println(dbmt.getDriverName());
    }
}