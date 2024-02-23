public class Car {
    // String to store the license plate number of the car
    private String licensePlate;

    Car(String licensePlate){
        // Assign the license plate number to the `licensePlate` field.
        this.licensePlate=licensePlate;
    }

    //Getter method to return the license plate number of the car.
    public String getLicensePlate() {
        // Return the stored license plate number.
        return licensePlate;
    }
}
