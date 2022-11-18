public class ExcerciseFour {
    public static void main(String[] args) throws Exception {
        String[] animals = {"Cats", "Dogs", "Birds", "Turtles", "Frogs"};
        int[] inventory = {5, 3, 10, 2, 4};

        System.out.println("We have " + inventory[0] + " " + animals[0]);
        System.out.println("We have " + inventory[1] + " " + animals[1]);
        System.out.println("We have " + inventory[2] + " " + animals[2]);
        System.out.println("We have " + inventory[3] + " " + animals[3]);
        System.out.println("We have " + inventory[4] + " " + animals[4]);

        animals[3] = "Ferrets";

        System.out.println("We have " + inventory[0] + " " + animals[0]);
        System.out.println("We have " + inventory[1] + " " + animals[1]);
        System.out.println("We have " + inventory[2] + " " + animals[2]);
        System.out.println("We have " + inventory[3] + " " + animals[3]);
        System.out.println("We have " + inventory[4] + " " + animals[4]);
    }
}
