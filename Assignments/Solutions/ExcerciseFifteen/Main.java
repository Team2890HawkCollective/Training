public class Main {
    public static void main(String[] args) throws Exception {
        OuterClass out = new OuterClass();
        OuterClass.shooter shoot = out.new shooter(15, 30, 45);
        shoot.shoot();
        OuterClass.Force.changeForce(10);
        shoot.shoot();
    }
}
