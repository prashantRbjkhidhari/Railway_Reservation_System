import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static RailwayReservationSystem system = new RailwayReservationSystem("RRS001");
    
    public static void main(String[] args) {
        // Initialize system with some sample data
        initializeSystem();
        
        // Start the application
        system.uses();
        system.works();
        
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    passengerMenu();
                    break;
                case 2:
                    adminMenu();
                    break;
                case 3:
                    viewAllTrains();
                    break;
                case 4:
                    registerNewPassenger();
                    break;
                case 5:
                    System.out.println("Thank you for using Railway Reservation System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
      private static void initializeSystem() {
        // Add sample trains with enhanced details
        system.addTrain(new Train("EXP001", "Rajdhani Express", 7, "New Delhi", "Mumbai Central",
                                LocalTime.of(16, 30), LocalTime.of(8, 45), 1384.0, 500, 85.0));
        
        system.addTrain(new Train("EXP002", "Shatabdi Express", 6, "New Delhi", "Chandigarh",
                                LocalTime.of(7, 20), LocalTime.of(10, 45), 245.0, 400, 45.0));
        
        system.addTrain(new Train("EXP003", "Duronto Express", 5, "Howrah", "Mumbai Central",
                                LocalTime.of(22, 15), LocalTime.of(17, 30), 1968.0, 600, 95.0));
        
        system.addTrain(new Train("LOC001", "Chennai Local", 30, "Chennai Central", "Tambaram",
                                LocalTime.of(6, 0), LocalTime.of(7, 15), 29.0, 1200, 5.0));
        
        system.addTrain(new Train("SUP001", "Garib Rath Express", 3, "Delhi Sarai Rohilla", "Jammu Tawi",
                                LocalTime.of(11, 45), LocalTime.of(4, 30), 576.0, 800, 65.0));
        
        // Add sample passengers with enhanced details
        system.addPassenger(new Passenger("PAX001", "John Doe", 30, "password123", 
                                        "john.doe@email.com", "+91-9876543210", "123 Main St, Delhi"));
        
        system.addPassenger(new Passenger("PAX002", "Jane Smith", 25, "secure456", 
                                        "jane.smith@email.com", "+91-8765432109", "456 Park Ave, Mumbai"));
        
        system.addPassenger(new Passenger("PAX003", "Raj Kumar", 35, "mypass789", 
                                        "raj.kumar@email.com", "+91-7654321098", "789 Garden Rd, Chennai"));
        
        System.out.println("System initialized with enhanced sample data.");
        System.out.println("Sample login credentials:");
        System.out.println("PAX001 / password123");
        System.out.println("PAX002 / secure456");
        System.out.println("PAX003 / mypass789");
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    RAILWAY RESERVATION SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Passenger Login");
        System.out.println("2. Admin Panel");
        System.out.println("3. View All Trains");
        System.out.println("4. Register New Passenger");
        System.out.println("5. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice: ");
    }
      private static void passengerMenu() {
        System.out.print("Enter your Passenger ID: ");
        String paxId = scanner.nextLine();
        
        Passenger passenger = system.findPassenger(paxId);
        if (passenger == null) {
            System.out.println("Passenger not found! Please register first.");
            return;
        }
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        if (passenger.login(paxId, password)) {
            System.out.println("Welcome, " + passenger.getName() + "!");
            
            boolean passengerLoggedIn = true;
            while (passengerLoggedIn) {
                displayPassengerMenu();
                int choice = getIntInput();
                
                switch (choice) {
                    case 1:                        searchTrains(passenger);
                        break;
                    case 2:
                        bookTicket(passenger);
                        break;
                    case 3:
                        cancelTicket(passenger);
                        break;
                    case 4:
                        viewMyTickets(passenger);
                        break;
                    case 5:
                        modifyPassengerDetails(passenger);
                        break;
                    case 6:
                        changePassword(passenger);
                        break;
                    case 7:
                        passengerLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials! Login failed.");
        }
    }
      private static void displayPassengerMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("    PASSENGER MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Search Trains");
        System.out.println("2. Book Ticket");
        System.out.println("3. Cancel Ticket");
        System.out.println("4. View My Tickets");
        System.out.println("5. Modify Details");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
        System.out.println("=".repeat(40));
        System.out.print("Enter your choice: ");
    }
      private static void searchTrains(Passenger passenger) {
        System.out.println("\n=== SEARCH TRAINS ===");
        System.out.println("1. Search by train name/code");
        System.out.println("2. Search by route (source/destination)");
        System.out.println("3. View all available trains");
        System.out.print("Enter your choice: ");
        
        int searchChoice = getIntInput();
        
        switch (searchChoice) {
            case 1:
                System.out.print("Enter train name or code to search: ");
                String searchCriteria = scanner.nextLine();
                displaySearchResults(passenger.searchTrain(system.getTrains(), searchCriteria));
                break;
            case 2:
                System.out.print("Enter source station (or press Enter to skip): ");
                String source = scanner.nextLine();
                System.out.print("Enter destination station (or press Enter to skip): ");
                String destination = scanner.nextLine();
                displaySearchResults(passenger.searchTrain(system.getTrains(), "", source, destination));
                break;
            case 3:
                displaySearchResults(passenger.searchTrain(system.getTrains(), ""));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void displaySearchResults(List<Train> foundTrains) {
        if (foundTrains.isEmpty()) {
            System.out.println("No trains found matching your search criteria.");
        } else {
            System.out.println("\n=== SEARCH RESULTS ===");
            for (Train train : foundTrains) {
                System.out.println(train);
                System.out.println("Available Seats: " + train.getAvailableSeats() + "/" + train.getTotalSeats());
                System.out.println("-".repeat(80));
            }
        }
    }
      private static void bookTicket(Passenger passenger) {
        system.displayAllTrains();
        
        System.out.print("Enter train code to book: ");
        String trainCode = scanner.nextLine();
        
        Train train = system.findTrain(trainCode);
        if (train == null) {
            System.out.println("Train not found!");
            return;
        }
        
        if (!train.hasAvailableSeats()) {
            System.out.println("Sorry, no seats available on this train!");
            return;
        }
        
        System.out.print("Enter journey date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        
        try {
            LocalDate journeyDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            
            // Check if journey date is in the future
            if (journeyDate.isBefore(LocalDate.now())) {
                System.out.println("Journey date cannot be in the past!");
                return;
            }
            
            // Select seat class
            System.out.println("\nSelect seat class:");
            System.out.println("1. AC First Class");
            System.out.println("2. AC 2 Tier");
            System.out.println("3. AC 3 Tier");
            System.out.println("4. Sleeper");
            System.out.println("5. General");
            System.out.print("Enter choice: ");
            
            int classChoice = getIntInput();
            String seatClass = getSeatClass(classChoice);
            
            // Calculate and display fare
            double ticketPrice = calculateFare(train, seatClass);
            System.out.println("\nBooking Details:");
            System.out.println("Train: " + train.getTrainName());
            System.out.println("Route: " + train.getSource() + " → " + train.getDestination());
            System.out.println("Date: " + journeyDate);
            System.out.println("Class: " + seatClass);
            System.out.println("Fare: $" + String.format("%.2f", ticketPrice));
            
            System.out.print("\nConfirm booking? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (!confirm.toLowerCase().startsWith("y")) {
                System.out.println("Booking cancelled.");
                return;
            }
            
            System.out.println("Select payment type:");
            System.out.println("1. Credit Card");
            System.out.println("2. Debit Card");
            System.out.println("3. Net Banking");
            System.out.println("4. UPI");
            System.out.println("5. Cash");
            System.out.print("Enter choice: ");
            
            int paymentChoice = getIntInput();
            String paymentType = getPaymentType(paymentChoice);
            
            if (passenger.payCharges(ticketPrice)) {
                Payment payment = new Payment(ticketPrice, "PROCESSING");
                if (payment.makes(paymentType, ticketPrice)) {
                    Ticket ticket = passenger.bookTicket(train, paymentType, journeyDate, seatClass);
                    if (ticket != null) {
                        System.out.println("\n" + "=".repeat(50));
                        System.out.println("         BOOKING SUCCESSFUL!");
                        System.out.println("=".repeat(50));
                        System.out.println(ticket);
                    }
                }
            }
            
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD format.");
        }
    }
    
    private static String getSeatClass(int choice) {
        switch (choice) {
            case 1: return "AC FIRST CLASS";
            case 2: return "AC 2 TIER";
            case 3: return "AC 3 TIER";
            case 4: return "SLEEPER";
            case 5: return "GENERAL";
            default: return "GENERAL";
        }
    }
    
    private static double calculateFare(Train train, String seatClass) {
        double basePrice = train.getBaseFare();
        double multiplier = 1.0;
        
        switch (seatClass.toUpperCase()) {
            case "AC FIRST CLASS":
                multiplier = 3.0;
                break;
            case "AC 2 TIER":
                multiplier = 2.0;
                break;
            case "AC 3 TIER":
                multiplier = 1.5;
                break;
            case "SLEEPER":
                multiplier = 1.0;
                break;
            case "GENERAL":
                multiplier = 0.5;
                break;
            default:
                multiplier = 1.0;
        }
        
        return basePrice * multiplier;
    }
      private static String getPaymentType(int choice) {
        switch (choice) {
            case 1: return "Credit Card";
            case 2: return "Debit Card";
            case 3: return "Net Banking";
            case 4: return "UPI";
            case 5: return "Cash";
            default: return "Credit Card";
        }
    }
    
    private static void cancelTicket(Passenger passenger) {
        viewMyTickets(passenger);
        
        if (passenger.getTickets().isEmpty()) {
            return;
        }
        
        System.out.print("Enter PNR number to cancel: ");
        String pnr = scanner.nextLine();
        
        passenger.cancelTicket(pnr);
    }
    
    private static void viewMyTickets(Passenger passenger) {
        System.out.println("\n=== MY TICKETS ===");
        
        if (passenger.getTickets().isEmpty()) {
            System.out.println("You have no booked tickets.");
            return;
        }
        
        for (Ticket ticket : passenger.getTickets()) {
            System.out.println(ticket);
        }
    }
      private static void modifyPassengerDetails(Passenger passenger) {
        System.out.println("\n=== MODIFY PASSENGER DETAILS ===");
        System.out.println("Current details:");
        System.out.println(passenger);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) {
            newName = passenger.getName();
        }
        
        System.out.print("Enter new age (or -1 to keep current): ");
        int newAge = getIntInput();
        if (newAge == -1) {
            newAge = passenger.getAge();
        }
        
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        if (newEmail.trim().isEmpty()) {
            newEmail = passenger.getEmail();
        }
        
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();
        if (newPhone.trim().isEmpty()) {
            newPhone = passenger.getPhoneNumber();
        }
        
        System.out.print("Enter new address: ");
        String newAddress = scanner.nextLine();
        if (newAddress.trim().isEmpty()) {
            newAddress = passenger.getAddress();
        }
        
        passenger.modifyForm(newName, newAge, newEmail, newPhone, newAddress);
        System.out.println("\nUpdated details:");
        System.out.println(passenger);
    }
    
    private static void changePassword(Passenger passenger) {
        System.out.println("\n=== CHANGE PASSWORD ===");
        
        System.out.print("Enter current password: ");
        String oldPassword = scanner.nextLine();
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New passwords do not match!");
            return;
        }
        
        if (newPassword.length() < 6) {
            System.out.println("Password must be at least 6 characters long!");
            return;
        }
        
        if (passenger.changePassword(oldPassword, newPassword)) {
            System.out.println("Password changed successfully!");
        }
    }
    
    private static void adminMenu() {
        System.out.println("\n=== ADMIN PANEL ===");
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        
        if (!adminId.equals("ADMIN001")) {
            System.out.println("Invalid Admin ID!");
            return;
        }
        
        boolean adminLoggedIn = true;
        while (adminLoggedIn) {
            displayAdminMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    system.getAdmin().formDetails(system.getPassengers());
                    break;
                case 2:
                    adminCancelTicket();
                    break;
                case 3:
                    adminRefund();
                    break;
                case 4:
                    addNewTrain();
                    break;
                case 5:
                    adminLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void displayAdminMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("    ADMIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. View All Passenger Details");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. Process Refund");
        System.out.println("4. Add New Train");
        System.out.println("5. Logout");
        System.out.println("=".repeat(40));
        System.out.print("Enter your choice: ");
    }
    
    private static void adminCancelTicket() {
        System.out.print("Enter PNR number to cancel: ");
        String pnr = scanner.nextLine();
        
        system.getAdmin().cancellationForm(pnr, system.getPassengers());
    }
    
    private static void adminRefund() {
        System.out.print("Enter PNR number for refund: ");
        String pnr = scanner.nextLine();
        
        double refundAmount = system.getAdmin().refundAmt(pnr, system.getPassengers());
        if (refundAmount > 0) {
            System.out.println("Refund of $" + refundAmount + " will be processed within 5-7 business days.");
        }
    }
      private static void addNewTrain() {
        System.out.println("\n=== ADD NEW TRAIN ===");
        
        System.out.print("Enter train code: ");
        String trainCode = scanner.nextLine();
        
        // Check if train already exists
        if (system.findTrain(trainCode) != null) {
            System.out.println("Train with this code already exists!");
            return;
        }
        
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        
        System.out.print("Enter frequency (days per week): ");
        int frequency = getIntInput();
        
        System.out.print("Enter source station: ");
        String source = scanner.nextLine();
        
        System.out.print("Enter destination station: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter departure time (HH:MM): ");
        String depTimeStr = scanner.nextLine();
        
        System.out.print("Enter arrival time (HH:MM): ");
        String arrTimeStr = scanner.nextLine();
        
        System.out.print("Enter distance (km): ");
        double distance = getDoubleInput();
        
        System.out.print("Enter total seats: ");
        int totalSeats = getIntInput();
        
        System.out.print("Enter base fare ($): ");
        double baseFare = getDoubleInput();
        
        try {
            LocalTime depTime = LocalTime.parse(depTimeStr);
            LocalTime arrTime = LocalTime.parse(arrTimeStr);
            
            Train newTrain = new Train(trainCode, trainName, frequency, source, destination, 
                                     depTime, arrTime, distance, totalSeats, baseFare);
            system.addTrain(newTrain);
            
            System.out.println("Train added successfully!");
            System.out.println(newTrain);
            
        } catch (Exception e) {
            System.out.println("Invalid time format! Please use HH:MM format.");
        }
    }
    
    private static double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    private static void viewAllTrains() {
        system.displayAllTrains();
    }
      private static void registerNewPassenger() {
        System.out.println("\n=== REGISTER NEW PASSENGER ===");
        
        System.out.print("Enter Passenger ID: ");
        String paxId = scanner.nextLine();
        
        // Check if passenger already exists
        if (system.findPassenger(paxId) != null) {
            System.out.println("Passenger with this ID already exists!");
            return;
        }
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = getIntInput();
        
        System.out.print("Enter password (minimum 6 characters): ");
        String password = scanner.nextLine();
        
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long!");
            return;
        }
        
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();
        
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        Passenger newPassenger = new Passenger(paxId, name, age, password, email, phone, address);
        system.addPassenger(newPassenger);
        
        System.out.println("\nRegistration successful!");
        System.out.println("Your login credentials:");
        System.out.println("Passenger ID: " + paxId);
        System.out.println("Password: " + password);
        System.out.println("Please keep this information safe!");
    }
    
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
