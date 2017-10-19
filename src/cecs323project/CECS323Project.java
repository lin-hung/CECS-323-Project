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
                            + "6. List all the data for a book specified by the user. \n"
                            + "This includes all the data for the associated publisher and writing group.\n"
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
//                            sql = "SELECT * FROM WritingGroups";
//                            rs = statement.executeQuery(sql);
//                            System.out.println("Which group?");
//                            String str;
//                            while (true) {
//                                try {
//                                    str = scanner.next();
//                                } catch (Exception e) {
//                                    continue;
//                                }
//                                while(rs.next()){
//                                    if(rs.getString("groupName").equals(str))
//                                }
//
//                                break;
                            }

                            //List all the data for a group specified by the user (include all tables)
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
