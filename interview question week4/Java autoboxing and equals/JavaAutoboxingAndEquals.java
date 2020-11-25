public class JavaAutoboxingAndEquals {
    public static void main(String[] args) {
        double a = 0.0;
        double b = -0.0;
        Double x = 0.0;
        Double y = -0.0;

        System.out.println("first situation, a == b is true but x.equals(y) is false");
        System.out.println(a == b);
        System.out.println(x.equals(y));
        double a1 = 0.0 / 0;
        double b1 = 0.0 / 0;
        Double x1 = 0.0 / 0;
        Double y1 = 0.0 / 0;
        System.out.println(
                "first situation, a == b is false but x.equals(y) is true. This situation happeneded because 0.0 / 0 is NaN");
        System.out.println(a1 == b1);
        System.out.println(x1.equals(y1));


    }
}
