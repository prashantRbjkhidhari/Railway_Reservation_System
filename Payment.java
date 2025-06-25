public class Payment {
    private double amount;
    private String ticketGeneration;
    
    public Payment(double amount, String ticketGeneration) {
        this.amount = amount;
        this.ticketGeneration = ticketGeneration;
    }
    
    // Getters and Setters
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getTicketGeneration() {
        return ticketGeneration;
    }
    
    public void setTicketGeneration(String ticketGeneration) {
        this.ticketGeneration = ticketGeneration;
    }
    
    // Make payment functionality
    public boolean makes(String paymentMethod, double paymentAmount) {
        if (paymentAmount >= this.amount) {
            System.out.println("Payment of $" + paymentAmount + " processed successfully via " + paymentMethod);
            this.ticketGeneration = "SUCCESS";
            return true;
        } else {
            System.out.println("Insufficient payment amount. Required: $" + this.amount + ", Provided: $" + paymentAmount);
            this.ticketGeneration = "FAILED";
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Payment Amount: $" + amount + ", Status: " + ticketGeneration;
    }
}
