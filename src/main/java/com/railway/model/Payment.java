package com.railway.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private String paymentId;
    private Ticket ticket;
    private double amount;
    private String paymentStatus;
    private LocalDateTime paymentDateTime;

    public Payment(Ticket ticket) {
        this.paymentId = generatePaymentId();
        this.ticket = ticket;
        this.amount = ticket.getFare();
        this.paymentStatus = "PENDING";
        this.paymentDateTime = LocalDateTime.now();
    }

    private String generatePaymentId() {
        return "PAY" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public void processPayment() {
        // In a real system, this would integrate with a payment gateway
        this.paymentStatus = "COMPLETED";
    }

    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public String generateReceipt() {
        return "Payment Receipt\n" +
               "---------------\n" +
               "Payment ID: " + paymentId + "\n" +
               "Amount: Rs. " + amount + "\n" +
               "Status: " + paymentStatus + "\n" +
               "Date: " + paymentDateTime + "\n" +
               "PNR: " + ticket.getPnrNumber() + "\n" +
               "Passenger: " + ticket.getPassenger().getName();
    }
}
