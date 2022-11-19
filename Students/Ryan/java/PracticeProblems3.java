import java.util.ArrayList;

public class PracticeProblems3 {
    public static void main(String args[])
    {
        int [] integerArray = {1,2,3,4,5};

        for(int i=0; i<integerArray.length; i++)
        {
            System.out.println(integerArray[i]);
        }

        System.out.println();

        int i = 0; 
        while (i < integerArray.length)
        {
            System.out.println(integerArray[i]);
            i++;
        }

        System.out.println();

        for (int element: integerArray)
        {
            System.out.println(element);
        }

        System.out.println();

        ArrayList<Integer> numList2 = new ArrayList<Integer>();
        
        for (int element: integerArray)
        {
            numList2.add(element);
        }
        numList2.add(6);

        for (int element: numList2)
        {
            System.out.println(numList2.get(element-1));
        }

    }
}
