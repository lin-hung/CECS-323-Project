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
            //System.out.println(rs.getString("bookTitle"));

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
                            String str;
                            scanner.nextLine();
                            while (true) {
                                sql = "SELECT * FROM WritingGroups NATURAL JOIN Books";
                                rs = statement.executeQuery(sql);
                                System.out.println("Which group would you like information on?");
                                str = scanner.nextLine();
                                boolean success = false;
                                while (rs.next()) {
                                    if (rs.getString("groupName").equals(str)) {
                                        ResultSetMetaData metadata = rs.getMetaData();
                                        int columnCount = metadata.getColumnCount();
                                        for (int i = 1; i <= columnCount; i++) {
                                            String columnName = metadata.getColumnName(i);
                                            System.out.println(columnName + '\t' + rs.getString(columnName));
                                        }
                                        success = true;
                                    }
                                }
                                if (success) {
                                    break;
                                }
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
                            scanner.nextLine();
                            while (true) {
                                sql = "SELECT * FROM Publishers NATURAL JOIN Books";
                                rs = statement.executeQuery(sql);
                                System.out.println("Which publisher would you like information on?");
                                str = scanner.nextLine();
                                System.out.println("#####publisherName="+str);
                                boolean success = false;
                                while (rs.next()) {
                                    if (rs.getString("publisherName").equals(str)) {
                                        ResultSetMetaData metadata = rs.getMetaData();
                                        int columnCount = metadata.getColumnCount();
                                        for (int i = 1; i <= columnCount; i++) {
                                            String columnName = metadata.getColumnName(i);
                                            System.out.println(columnName + '\t' + rs.getString(columnName));
                                        }
                                        success = true;
                                    }
                                }
                                if (success) {
                                    break;
                                }
                            }
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
                            //List all the data for a book specified by the user. This includes all the data for the associated publisher and writing group.
                            scanner.nextLine();
                            while (true) {
                                sql = "SELECT * FROM Books NATURAL JOIN Publishers NATURAL JOIN WritingGroups";
                                rs = statement.executeQuery(sql);
                                System.out.println("Which book would you like information on?");
                                str = scanner.nextLine();
                                System.out.println("#####bookTitle="+str);
                                boolean success = false;
                                while (rs.next()) {
                                    if (rs.getString("bookTitle").equals(str)) {
                                        ResultSetMetaData metadata = rs.getMetaData();
                                        int columnCount = metadata.getColumnCount();
                                        for (int i = 1; i <= columnCount; i++) {
                                            String columnName = metadata.getColumnName(i);
                                            System.out.println(columnName + '\t' + rs.getString(columnName));
                                        }
                                        success = true;
                                    }
                                }
                                if (success) {
                                    break;
                                }
                            }                            
                            break;
                        case 7:
                            //Insert a new book
                            scanner.nextLine();
                            System.out.println("So you'd like to enter a new book! Which writing group was responsible for this book?");
                            String wG = scanner.nextLine();
                            System.out.println("What is the title of the book?");
                            String bookName = scanner.nextLine();
                            System.out.println("Who published the book?");
                            String publisher = scanner.nextLine();
                            System.out.println("What year what the book published?");
                            int year = scanner.nextInt();
                            System.out.println("How many pages are in the book?");
                            int pages = scanner.nextInt();
                            
                            System.out.println("Great! Let me just insert that for ya...");
                            sql = "INSERT INTO Books(groupName, bookTitle, publisherName, yearPublished, numberPages) VALUES (?,?,?,?,?)";
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, wG);
                            pstmt.setString(2, bookName);
                            pstmt.setString(3, publisher);
                            pstmt.setInt(4, year);
                            pstmt.setInt(5, pages);
                            pstmt.executeUpdate();
                            
                            System.out.println("Okay! Here you go:");
                            sql  = "SELECT * FROM Books";
                            rs = statement.executeQuery(sql);
                            while (rs.next())
                            {
                                if(rs.getString("bookTitle").equals(bookName))
                                {
                                    ResultSetMetaData metadata = rs.getMetaData();
                                    int columnCount = metadata.getColumnCount();
                                    for (int i=1; i<columnCount; i++)
                                    {
                                        String columnName = metadata.getColumnName(i);
                                        System.out.println(columnName + '\t' + rs.getString(columnName));
                                    }
                                }
                            }
                            break;
                        case 8:
                            //Insert a new publisher and update all book published by one publisher to be published by the new pubisher
                            scanner.nextLine();
                            System.out.println("A new publisher bought out an old one, you say? Well, what's the new publishers name?");
                            /*CREATE TABLE publishers(
                            publisherName varchar(30) NOT NULL PRIMARY KEY,
                            publisherAddress varchar(100),
                            publisherPhone varchar(10),
                            publisherEmail varchar(30)
                            );*/
                            String pubName = scanner.nextLine();
                            System.out.println("What address of the publisher?");
                            String address = scanner.nextLine();
                            System.out.println("What is their phone number?");
                            String phone = scanner.nextLine();
                            System.out.println("What is their email?");
                            String email = scanner.nextLine();
                            
                            System.out.println("Great! Let me just insert that for ya...");
                            sql = "INSERT INTO publishers(publisherName, publisherAddress, publisherPhone, publisherEmail) VALUES (?,?,?,?)";
                            PreparedStatement pstmt2 = conn.prepareStatement(sql);
                            pstmt2.setString(1, pubName);
                            pstmt2.setString(2, address);
                            pstmt2.setString(3, phone);
                            pstmt2.setString(4, email);
                            pstmt2.executeUpdate();
                            
                            System.out.println("Okay! Here you go:");
                            sql  = "SELECT * FROM publishers";
                            rs = statement.executeQuery(sql);
                            while (rs.next())
                            {
                                if(rs.getString("publisherName").equals(pubName))
                                {
                                    ResultSetMetaData metadata = rs.getMetaData();
                                    int columnCount = metadata.getColumnCount();
                                    for (int i=1; i<columnCount+1; i++)
                                    {
                                        String columnName = metadata.getColumnName(i);
                                        System.out.println(columnName + '\t' + rs.getString(columnName));
                                    }
                                }
                            }
                            
                            System.out.println("Now, which publisher did this new one buy out?");
                            String oldPub = scanner.nextLine();
                            sql = "UPDATE Books SET publisherName = ? WHERE publisherName = ?";
                            PreparedStatement pstmt3 = conn.prepareStatement(sql);
                            pstmt3.setString(1, pubName);
                            pstmt3.setString(2, oldPub);
                            pstmt3.executeUpdate();
                            
                            System.out.println("Replaced!");
                            break;
                        case 9:
                            //Remove a book specified by the user
                            scanner.nextLine();
                            System.out.println("Removing books now, huh? Alright, what's the book name?");
                            String book = scanner.nextLine();
                            sql = "DELETE FROM Books WHERE bookTitle = ?";
                            PreparedStatement pstmt4 = conn.prepareStatement(sql);
                            pstmt4.clearParameters();
                            pstmt4.setString(1, book);
                            pstmt4.executeUpdate();
                            
                            System.out.println("Alright, it's gone now, here are the remaining book titles: ");
                            sql = "SELECT bookTitle FROM Books";
                            rs = statement.executeQuery(sql);
                            while (rs.next()) {
                                System.out.println(rs.getString("bookTitle"));
                            }
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