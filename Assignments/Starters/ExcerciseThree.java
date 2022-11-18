public class ExcerciseThree {
    public static void main(String[] args) throws Exception {
        String currentDay = "Tuesday";

        if (currentDay == "Monday")
            System.out.println("5 days until the weekend...");
        else if (currentDay == "Tuesday")
            System.out.println("4 days until the weekend...");
        else if (currentDay == "Wednesday")
            System.out.println("3 days until the weekend...");
        else if (currentDay == "Thursday")
            System.out.println("2 days until the weekend...");
        else if (currentDay == "Friday")
            System.out.println("1 day until the weekend...");
        else
            System.out.println("IT'S THE WEEKEND!");

        System.out.println("The current month is:");
        int month = 6;
        String monthString;
        if (month == 1)
            monthString = "January";
        else if (month == 2)
            monthString = "February";
        else if (month == 3)
            monthString = "March";
        else if (month == 4)
            monthString = "April";
        else if (month == 5)
            monthString = "May";
        else if (month == 6)
            monthString = "June";
        else if (month == 7)
            monthString = "July";
        else if (month == 8)
            monthString = "August";
        else if (month == 9)
            monthString = "September";
        else if (month == 10)
            monthString = "October";
        else if (month == 11)
            monthString = "November";
        else if (month == 12)
            monthString = "December";
        else
            monthString = "ERR: INVALID MONTH";

        System.out.println(monthString);
    }
}
