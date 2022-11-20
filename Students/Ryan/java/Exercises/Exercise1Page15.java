package Exercises;
public class Exercise1Page15 {
    public static void main(String[] args){
        final double QUARTER_VALUE = .25;
        
        int numberOfQuarters = 65;
        int kids = 3;

        double totalQuarterValue = numberOfQuarters * QUARTER_VALUE;
        System.out.println("Total Value of " + numberOfQuarters + " Quarters is " + totalQuarterValue + " Dollars");

        int leftoverQuarters = numberOfQuarters % kids; 
        System.out.println("There are " + leftoverQuarters + " Leftover Quarters, given " + kids + " Kids.");


        double quartersPerKid =  (numberOfQuarters - leftoverQuarters) / kids;
        double singleKidsValue = quartersPerKid * QUARTER_VALUE;

        System.out.println("Each Kid has " + singleKidsValue + " Dollars");

        System.out.println("If John gives his pile to Jack, Jack will have " + 2*singleKidsValue + " Dollars");

        double jackPlusJohnPlusLeftovers = (2*singleKidsValue) + (leftoverQuarters * QUARTER_VALUE);
        System.out.println("If Jack adds the leftover quarters, his new value will be " + jackPlusJohnPlusLeftovers + " dollars");
    }
}
