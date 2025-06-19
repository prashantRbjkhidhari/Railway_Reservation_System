# Railway Reservation System

A simple console-based Railway Reservation System implemented in Java. This system allows users to manage train bookings, passenger information, and reservations.

## Features

- Train Management
  - Add/Remove trains
  - View train schedules
  - Check train availability

- Passenger Management
  - Register new passengers
  - View passenger details
  - Update passenger information

- Reservation System
  - Book tickets
  - Cancel reservations
  - Check booking status
  - View ticket details

- Payment Processing
  - Handle ticket payments
  - Generate payment receipts

## Project Structure

```
Railway_Reservation_System/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/
│   │       │   └── railway/
│   │       │       ├── model/
│   │       │       │   ├── Train.java
│   │       │       │   ├── Passenger.java
│   │       │       │   ├── Ticket.java
│   │       │       │   ├── Payment.java
│   │       │       │   └── Station.java
│   │       │       ├── service/
│   │       │       │   ├── ReservationService.java
│   │       │       │   ├── TrainService.java
│   │       │       │   ├── PassengerService.java
│   │       │       │   └── PaymentService.java
│   │       │       └── Main.java
│   └── test/
│       └── java/
│           └── com/
│               └── railway/
│                   └── service/
│                       ├── ReservationServiceTest.java
│                       ├── TrainServiceTest.java
│                       └── PassengerServiceTest.java
```

## Setup Instructions

1. Ensure you have Java JDK 11 or higher installed
2. Clone this repository
3. Navigate to the project directory
4. Compile the project:
   ```bash
   javac -d bin src/main/java/com/railway/*.java
   ```
5. Run the application:
   ```bash
   java -cp bin com.railway.Main
   ```

## Class Structure

- **Train**: Represents train details including train number, name, source, destination, and available seats
- **Passenger**: Contains passenger information such as name, age, and contact details
- **Ticket**: Manages booking information including PNR, train details, passenger details, and journey date
- **Payment**: Handles payment processing and receipt generation
- **Station**: Contains station details such as station code and name

## Usage

1. Run the application
2. Choose from the available menu options:
   - Book a ticket
   - Cancel a ticket
   - Check PNR status
   - View train schedule
   - Exit

## Contributing

Feel free to contribute to this project by creating pull requests or reporting issues.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
