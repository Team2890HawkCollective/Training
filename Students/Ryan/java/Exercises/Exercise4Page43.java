package Exercises;
public class Exercise4Page43 {
    public static void main(String args[])
    {
        String [] animalNames = {"cats", "dogs", "birds", "turtles", "frogs"};
        int [] numAvailable = {5, 3, 10, 2, 4};

        System.out.println(animalNames[0] + " " + numAvailable[0]);
        System.out.println(animalNames[1] + " " + numAvailable[1]);
        System.out.println(animalNames[2] + " " + numAvailable[2]);
        System.out.println(animalNames[3] + " " + numAvailable[3]);
        System.out.println(animalNames[4] + " " + numAvailable[4]);

        System.out.println();
        animalNames[3] = "ferrets";

        System.out.println(animalNames[0] + " " + numAvailable[0]);
        System.out.println(animalNames[1] + " " + numAvailable[1]);
        System.out.println(animalNames[2] + " " + numAvailable[2]);
        System.out.println(animalNames[3] + " " + numAvailable[3]);
        System.out.println(animalNames[4] + " " + numAvailable[4]);
    }
}