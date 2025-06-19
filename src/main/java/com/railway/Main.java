package com.railway;

import com.railway.model.*;
import com.railway.service.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TrainService trainService = new TrainService();
    private static PassengerService passengerService = new PassengerService();
    private static ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {
        initializeSampleData();
        
        while (true) {
            System.out.println("\nRailway Reservation System");
            System.out.println("1. View Available Trains");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Check PNR Status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    viewTrains();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    checkPNRStatus();
                    break;
                case 5:
                    System.out.println("Thank you for using Railway Reservation System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSampleData() {
        // Create sample stations
        Station delhi = new Station("DEL", "New Delhi");
        Station mumbai = new Station("MUM", "Mumbai Central");
        Station chennai = new Station("CHE", "Chennai Central");

        // Create sample trains
        Train train1 = new Train("12345", "Rajdhani Express", delhi, mumbai, 100, 1500.0);
        Train train2 = new Train("67890", "Chennai Mail", delhi, chennai, 150, 1200.0);

        trainService.addTrain(train1);
        trainService.addTrain(train2);
    }

    private static void viewTrains() {
        System.out.println("\nAvailable Trains:");
        trainService.getAllTrains().forEach(System.out::println);
    }

    private static void bookTicket() {
        try {
            // Get passenger details
            System.out.println("\nEnter Passenger Details:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();
            System.out.print("Contact Number: ");
            String contact = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Passenger passenger = new Passenger(name, age, gender, contact, email);

            // Show available trains
            viewTrains();
            System.out.print("\nEnter Train Number: ");
            String trainNumber = scanner.nextLine();

            System.out.print("Enter Journey Date (DD-MM-YYYY): ");
            String dateStr = scanner.nextLine();
            LocalDate journeyDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            // Book ticket
            Train train = trainService.getTrainByNumber(trainNumber);
            if (train != null) {
                Ticket ticket = reservationService.bookTicket(train, passenger, journeyDate);
                System.out.println("\nTicket Booked Successfully!");
                System.out.println(ticket);
            } else {
                System.out.println("Invalid train number!");
            }
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
    }

    private static void cancelTicket() {
        System.out.print("\nEnter PNR Number to cancel: ");
        String pnr = scanner.nextLine();
        if (reservationService.cancelTicket(pnr)) {
            System.out.println("Ticket cancelled successfully!");
        } else {
            System.out.println("Invalid PNR or ticket already cancelled!");
        }
    }

    private static void checkPNRStatus() {
        System.out.print("\nEnter PNR Number: ");
        String pnr = scanner.nextLine();
        Ticket ticket = reservationService.getTicketByPNR(pnr);
        if (ticket != null) {
            System.out.println(ticket);
        } else {
            System.out.println("Invalid PNR number!");
        }
    }
}
