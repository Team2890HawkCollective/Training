package Exercises;

public class Exercise7Page60 {
    public static void main(String args[])
    {
        int smallNum = smallest(21, 43, 7);
        int largeNum = largest(21, 43, 7);
        int avgNum = average(21, 43, 7);

        print(smallNum, largeNum, avgNum);
    }

    private static int smallest(int a, int b, int c)
    {
        if(a < b && a < c)
        return a;

        if(b < a && b < c)
        return b;

        if(c < a && c < b)
        return c;

        else
        return a; 
    }

    private static int largest(int a, int b, int c)
    {
        if(a > b && a > c)
        return a;

        if(b > a && b > c)
        return b;

        if(c > a && c > b)
        return c;

        else
        return a; 
    }

    private static int average(int a, int b, int c)
    {
        return (a + b + c) / 3;
    }

    private static void print(int a, int b, int c)
    {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
