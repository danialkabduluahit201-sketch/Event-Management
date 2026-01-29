package org.example.eventmanagement.eventmanagement;

import java.sql.*;
import java.util.Scanner;

public class DatabaseOperations {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Assignment(java)";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    public static void addEvent(Scanner sc) {
        System.out.println("Event name");
        String eventName=sc.nextLine();
        System.out.println("Location");
        String location=sc.nextLine();
        System.out.println("date_time");
        String date_time=sc.nextLine();
    }
    public static void addFemaleParticipantToDB(Scanner sc) {
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
        System.out.println("Address: ");
        String address=sc.nextLine();

        String sql = "INSERT INTO female_participants (first_name, last_name, age,t_shirt_size,email,address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.setString(4, tShirtSize);
            pstmt.setString(5, email);
            pstmt.setString(6,address);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Female participant added to database!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addMaleParticipantToDB(Scanner sc) {
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
        System.out.println("Address: ");
        String address=sc.nextLine();
        sc.nextLine();
        MaleParticipant m_p=new MaleParticipant(first_name+last_name,age,email,T_shirt_size);
        DataPool.addParticipant(m_p);
        String sql = "INSERT INTO male_participants (first_name,last_name,age,t_shirt_size,email) VALUES (?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setInt(3, age);
            pstmt.setString(4, T_shirt_size);
            pstmt.setString(5, email);
            pstmt.setString(6,address);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) System.out.println("Female participant added to database!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void viewAllFemaleParticipants(){
        String sql="SELECT * FROM female_participants";
        try(Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql)) {
            System.out.println("Female participants");
            while(rs.next()){
                System.out.println(
                        "ID: " + rs.getInt("female_id") +
                                ", Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                ", Age: " + rs.getInt("age") +
                                ", T-Shirt size: " + rs.getString("t_shirt_size") +
                                ", Email: " + rs.getString("email")
                );
            }
        }catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void viewAllMaleParticipants(){
        String sql="SELECT * FROM male_participants ORDER BY age";
        try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql)) {
            System.out.println("Male participants");
            while(rs.next()){
                System.out.println(
                        "ID: " + rs.getInt("male_id") +
                                ", Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                ", Age: " + rs.getInt("age") +
                                ", T-Shirt size: " + rs.getString("t_shirt_size") +
                                ", Email: " + rs.getString("email")
                );
            }
        }catch(SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public static void updateParticipant(Scanner sc) {
        System.out.println("Gender (M/F): ");
        String gender = sc.nextLine();
        System.out.println("Participant ID");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("\nWhat to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Age");
        System.out.println("4. Email");
        System.out.println("5. T-Shirt Size");
        System.out.println("6. Address");
        System.out.print("Choose: ");
        int updateChoice = sc.nextInt();
        sc.nextLine();

        String tableName = gender.equalsIgnoreCase("F") ? "female_participants" : "male_participants";
        String idColumn = gender.equalsIgnoreCase("F") ? "female_id" : "male_id";
        String columnToUpdate = "";

        switch (updateChoice) {
            case 1:
                columnToUpdate = "first_name";
                break;
            case 2:
                columnToUpdate = "last_name";
                break;
            case 3:
                columnToUpdate = "age";
                break;
            case 4:
                columnToUpdate = "email";
                break;
            case 5:
                columnToUpdate = "t_shirt_size";
                break;
            case 6: columnToUpdate="address";
            break;


            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.print("Enter new value: ");
        String newValue = sc.nextLine();

        String sql = "UPDATE " + tableName + " SET " + columnToUpdate + " = ? WHERE " + idColumn + " = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (updateChoice == 3) {
                pstmt.setInt(1, Integer.parseInt(newValue));
            } else {
                pstmt.setString(1, newValue);
            }
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully!");
            } else {
                System.out.println("Participant not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void deleteParticipant(Scanner sc){
        System.out.println("Gender (M/F): ");
        String gender=sc.nextLine();
        System.out.println("Participant ID: ");
        int id=sc.nextInt();
        sc.nextLine();
        String table_name=gender.equalsIgnoreCase("F") ? "female_participants":"male_participants";
        String idColumn=gender.equalsIgnoreCase("F") ? "female_id":"male_id";
        String sql=" DELETE FROM "+table_name+" WHERE "+idColumn+" = ?";
        try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql)){
            pstmt.setInt(1,id);
            int rowsAffected=pstmt.executeUpdate();
            String resultWORD=rowsAffected>0 ? "Participant deleted successfully!" : "No such participant";
            System.out.println(resultWORD);
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    public static void searchParticipantInDB(Scanner sc){
        System.out.println("Gender (M/F): ");
        String gender=sc.nextLine();
        System.out.println("How many participants do you need? (1 or MORE?)");
        int n=sc.nextInt();
        sc.nextLine();
        if(n>1) {
            System.out.println("Enter the first index: ");
            int first=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the last index: ");
            int last=sc.nextInt();
            sc.nextLine();
            String table_name=gender.equalsIgnoreCase("F") ? "female_participants":"male_participants";
            String IdColumn=gender.equalsIgnoreCase("F") ? "female_id":"male_id";
            String sql="SELECT * FROM "+table_name+" WHERE "+IdColumn+" BETWEEN ? AND ? ORDER BY first_name";
            try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
                PreparedStatement pstmt=con.prepareStatement(sql)){
                pstmt.setInt(1,first);
                pstmt.setInt(2,last);
                ResultSet rs=pstmt.executeQuery();
                while(rs.next()) {
                    System.out.println("\n=== PARTICIPANTS FOUND ===");
                    System.out.println("ID: " + rs.getInt(IdColumn));
                    System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("T-Shirt: " + rs.getString("t_shirt_size"));
                    System.out.println("Email: " + rs.getString("email"));
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else {System.out.println("Enter the Participant ID");
            int id=sc.nextInt();
            String tableName = gender.equalsIgnoreCase("F") ? "female_participants" : "male_participants";
            String idColumn = gender.equalsIgnoreCase("F") ? "female_id" : "male_id";

            String sql = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ? ORDER BY first_name";

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("\n=== PARTICIPANT FOUND ===");
                    System.out.println("ID: " + rs.getInt(idColumn));
                    System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("T-Shirt: " + rs.getString("t_shirt_size"));
                } else {
                    System.out.println("Participant not found!");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
