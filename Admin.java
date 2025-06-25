import java.util.*;

public class Admin {
    private String ID;
    private String name;
    
    public Admin(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    // Getters and Setters
    public String getID() {
        return ID;
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // Form details functionality
    public void formDetails(List<Passenger> passengers) {
        System.out.println("\n=== PASSENGER DETAILS ===");
        if (passengers.isEmpty()) {
            System.out.println("No passengers registered.");
            return;
        }
        
        for (Passenger passenger : passengers) {
            System.out.println(passenger);
            System.out.println("Tickets booked: " + passenger.getTickets().size());
            for (Ticket ticket : passenger.getTickets()) {
                System.out.println("  - " + ticket);
            }
            System.out.println("-------------------");
        }
    }
    
    // Cancellation form functionality
    public boolean cancellationForm(String pnr, List<Passenger> passengers) {
        System.out.println("Processing cancellation for PNR: " + pnr);
        
        for (Passenger passenger : passengers) {
            for (Ticket ticket : passenger.getTickets()) {
                if (ticket.getPNRno().equals(pnr)) {
                    ticket.deleteTicket();
                    passenger.getTickets().remove(ticket);
                    System.out.println("Ticket cancelled successfully by Admin");
                    return true;
                }
            }
        }
        
        System.out.println("Ticket with PNR " + pnr + " not found");
        return false;
    }
    
    // Refund amount functionality
    public double refundAmt(String pnr, List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            for (Ticket ticket : passenger.getTickets()) {
                if (ticket.getPNRno().equals(pnr) && ticket.getStatus().equals("CANCELLED")) {
                    double refundAmount = calculateRefund(ticket);
                    System.out.println("Refund amount for PNR " + pnr + ": $" + refundAmount);
                    return refundAmount;
                }
            }
        }
        
        System.out.println("No refund applicable for PNR: " + pnr);
        return 0.0;
    }
    
    // Helper method to calculate refund amount
    private double calculateRefund(Ticket ticket) {
        // Simple refund calculation - 80% of original amount
        double baseAmount = 100.0; // Default ticket price
        return baseAmount * 0.8;
    }
    
    @Override
    public String toString() {
        return "Admin ID: " + ID + ", Name: " + name;
    }
}
