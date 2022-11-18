/**
 * This is the Car subclass that extends Vehicle
 */
public class Car extends Vehicle{

    // model holds the model of the car
    public String model;

    /**
     * This is the constructor of Car. It automatically sets mode to "drive"
     * @param make the company brand of the Car
     * @param year the year the Car was manufactured
     * @param mo the model of the Car
     */
    public Car(String make, int year, String mo)
    {
        // brand comes from the Vehicle superclass
        brand = make;
        // yearCreated comes from the Vehicle superclass
        yearCreated = year;
        // model comes from the local class
        model = mo;
        // mode comes from the Vehicle superclass
        mode = "drive";
    }
    
}
