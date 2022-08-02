/*
COMP 6120 GROUP PROJECT
FALL 2021
DR. Zhou
Group Members:

    John Ropa

 */



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    public GUI() {

        JFrame frame = new JFrame();
        JPanel panel =  new JPanel();
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#003366"));
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setBackground(Color.decode("#003366"));
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.decode("#003366"));
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode("#003366"));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setBorder(new EmptyBorder(20,0,20,0));
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));

        // Set the contents of the JTextArea.
        String text = "Enter Query Statement Here";
        textArea.setText(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.GRAY);

        // Create a scroll pane to hold the text area.
        JScrollPane sqlWindow = new JScrollPane(textArea);
        sqlWindow.setPreferredSize(new Dimension(500, 200));
        sqlWindow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            }
        });

        // EVENT LISTENER FOR TEXT AREA WHEN BUTTON CLICKED THE SQL STATEMENT IS EXECUTED //
        JButton sqlButton = new JButton("Execute Query");
        sqlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the contents of the JTextArea component.
                String contents = textArea.getText();
                contents.replace('"', '\"');
                String sqlStatement = contents;
                String search = "drop";
                // throw error on drop command
                if (sqlStatement .toLowerCase().indexOf(search.toLowerCase()) != -1) {
                    JOptionPane.showMessageDialog(null,  "Drop Operation is Not Allowed",
                            "WARNING! Illegal Command",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // remove everything from tablePanel
                    tablePanel.removeAll();
                    // Add the label back

                    JLabel tableResultsLabel = new JLabel("Query Results");
                    tableResultsLabel.setHorizontalAlignment(JLabel.LEFT);
                    tableResultsLabel.setBorder(new EmptyBorder(20,0,20,0));
                    tableResultsLabel.setFont(new Font("Serif", Font.BOLD, 14));
                    tableResultsLabel.setForeground(Color.WHITE);
                    tablePanel.add(tableResultsLabel, BorderLayout.WEST);

                    // create the new table display with the results of the entered query
                    tableDisplay testTable = new tableDisplay(sqlStatement );
                    testTable.setBackground(Color.decode("#003366"));
                    tablePanel.revalidate();
                    tablePanel.setBackground(Color.decode("#003366"));
                    tablePanel.add(testTable);
                    tablePanel.repaint();
                }
            }
        });

        // Get the table data from database
        String sqlStatement  = "SELECT * FROM db_book ";
        // Create the table display with the results of the entered query
        tableDisplay testTable = new tableDisplay(sqlStatement);
        testTable.setBackground(Color.decode("#003366"));
        // Set up the top panel to house the sql text area and the sql button
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));

        // Create Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#003366"));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(sqlButton);
        buttonPanel.add(clearButton);

        topPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));

        JLabel topLable = new JLabel("To perform a custom query, enter the SQL statement below: ");
        topLable.setBorder(new EmptyBorder(0,0,10,0));
        topLable.setFont(new Font("Serif", Font.BOLD, 16));
        topLable.setForeground(Color.WHITE);
//        topPanel.add(new JLabel("Enter SQL Query in Text Area Below:", SwingConstants.LEFT, new EmptyBorder(0,0,5,0)));
        topPanel.add(topLable, BorderLayout.WEST);
        topPanel.add(sqlWindow, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Get the list of table names make them clickable
        getTableNames tableNames = new getTableNames();
        // create a list object to hold the table names
        Object[] tableNameList = tableNames.tableNames.toArray();
        // create a JList object to hold the table names
        JList tbList = new JList(tableNameList);
        // setting an index to the first item in the list
        tbList.setSelectedIndex(0);
        // add an event listener to the get the mouse click
        tbList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    // get the selected item from the list
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        //
                        Object item = target.getModel().getElementAt(index);
                        String sql = item.toString();
                        // clear the panel
                        tablePanel.removeAll();
                        // add updated label
                        JLabel tableResultsLabel = new JLabel(String.format("Table Contents of %s ", sql));
                        tableResultsLabel.setBorder(new EmptyBorder(20,0,20,0));
                        tableResultsLabel.setFont(new Font("Serif", Font.BOLD, 14));
                        tableResultsLabel.setForeground(Color.WHITE);
                        tablePanel.add(tableResultsLabel, BorderLayout.WEST);
                        // create the new table display with the results of the entered query
                        String sqlStatement  = String.format("SELECT * FROM %s ", sql);
                        tableDisplay testTable = new tableDisplay(sqlStatement );
                        testTable.setBackground(Color.decode("#003366"));
                        // add the table to the panel
                        tablePanel.add(testTable);
                        tablePanel.revalidate();
                        // repaint the display to show the query results
                        tablePanel.repaint();
                    }
                }
            }
        });
        JLabel tableNamesLabel = new JLabel("Select a Table to View");
        tableNamesLabel.setBorder(new EmptyBorder(20,0,20,0));
        tableNamesLabel.setFont(new Font("Serif", Font.BOLD, 14));
        tableNamesLabel.setForeground(Color.WHITE);



        // Set up the JScrollPane to house the table list
        JScrollPane listPane = new JScrollPane(tbList);
        listPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // set the dimensions of the list
        listPane.setPreferredSize(new Dimension(200,100));
        // set up new Dimension, takes up whole width of the screen


        middlePanel.add(tableNamesLabel, BorderLayout.NORTH);
        middlePanel.add(listPane);

        // Set up the bottom panel to house the table display
        JLabel tableResultsLabel = new JLabel("Table Contents");
        tableResultsLabel.setBorder(new EmptyBorder(20,0,20,0));
        tableResultsLabel.setFont(new Font("Serif", Font.BOLD, 14));
        tableResultsLabel.setForeground(Color.WHITE);



        tablePanel.add(tableResultsLabel, BorderLayout.WEST);
        tablePanel.setBackground(Color.decode("#003366"));
        tablePanel.add(testTable);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.decode("#003366"));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,20,5));
        panel.add(topPanel);
        panel.add(middlePanel);


        panel.add(tablePanel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("COMP 6120 PROJECT GROUP 14");
        frame.add(bottomPanel, BorderLayout.SOUTH);

        //  let layout manager be in charge of frame size.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new GUI();

    }
}
