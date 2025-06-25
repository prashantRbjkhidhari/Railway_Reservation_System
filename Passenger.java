import java.util.*;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Passenger {
    private String paxId;
    private String name;
    private int age;
    private String password; // Encrypted password
    private String email;
    private String phoneNumber;
    private String address;
    private List<Ticket> tickets;
      // Constructor with basic information (backward compatibility)
    public Passenger(String paxId, String name, int age) {
        this.paxId = paxId;
        this.name = name;
        this.age = age;
        this.password = hashPassword("default123"); // Default password
        this.email = "not-provided@example.com";
        this.phoneNumber = "Not provided";
        this.address = "Not provided";
        this.tickets = new ArrayList<>();
    }
    
    // Constructor with all details
    public Passenger(String paxId, String name, int age, String password, String email, String phoneNumber, String address) {
        this.paxId = paxId;
        this.name = name;
        this.age = age;
        this.password = hashPassword(password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.tickets = new ArrayList<>();
    }
    // Getters and Setters
    public String getPaxId() {
        return paxId;
    }
    
    public void setPaxId(String paxId) {
        this.paxId = paxId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public List<Ticket> getTickets() {
        return tickets;
    }
    
    // Password hashing method
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback to simple hash if SHA-256 is not available
            return Integer.toString(password.hashCode());
        }
    }
    
    // Verify password
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(hashPassword(inputPassword));
    }      // Simple login for backward compatibility (only checks ID)
    public boolean login(String inputPaxId) {
        return this.paxId.equals(inputPaxId);
    }
    
    // Enhanced login functionality with password verification
    public boolean login(String inputPaxId, String inputPassword) {
        return this.paxId.equals(inputPaxId) && verifyPassword(inputPassword);
    }
      // Basic search train functionality (backward compatibility)
    public List<Train> searchTrain(List<Train> allTrains, String searchCriteria) {
        return searchTrain(allTrains, searchCriteria, null, null);
    }
    
    // Enhanced search train functionality with source/destination
    public List<Train> searchTrain(List<Train> allTrains, String searchCriteria, String source, String destination) {
        List<Train> foundTrains = new ArrayList<>();
        for (Train train : allTrains) {
            boolean matches = false;
            
            // Search by train name or code
            if (searchCriteria != null && !searchCriteria.trim().isEmpty()) {
                if (train.getTrainName().toLowerCase().contains(searchCriteria.toLowerCase()) ||
                    train.getTrainCode().toLowerCase().contains(searchCriteria.toLowerCase())) {
                    matches = true;
                }
            }
            
            // Search by source and destination
            if (source != null && !source.trim().isEmpty()) {
                if (train.getSource().toLowerCase().contains(source.toLowerCase())) {
                    matches = true;
                }
            }
            
            if (destination != null && !destination.trim().isEmpty()) {
                if (train.getDestination().toLowerCase().contains(destination.toLowerCase())) {
                    matches = matches && true; // Both source and destination should match if provided
                } else if (source != null && !source.trim().isEmpty()) {
                    matches = false; // If destination doesn't match but was provided
                }
            }
            
            // If no specific criteria provided, show all trains with available seats
            if ((searchCriteria == null || searchCriteria.trim().isEmpty()) && 
                (source == null || source.trim().isEmpty()) && 
                (destination == null || destination.trim().isEmpty())) {
                matches = train.hasAvailableSeats();
            }
            
            if (matches && train.hasAvailableSeats()) {
                foundTrains.add(train);
            }
        }
        return foundTrains;
    }    // Basic modify form functionality (backward compatibility)
    public void modifyForm(String newName, int newAge) {
        modifyForm(newName, newAge, null, null, null);
    }
    
    // Enhanced modify form functionality with all details
    public void modifyForm(String newName, int newAge, String newEmail, String newPhoneNumber, String newAddress) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
        if (newAge > 0) {
            this.age = newAge;
        }
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            this.email = newEmail;
        }
        if (newPhoneNumber != null && !newPhoneNumber.trim().isEmpty()) {
            this.phoneNumber = newPhoneNumber;
        }
        if (newAddress != null && !newAddress.trim().isEmpty()) {
            this.address = newAddress;
        }
        System.out.println("Passenger details updated successfully!");
    }
    
    // Change password
    public boolean changePassword(String oldPassword, String newPassword) {
        if (verifyPassword(oldPassword)) {
            this.password = hashPassword(newPassword);
            System.out.println("Password changed successfully!");
            return true;
        } else {
            System.out.println("Old password is incorrect!");
            return false;
        }
    }
    
    // Pay charges functionality with enhanced calculation
    public boolean payCharges(double amount) {
        System.out.println("Processing payment of $" + String.format("%.2f", amount) + " for passenger: " + name);
        // Simulate payment processing with some validation
        if (amount <= 0) {
            System.out.println("Invalid payment amount!");
            return false;
        }
        System.out.println("Payment processed successfully!");
        return true;
    }
      // Basic book ticket functionality (backward compatibility)
    public Ticket bookTicket(Train train, String paymentType, LocalDate dateOfJourney) {
        return bookTicket(train, paymentType, dateOfJourney, "GENERAL");
    }
    
    // Enhanced book ticket functionality with seat preference
    public Ticket bookTicket(Train train, String paymentType, LocalDate dateOfJourney, String seatPreference) {
        if (!train.hasAvailableSeats()) {
            System.out.println("No seats available on this train!");
            return null;
        }
        
        String pnr = generatePNR();
        double ticketPrice = calculateTicketPrice(train, seatPreference);
        
        Ticket ticket = new Ticket(pnr, "CONFIRMED", paymentType, train.getTrainCode(), 
                                 dateOfJourney, train.getSource(), train.getDestination(), 
                                 ticketPrice, seatPreference);
        this.tickets.add(ticket);
        train.addPassenger(this);
        
        System.out.println("Ticket booked successfully!");
        System.out.println("PNR: " + pnr);
        System.out.println("Fare: $" + String.format("%.2f", ticketPrice));
        return ticket;
    }
    
    // Calculate ticket price based on train and seat preference
    private double calculateTicketPrice(Train train, String seatPreference) {
        double basePrice = train.getBaseFare();
        double multiplier = 1.0;
        
        switch (seatPreference.toUpperCase()) {
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
    
    // Cancel ticket functionality
    public boolean cancelTicket(String pnr) {
        for (Ticket ticket : tickets) {
            if (ticket.getPNRno().equals(pnr)) {
                ticket.deleteTicket();
                tickets.remove(ticket);
                System.out.println("Ticket with PNR " + pnr + " cancelled successfully!");
                return true;
            }
        }
        System.out.println("Ticket with PNR " + pnr + " not found!");
        return false;
    }
    
    // Generate unique PNR
    private String generatePNR() {
        return "PNR" + System.currentTimeMillis();
    }    
    @Override
    public String toString() {
        return String.format("Passenger ID: %s | Name: %s | Age: %d | Email: %s | Phone: %s | Address: %s | Tickets: %d", 
                           paxId, name, age, email, phoneNumber, address, tickets.size());
    }
}
