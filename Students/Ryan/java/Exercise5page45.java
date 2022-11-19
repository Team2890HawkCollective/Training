public class Exercise5page45 {
    public static void main(String args[])
    {
        String [] animalNames = {"cats", "dogs", "birds", "turtles", "frogs"};
        int [] numAvailable = {5, 3, 10, 2, 4};

        int i = 0;
        while (i< animalNames.length)
        {
            System.out.println(animalNames[i] + " " + numAvailable[i]);
            i++;
        }

        System.out.println();
        animalNames[3] = "ferrets";

        for(int j = 0; j<animalNames.length; j++)
        {
            System.out.println(animalNames[j] + " " + numAvailable[j]);
        }
    }
}
