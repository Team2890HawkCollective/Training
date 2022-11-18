/**
 * This is the Airplane subclass that extends Vehicle
 */
public class Airplane extends Vehicle{

    // id holds the id of the type of Airplane. e.g. 717
    public int id;

    /**
     * This is the constructor of Airplane. It automatically sets mode to "fly"
     * @param br the company brand of the Airplane
     * @param year the year the Airplane was manufactured
     * @param identification the ID of the type of airplane. e.g. 717
     */
    public Airplane(String br, int year, int identification)
    {
        // brand comes from the Vehicle superclass
        brand = br;
        // yearCreated comes from the Vehicle superclass
        yearCreated = year;
        // id comes from the local class
        id = identification;
        // mode comes from the Vehicle superclass
        mode = "fly";
    }
}
