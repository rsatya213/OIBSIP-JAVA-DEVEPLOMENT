import java.util.*;
public class OnlineReservationSystem {
    static Map<String, Reservation> reservations = new HashMap<>();
    static final String username = "admin";
    static final String password = "1234";
    static class Reservation {
        int trainNumber;
        String trainName;
        String classType;
        String from;
        String to;
        String dateOfJourney;
        Reservation(int trainNumber, String trainName, String classType, String from, String to, String dateOfJourney) {
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.from = from;
            this.to = to;
            this.dateOfJourney = dateOfJourney;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("");
        System.out.println("         ONLINE RESERVATION SYSTEM          ");
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("");
        System.out.print("             Username : ");
        String inputUsername = sc.nextLine();
        System.out.print("             Password : ");
        String inputPassword = sc.nextLine();
        System.out.println("");
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("-------------------------------------------");
            System.out.println("          Login successful...               ");
            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------");
            System.out.println("");
            System.out.println("Please fill the details for your reservation :");
            System.out.println("");
            System.out.print("Train number: ");
            int trainNumber = sc.nextInt();
            sc.nextLine();
            System.out.print("Train name: ");
            String trainName = sc.nextLine();
            System.out.print("Class type: ");
            String classType = sc.nextLine();
            System.out.print("From: ");
            String from = sc.nextLine();
            System.out.print("To: ");
            String to = sc.nextLine();
            System.out.print("Date of journey: ");
            String dateOfJourney = sc.nextLine();
            System.out.println("");
            Reservation reservation = new Reservation(trainNumber, trainName, classType, from, to, dateOfJourney);
            String pnr = generatePNR();
            reservations.put(pnr, reservation);
            printTicket(reservation);
            System.out.println("Your PNR number: " + pnr); // Display PNR number
            System.out.println("Enter PNR number to cancel a reservation: ");
            String pnrNumber = sc.nextLine();
            if (reservations.containsKey(pnrNumber)) {
                System.out.println("");
                System.out.println("Reservation details:");
                printTicket(reservations.get(pnrNumber));
                System.out.println("Do you want to cancel this ticket (OK/Cancel): ");
                String confirmation = sc.nextLine();
                if (confirmation.equalsIgnoreCase("OK")) {
                    reservations.remove(pnrNumber);
                    System.out.println("");
                    System.out.println("Reservation cancelled successfully...");
                } else {
                    System.out.println("Reservation not cancelled...");
                }
            } else {
                System.out.println("Invalid PNR number.");
            }
        } else {
            System.out.println("");
            System.out.println("Invalid login credentials...        ");
            System.out.println("");
        }
        sc.close();
    }
    static String generatePNR() {
        return "PNR" + (int)(Math.random() * 10000);
    }
    static void printTicket(Reservation reservation) {
        System.out.println("-------------------------------------------");
        System.out.println("");
        System.out.println("            TICKET DETAILS                ");
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("");
        System.out.println("Train Number: " + reservation.trainNumber);
        System.out.println("Train Name: " + reservation.trainName);
        System.out.println("Class Type: " + reservation.classType);
        System.out.println("From: " + reservation.from);
        System.out.println("To: " + reservation.to);
        System.out.println("Date of Journey: " + reservation.dateOfJourney);
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("");
    }
}
