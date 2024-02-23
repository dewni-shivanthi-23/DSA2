import java.util.Scanner;

public class LaughsParkingGarage {

    //Maximum capacity of the parking garage
    private static final int MAX_CAPACITY = 10;

    //Queues to store cars in the garage and waiting queue
    Queue garage = new Queue(MAX_CAPACITY);
    Queue waitingQueue = new Queue(10);

    public static void main(String[] args) {
        // Create an instance of the parking garage and run the simulation
        LaughsParkingGarage garage = new LaughsParkingGarage();
        garage.runSimulation();
    }

    private void runSimulation() {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message and get the user input
        System.out.println("------Welcome to Laughs Parking Garage!-----");
        System.out.println("License plate number = ABC123");
        System.out.println("Input line for arrival = a ABC123");
        System.out.println("Input line for departure = d ABC123");
        System.out.println("---------------------------------------------");

        System.out.println("Enter 'a' for arrival or 'd' for departure followed by the license plate number:");

        // Loop indefinitely until user exits
        while (true) {
            // Read user input
            String line = scanner.nextLine();

            // Extract action and license plate from input
            String action = line.substring(0, 1);
            String licensePlate = line.substring(2).trim();

            // Handle arrival or departure based on user input
            if (action.equals("a")) {
                handleArrival(licensePlate);
            } else if (action.equals("d")) {
                handleDeparture(licensePlate);
            } else {
                // Handle invalid input
                System.out.println("Invalid input. Please enter 'a' for arrival or 'd' for departure.");
            }
        }
    }


    private void handleArrival(String licensePlate) {
        // Create a new car object
        Car newcar=new Car(licensePlate);

        // Check if there is space in the garage
        if (garage.size() < MAX_CAPACITY) {
            // Add the car to the garage queue
            garage.enqueue(new Car(licensePlate));
            System.out.println("Car " + licensePlate + " arrived and parked to the line successfully.");
        } else {
            // Add the car to the waiting queue if garage is full
            System.out.println("Car " + licensePlate + " arrived but there is no room for the car in the garage. Successfully added to waiting queue.");
            waitingQueue.enqueue(new Car(licensePlate));
        }
    }

    private void handleDeparture(String licensePlate) {
        // Initialize number of moves made
        int moves = 0;

        // Check if car is in waiting queue
        if (!waitingQueue.isEmpty() && waitingQueue.peek().getLicensePlate().equals(licensePlate)) {
            // Car found in waiting queue, remove it and print message
            waitingQueue.dequeue();
            System.out.println("Car " + licensePlate + " departed from waiting queue (moved 0 times).");
            return;
        }

        // Car not found in waiting queue, search for it in the garage
        Car car = null;
        while (!garage.isEmpty()) {
            // Get the next car from the garage queue
            Car currentCar = garage.dequeue();

            // Check if the current car is the one we're looking for
            if (currentCar.getLicensePlate().equals(licensePlate)) {
                car = currentCar;
                break;
            }
            // Increment move count and add current car to waiting queue
            moves++;
            waitingQueue.enqueue(currentCar);
        }

        // Handle different scenarios based on whether the car was found
        if (car != null) {
            // Car found, print message with number of moves made
            System.out.println("Car " + car.getLicensePlate() + " departed (moved " + moves + " times within the garage).");

            // Move cars back from waiting queue if space available
            while (!waitingQueue.isEmpty() && garage.size() < MAX_CAPACITY) {
                garage.enqueue(waitingQueue.dequeue());
            }
        } else {
            // Car not found, print message
            System.out.println("Car " + licensePlate + " not found in garage or waiting queue.");
        }
    }
}
