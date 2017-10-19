package cecs323project;

import java.sql.*;
import java.util.Scanner;
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

            //TEST
            String sql = "SELECT * FROM BOOKS";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("bookTitle"));

            Scanner scanner = new Scanner(System.in);
            int input;
            while (true) {
                try {
                    System.out.println("1. List all writing groups\n"
                            + "2. List all the data for a group specified by the user (include all tables)\n"
                            + "3. List all publishers\n"
                            + "4. List all the data for a pubisher specified by the user (include all tables)\n"
                            + "5. List all book titles\n"
                            + "6. List all the data for a book specified by the user. This includes all the data for the associated publisher and writing group.\n"
                            + "7. Insert a new book\n"
                            + "8. Insert a new publisher and update all book published by one publisher to be published by the new pubisher.\n"
                            + "9. Remove a book specified by the user.\n"
                            + "0. Exit");
                    input = scanner.nextInt();
                } catch (Exception e) {
                    scanner.nextLine();
                    continue;
                }
                if (input == 0) {
                    System.out.println("#####");
                    break;
                } else {
                    switch (input) {
                        case 1:
                            //List all writing groups
                            sql = "SELECT groupName FROM WritingGroups";
                            rs = statement.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("groupName"));
                            }
                            break;
                        case 2:
                            sql = "SELECT * FROM WritingGroups";
                            rs = statement.executeQuery(sql);
                            String str;
                            while (true) {
                                try {
                                    System.out.println("Which group?");
                                    str = scanner.next();
                                   
                                    System.out.println("####"+str);
                                } catch (Exception e) {
                                    continue;
                                }
                                int success=0; 
                                while (rs.next()) {
                                    if (rs.getString("groupName").equals(str)) {
                                        //nat join with books, then print out the data
                                        sql = "SELECT * FROM WritingGroups NATURAL JOIN Books WHERE groupName="
                                                + "'" + str + "'";
                                        rs = statement.executeQuery(sql);
                                        
                                        ResultSetMetaData metadata = rs.getMetaData();
                                        int columnCount = metadata.getColumnCount();
                                        System.out.print('\n');
                                         rs.next();
                                        for (int i = 1; i <= columnCount; i++) {
                                            String columnName = metadata.getColumnName(i);
                                            System.out.println(columnName+'\t'+rs.getString(columnName));
                                        }
                                        success=1;
                                    }
                                    else success=0;
                                }
                                if(success==1){
                                    break;
                                }
                                else System.out.println("####continue");
                            }
                            break;
                        case 3:
                            //List all publishers
                            sql = "SELECT publisherName FROM Publishers";
                            rs = statement.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("publisherName"));
                            }
                            break;
                        case 4:
                            //List all the data for a pubisher specified by the user (include all tables)
                            break;
                        case 5:
                            //List all book titles
                            sql = "SELECT bookTitle FROM Books";
                            rs = statement.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("bookTitle"));
                            }
                            break;
                        case 6:
                            //List all the data for a book specified by the user.
                            break;
                        case 7:
                            //Insert a new book
                            break;
                        case 8:
                            //Insert a new publisher and update all book published by one publisher to be published by the new pubisher
                            break;
                        case 9:
                            //Remove a book specified by the user
                            break;
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CECS323Project.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
