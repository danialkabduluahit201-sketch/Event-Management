package com.eventmanagement;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Assignment(java)";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "200888";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEVENT MANAGEMENT SYSTEM");
            System.out.println("--- CRUD---");
            System.out.println("1. Create a female Participant to DB");
            System.out.println("2. Create a male Participant to DB");
            System.out.println("3. Read all Female Participants from DB");
            System.out.println("4. Read all Male Participants from DB");
            System.out.println("5. Update Participant in DB");
            System.out.println("6. Delete Participant from DB");
            System.out.println("7. Search Participant by ID in DB");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    DatabaseOperations.addFemaleParticipantToDB(sc);
                    break;
                case 2:
                    DatabaseOperations.addFemaleParticipantToDB(sc);
                    break;
                case 3:
                    DatabaseOperations.viewAllFemaleParticipants();
                    break;
                case 4:
                    DatabaseOperations.viewAllMaleParticipants();
                    break;
                case 5:
                    DatabaseOperations.updateParticipant(sc);
                    break;
                case 6:
                    DatabaseOperations.deleteParticipant(sc);
                    break;
                case 7:
                    DatabaseOperations.searchParticipantInDB(sc);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
