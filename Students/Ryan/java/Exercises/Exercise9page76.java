package Exercises;

public class Exercise9page76 {
    public static void main(String args[])
    {
        String sentence = "The quick brown fox jumps over the lazy dog";

        System.out.println(sentence.length());
        String sentencePart = sentence.substring(31, sentence.length());
        System.out.println(sentencePart);

        System.out.println(sentence.compareToIgnoreCase(sentencePart));

        String [] stringArray = sentencePart.split(" ");
        
        String words = ""; 

        for (String element: stringArray)
        {
            words += element + " ";
        }

        System.out.println(stringArray.length);
    }
}
