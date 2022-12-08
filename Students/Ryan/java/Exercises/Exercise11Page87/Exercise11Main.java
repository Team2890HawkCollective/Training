package Exercises.Exercise11Page87;

public class Exercise11Main {

    public static void main(String args[]){





   Holiday holiday1 = new Holiday("Independence Day", 4, "July");
   Holiday holiday2 = new Holiday("Canadian Independence Day", 1, "July");

    if(holiday1.inSameMonth(holiday2))
    {
        System.out.println("Holidays are in the same month");
    }
    else
    {
        System.out.println("Holidays are NOT in the same month");
    }
   
    }


    Movie movie1 = new Movie("Casino Royale", "Eon Productions", "PG-13");


}


