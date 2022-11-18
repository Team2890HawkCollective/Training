public class President {
    public String firstName;
    public String lastName;
    public int ID;

    private static boolean inOffice = false;

    public President(String first, String last, int id)
    {
        if(!inOffice)
        {
            firstName = first;
            lastName = last;
            ID = id;
            inOffice = true;
        }
        else
        {
            firstName = null;
            lastName = null;
            ID = -1;
        }
    }

    public static void print()
    {
        System.out.println("I am the president of the United States");
    }

}
