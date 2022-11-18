public class Main {
    public static void main(String[] args) throws Exception {
        President pres1 = new President("Theodore", "Roosevelt", 26);
        President pres2 = new President("George", "Washington", 1);

        //pres2.firstName should print null
        System.out.println("pres1: " + pres1.firstName);
        System.out.println("pres2: " + pres2.firstName);

        //Calling the static print method
        President.print();
    }
}
