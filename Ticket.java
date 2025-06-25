import java.time.LocalDate;

public class Ticket {
    private String PNRno;
    private String status;
    private String paymentType;
    private String trainCode;
    private LocalDate dateOfJourney;
    private String source;
    private String destination;
    private double fare;
    private String seatClass;
    private String seatNumber;
      // Basic constructor (backward compatibility)
    public Ticket(String PNRno, String status, String paymentType, String trainCode, LocalDate dateOfJourney) {
        this.PNRno = PNRno;
        this.status = status;
        this.paymentType = paymentType;
        this.trainCode = trainCode;
        this.dateOfJourney = dateOfJourney;
        this.source = "Not specified";
        this.destination = "Not specified";
        this.fare = 100.0; // Default fare
        this.seatClass = "GENERAL";
        this.seatNumber = generateSeatNumber();
    }
    
    // Enhanced constructor with all details
    public Ticket(String PNRno, String status, String paymentType, String trainCode, 
                  LocalDate dateOfJourney, String source, String destination, 
                  double fare, String seatClass) {
        this.PNRno = PNRno;
        this.status = status;
        this.paymentType = paymentType;
        this.trainCode = trainCode;
        this.dateOfJourney = dateOfJourney;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.seatClass = seatClass;
        this.seatNumber = generateSeatNumber();
    }
    
    // Generate seat number (simplified logic)
    private String generateSeatNumber() {
        return seatClass.charAt(0) + "-" + (int)(Math.random() * 100 + 1);
    }    
    // Getters and Setters
    public String getPNRno() {
        return PNRno;
    }
    
    public void setPNRno(String PNRno) {
        this.PNRno = PNRno;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public String getTrainCode() {
        return trainCode;
    }
    
    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }
    
    public LocalDate getDateOfJourney() {
        return dateOfJourney;
    }
    
    public void setDateOfJourney(LocalDate dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public double getFare() {
        return fare;
    }
    
    public void setFare(double fare) {
        this.fare = fare;
    }
    
    public String getSeatClass() {
        return seatClass;
    }
    
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }    
    // Create new ticket
    public void newTicket() {
        this.status = "CONFIRMED";
        System.out.println("New ticket created with PNR: " + PNRno);
    }
    
    // Delete ticket
    public void deleteTicket() {
        this.status = "CANCELLED";
        System.out.println("Ticket with PNR " + PNRno + " has been cancelled");
    }
    
    // Calculate cancellation charges (20% of fare)
    public double getCancellationCharges() {
        return fare * 0.20;
    }
    
    // Get refund amount (80% of fare)
    public double getRefundAmount() {
        return fare * 0.80;
    }
    
    @Override
    public String toString() {
        return String.format("╔═══════════════════════════════════════════════════════════════════════════════╗\n" +
                           "║                                  TICKET DETAILS                              ║\n" +
                           "╠═══════════════════════════════════════════════════════════════════════════════╣\n" +
                           "║ PNR: %-15s │ Status: %-15s │ Train: %-15s ║\n" +
                           "║ From: %-14s │ To: %-18s │ Date: %-16s ║\n" +
                           "║ Class: %-13s │ Seat: %-17s │ Fare: $%-15.2f ║\n" +
                           "║ Payment: %-25s │ Booking ID: %-25s ║\n" +
                           "╚═══════════════════════════════════════════════════════════════════════════════╝",
                           PNRno, status, trainCode,
                           source, destination, dateOfJourney,
                           seatClass, seatNumber, fare,
                           paymentType, PNRno);
    }
}
