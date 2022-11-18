public class TryCatchStarter {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Point");
        int a = 3;
        int b = 2;
        int c = 0;
        int d = a * b;
        int e = b / c;
        System.out.println("Checkpoint 1");
        String[] arr = {"cat", "dog", "pig", "ferret", "bird"};
        String cat = arr[0];
        String dog = arr[3];
        String bird = arr[5];
        System.out.println("Checkpoint 2");
        throw new NullPointerException("Don't Mind Me");
        System.out.println("Finished");
    }
}
