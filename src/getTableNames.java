/*
COMP 6120 GROUP PROJECT
FALL 2021
DR. Zhou
Group Members:

    John Ropa

 */


import java.sql.*;
import javax.swing.*;
import java.util.*;


public class getTableNames extends JPanel {

    public static ArrayList tableNames = new ArrayList();

    public getTableNames() {
        try {

            dbConnection dbconnection = new dbConnection();
            String dbSelect = dbconnection.connectorURL;
            String dbusername = dbconnection.username;
            String newpw = dbconnection.dbpassword;
            Connection connection = DriverManager.getConnection(dbSelect, dbusername, newpw);
            // create statement object
            Statement statement = connection.createStatement();
            // perform the query
            ResultSet resultset = statement.executeQuery("Show Tables");
            // add the results to the arraylist
            while (resultset.next()) {
                tableNames.add(resultset.getString(1));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
