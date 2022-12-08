    package Exercises;

public class Exercise8Page64 {
    public static void main(String args[])
    {
        System.out.println(factorial(12));
    }

    private static int factorial(int input)
    {
        if(input == 1)
            return 1;
        else
            return (input * factorial(input-1));
    }
}
