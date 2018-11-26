package com.company;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:G:\\java\\intellij\\testDb\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\java\\intellij\\testDb\\testjava.db");
//            Statement statement = conn.createStatement()){

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
//            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " +
                        COLUMN_PHONE + " integer, " +
                        COLUMN_EMAIL + " text" +
                    ")");

            insertContact(statement,"Greg", 65432182, "greq@gmail.com");
            insertContact(statement,"Marta", 45345671, "marta@gmail.com");
            insertContact(statement,"Jane", 78899124, "jane@gmail.com");
            insertContact(statement,"Joe", 349872112, "joe@gmail.com");

//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Marta', 12344521, 'marta@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Jane', 23123466, 'jane@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)"+
//                                    " VALUES ('Joe', 67687212, 'joe@gmail.com')");

//            statement.execute("UPDATE contacts SET phone = 5566789 WHERE name = 'Jane'");
//            statement.execute("DELETE FROM contacts WHERE name = 'Joe'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while(results.next()){
                System.out.println(results.getString(COLUMN_NAME) + " " +
                                    results.getInt(COLUMN_PHONE) + " " +
                                    results.getString(COLUMN_EMAIL));
            }

            results.close();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute("INSERT  INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", "+
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ") " +
                "VALUES('" + name + "', " + phone + " , '" + email + "')");
    }

}
