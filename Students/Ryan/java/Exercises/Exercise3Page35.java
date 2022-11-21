package Exercises;
public class Exercise3Page35 {
    public static void main(String args[]){
        String currentDay = "Tuesday";

        switch(currentDay)
        {
            case "Monday":
                System.out.println("5 days until the weekend...");
                break;
            case "Tuesday":
                System.out.println("4 days until the weekend...");
                break;
            case "Wednesday":
                System.out.println("3 days until the weekend...");
                break;
            case "Thursday":
                System.out.println("2 days until the weekend...");
                break;
            case "Friday":
                System.out.println("5 days until the weekend...");
                break;
            default:
                System.out.println("IT'S THE WEEKEND!");
        }
        System.out.println("The current month is:");
        int month = 6; 
        String monthString;
        switch(month)
        {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break; 
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "ERR: INVALID MONTH";
            
        }
        System.out.println(monthString);
    }
}
