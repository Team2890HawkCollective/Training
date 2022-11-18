public class Main {
    /**
     * This is the main method of this assignment.
     * All it does is creates a Car object with brand set to "Honda", yearCreated set to 2009, and model set to "CRV"
     * then creates a Airplane object with brand set to "Boeing", yearCreated set to 2000, and id set to 717
     * it prints all these variables plus the transportation mode of each object.
     */
    public static void main(String[] args) throws Exception {
        Car car = new Car("Honda", 2009, "CRV");

        System.out.println("Brand: " + car.brand);
        System.out.println("Year: " + car.yearCreated);
        System.out.println("Model: " + car.model);
        System.out.println("Mode: " + car.mode);

        Airplane plane = new Airplane("Boeing", 2000, 717);

        System.out.println("Brand: " + plane.brand);
        System.out.println("Year: " + plane.yearCreated);
        System.out.println("ID: " + plane.id);
        System.out.println("Mode: " + plane.mode);
    }
}
