public class Main {
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
