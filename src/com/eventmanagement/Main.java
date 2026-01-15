package com.eventmanagement;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Assignment(java)";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(DB_URL, "postgres", "200888");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from female_participants");
            while (rs.next()) System.out.println(rs.getInt("female_id") + " "
                    + rs.getString("first_name")
                    + " " + rs.getString("last_name")
                    + " " + rs.getInt("age")
                    + " " + rs.getString("t_shirt_size"));
        } catch (Exception e) {
            System.out.println("Exception occured!");
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occured!");
            }
        }
    }

    private static void addFemaleParticipantToDB(Scanner sc) {
        System.out.print("First name: ");
        String firstName = sc.nextLine();
        System.out.print("Last name: ");
        String lastName = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("T-shirt size: ");
        String tShirtSize = sc.nextLine();

        String sql = "INSERT INTO female_participants (first_name, last_name, age,t_shirt_size,email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.setString(4, tShirtSize);
            pstmt.setString(5, email);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Female participant added to database!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addMaleParticipantToDB(Scanner sc) {
        System.out.println("First name: ");
        String first_name = sc.nextLine();
        System.out.println("Last name: ");
        String last_name = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("T-shirt size: ");
        String T_shirt_size = sc.nextLine();
        System.out.println("Email: ");
        String email=sc.nextLine();
        String sql = "INSERT INTO male_participants (first_name,last_name,age,t_shirt_size,email) VALUES (?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setInt(3, age);
            pstmt.setString(4, T_shirt_size);
            pstmt.setString(5, email);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) System.out.println("Female participant added to database!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}