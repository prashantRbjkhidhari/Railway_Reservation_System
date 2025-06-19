package com.railway.service;

import com.railway.model.Train;
import com.railway.model.Passenger;
import com.railway.model.Ticket;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReservationService {
    private Map<String, Ticket> tickets;

    public ReservationService() {
        this.tickets = new HashMap<>();
    }

    public Ticket bookTicket(Train train, Passenger passenger, LocalDate journeyDate) {
        if (train.getAvailableSeats() > 0) {
            Ticket ticket = new Ticket(train, passenger, journeyDate);
            tickets.put(ticket.getPnrNumber(), ticket);
            train.setAvailableSeats(train.getAvailableSeats() - 1);
            return ticket;
        }
        throw new RuntimeException("No seats available!");
    }

    public boolean cancelTicket(String pnrNumber) {
        Ticket ticket = tickets.get(pnrNumber);
        if (ticket != null && !ticket.getStatus().equals("CANCELLED")) {
            ticket.setStatus("CANCELLED");
            Train train = ticket.getTrain();
            train.setAvailableSeats(train.getAvailableSeats() + 1);
            return true;
        }
        return false;
    }

    public Ticket getTicketByPNR(String pnrNumber) {
        return tickets.get(pnrNumber);
    }
}
