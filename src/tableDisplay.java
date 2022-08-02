/*
COMP 6120 GROUP PROJECT
FALL 2021
DR. Zhou
Group Members:

    John Ropa

 */


import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;


public class tableDisplay extends JPanel {


    public tableDisplay(String sql) {
        try {

            ArrayList columnNames = new ArrayList();
            ArrayList data = new ArrayList();
            // create a new database connection
            dbConnection dbconnection = new dbConnection();
            // get db credentials
            String dbSelect = dbconnection.connectorURL;
            String dbusername = dbconnection.username;
            String dbpw  = dbconnection.dbpassword;
//          create database connection
            Connection connection = DriverManager.getConnection(dbSelect, dbusername, dbpw);
//          create statement object
            Statement statement = connection.createStatement();
            // perform the query
            ResultSet resultset = statement.executeQuery(sql);
            // get the metadata from the result set
            ResultSetMetaData metadata = resultset.getMetaData();
            // get number of columns
            int columns = metadata.getColumnCount();
            // add column names to arraylist
            for (int i = 1; i <= columns; i++) {
                columnNames.add(metadata.getColumnName(i));
            }
            // process the result set
            while (resultset.next()) {
                ArrayList row = new ArrayList(columns);
                for (int i = 1; i <= columns; i++) {
                    row.add(resultset.getObject(i));
                }
                data.add(row);
            }

            // create vectors for column names and data
            Vector columnNamesVector = new Vector();
            Vector dataVector = new Vector();
            //  add column names to vector
            for (int i = 0; i < data.size(); i++) {
                //  get the data for the current row
                ArrayList subArray = (ArrayList) data.get(i);
                //  create a vector for the current row
                Vector subVector = new Vector();
                //  add data to the vector
                for (int j = 0; j < subArray.size(); j++) {
                    subVector.add(subArray.get(j));
                }
                //  add the vector to the data vector
                dataVector.add(subVector);
            }
            // add column names to vector
            for (int i = 0; i < columnNames.size(); i++)
                columnNamesVector.add(columnNames.get(i));
            // create table model
            JTable table = new JTable(dataVector, columnNamesVector) {
                //  Auto resize columns
                public Class getColumnClass(int column) {
                    //  get the class of the column
                    for (int row = 0; row < getRowCount(); row++) {
                        //  get the class of the data in the column
                        Object o = getValueAt(row, column);
                        if (o != null) {
                            //  get the class of the object
                            return o.getClass();
                        }
                    }
                    //  if no class is returned, return the default class
                    return Object.class;
                }
            };
            //  add the table to the scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setAutoCreateRowSorter(true);
            //  add the scroll pane to the panel
            scrollPane.setPreferredSize(new Dimension(720,120));
            add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
            //  display error message
            JOptionPane.showMessageDialog(null, "oops!  There was an error in your SQL statement!  Please try again.",
                    "SQL STATEMENT ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
}
