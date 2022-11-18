import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> inputted = new ArrayList<Integer>();
        int newInput = 0;

        System.out.println("Please enter positive integers one at a time.");
        System.out.println("You may exit by entering any negative integer.");

        Scanner scan = new Scanner(System.in);

        while(newInput >= 0)
        {
            newInput = scan.nextInt();

            if(newInput >= 0)
                inputted.add(newInput);
        }

        scan.close();

        int sum = 0;
        for(int i = 0; i < inputted.size(); i++)
            sum += inputted.get(i);

        if(inputted.size() != 0)
        {
            int avg = sum / inputted.size();
            System.out.println("Average is: " + avg);
        }
        else
        {
            System.out.println("No positive integers were added...");
        }
    }
}
