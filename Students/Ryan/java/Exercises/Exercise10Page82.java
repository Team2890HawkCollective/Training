package Exercises;

import java.util.ArrayList;
import java.util.Scanner;




// DO NOT COMMIT WITHOUT ASKING ABOUT INPUTS 
// HOW ON EARTH DO I INPUT IN VSCODE????




public class Exercise10Page82 {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        System.out.println("Please enter positive integers one at a time.");
        System.out.println("You may exit by entering any negative integer");
       
        boolean flag = true;
        int input; 

        while (flag);
        {
            
            input = in.nextInt();
            if (input < 0)
            {
                flag = false; 
            } 
            else
            {
                intArray.add(input);
            }
        } 
        int total = 0; 

        for (int element: intArray)
        {
            total = total + element; 
        }
        int avg = total / intArray.size();
        System.out.println(avg);
    }
}
