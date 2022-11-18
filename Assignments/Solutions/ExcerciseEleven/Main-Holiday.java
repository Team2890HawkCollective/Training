public class Main {
    public static void main(String[] args) throws Exception {
        //Answe D
        Holiday hol = new Holiday("Independence Day", 4, "July");

        //Not answers, but extra code to show you the results of the methods
        Holiday secondHol = new Holiday("Christmas", 25, "December");
        Holiday thirdHol = new Holiday("Halloween", 27, "October");

        System.out.println("Is independence day in the same month as christmas?");
        System.out.println(hol.inSameMonth(secondHol));

        Holiday[] holidays = {hol, secondHol, thirdHol};
        System.out.println("What is the average day of the three holidays?");
        //We can do Holiday.avgDate instead of using one of the objects declared above since avgDate is a static method
        //Static methods get called by using the type of object, not an instance of the object like inSameMonth
        System.out.println(Holiday.avgDate(holidays));
    }
}
