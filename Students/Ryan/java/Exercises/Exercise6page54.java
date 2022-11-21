package Exercises;
import java.util.ArrayList;

public class Exercise6page54 {
    public static void main(String args[])
    {
        ArrayList<Double> numbersList = new ArrayList<Double>();
        numbersList.add(4.3);
        numbersList.add(27.8);
        numbersList.add(1.0);
        numbersList.add(3.45);
        numbersList.add(99.99);

        System.out.println("the ArrayList has " + numbersList.size() + " entries");
        
        numbersList.add(3, 2.5);
        //System.out.println(numbersList);

        numbersList.remove(27.8);
        //System.out.println(numbersList);

        numbersList.set(numbersList.indexOf(4.3), 5.55);
        //System.out.println(numbersList);

        for( double element: numbersList)
        {
            System.out.println(element);
        }
    }
}
