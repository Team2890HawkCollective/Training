package Exercises.Exercise11Page87;

public class Exercise11Main {

    public static void main(String args[]){





   Holiday holiday1 = new Holiday("Christmas", 25, "December");
   Holiday holiday2 = new Holiday("Christmas", 25, "February");
   
    if(holiday1.inSameMonth(holiday2))
    {
        System.out.println("Holidays are in the same month");
    }
    else
    {
        System.out.println("Holidays are NOT in the same month");
    }
   
    }
}



