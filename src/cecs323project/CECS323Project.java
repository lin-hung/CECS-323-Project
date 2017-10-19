package cecs323project;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CECS323Project {

    public static void main(String[] args) {
         Database db;
         Connection conn;
         Statement statement;
           
        try {
            db = new Database();
            conn = db.conn;
            statement = conn.createStatement();
            
            
            String sql="SELECT * FROM BOOKS";
             ResultSet rs = statement.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("bookTitle"));
            
        } catch (SQLException ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

}