public class Airplane extends Vehicle{

    public int id;

    public Airplane(String br, int year, int identification)
    {
        brand = br;
        yearCreated = year;
        id = identification;
        mode = "fly";
    }
}
