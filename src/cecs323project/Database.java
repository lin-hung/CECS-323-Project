package cecs323project;


import java.io.IOException;
import java.sql.*;



/*****************************************************
* This code only initializes a Database instance and *
* connects it to the server. No tables are defined   *
* or any other operations provided.                  *
*****************************************************/

public class Database {
    public Connection conn; //once a connection is established it stays
                     //as long as the code that created this
			     //instance does not exit

    Statement stat;  //stat can be reused in every operation

    public Database()throws IOException, ClassNotFoundException, 
                            SQLException, Exception
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Derby driver not found.");
        }
  //In the string to getConnection you may replace "MP3Player"      
        try {
           // conn = DriverManager.getConnection("jdbc:derby://localhost:1527/help;create=true;user=lin;password=abc");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/project;create=true");

            stat = conn.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
        }      
    }
}