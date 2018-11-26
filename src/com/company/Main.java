package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\java\\intellij\\testDb\\testjava.db");
//            Statement statement = conn.createStatement()){

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\java\\intellij\\testDb\\testjava.db");
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                                    "(name TEXT, phone INTEGER , email TEXT)");
//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Marta', 12344521, 'marta@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Jane', 23123466, 'jane@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Joe', 67687212, 'joe@gmail.com')");

//            statement.execute("UPDATE contacts SET phone = 5566789 WHERE name = 'Jane'");
//            statement.execute("DELETE FROM contacts WHERE name = 'Joe'");

            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();
            while(results.next()){
                System.out.println(results.getString("name") + " " +
                                    results.getInt("phone") + " " +
                                    results.getString("email"));
            }

            results.close();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
