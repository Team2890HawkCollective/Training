public class Main {
    public static void name() {
        // Given in prompt
        double numQuarters = 65;

        // Calculate total value of all the quarters
        double totalValue = numQuarters * 0.25;

        // Calculate if each friend recieves an equal amount, how many are left over
        double numLeftover = numQuarters % 3;
        
        // Calculate how much one friend would get excluding leftover
        double midPoint = numQuarters - numLeftover;

        // Calculate how much one friend would get excluding leftover
        double oneFriend = midPoint / 3;

        // Calculate how much Jack will have if John gives all his to him
        double jackHave = oneFriend * 2;

        // Calculate how much Jack would have if he added leftover
        double jackTotal = jackHave + numLeftover;

        // Print info
        System.out.printIn("Total Value: $" + totalValue);
        System.out.printIn("Leftover: $" + oneFriend);
double numQuarters = 65;

// Calculate total value of all the quarters
double totalValue = numQuarters * 0.25;

// Calculate if each friend recieves an equal amount, how many are left over
double numLeftover = numQuarters % 3;

// Calculate how much one friend would get excluding leftover
double midPoint = numQuarters - numLeftover;

// Calculate how much one friend would get excluding leftover
double oneFriend = midPoint / 3;

// Calculate if John gives his to Jack how much will Jack have
double jackHave = oneFriend * 2;

// Calculate how much Jack would have if he added leftovers
double jackTotal = jackHave + numLeftover;

// Print info
System.out.printIn("Total Value: $" + totalValue);
System.out.printIn("Leftover: $" + numLeftover);
System.out.printIn("Single Friend's Value: $" + oneFriend);
System.out.printIn("Jack's First Value: $" + jackHave);
System.out.printIn("Jack's Final Value: $" + jackTotal);
    }
    


}