public class OuterClass {
    private int x;
    private int y;
    private int z;

    private static int multiplier = 1;

    public class shooter {
        public shooter(int X, int Y, int Z)
        {
            x = X;
            y = Y;
            z = Z;
        }

        public void shoot()
        {
            System.out.println("x: " + (x*multiplier));
            System.out.println("y: " + (y*multiplier));
            System.out.println("z: " + (z*multiplier));
        }
    }

    public static class Force {
        public static void changeForce(int mult) {
            multiplier = mult;
        }
    }
}
