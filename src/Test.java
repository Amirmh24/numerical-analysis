public class Test {
    public static void main(String[] args) {
        double x1=0.70197/2;
        double x2=-0.65459/2;
        double x3=0.28064/2;
        System.out.println(Math.pow(f1(x1,x2,x3),2)+Math.pow(f2(x1,x2,x3),2)+Math.pow(f3(x1,x2,x3),2));
    }

    public static double f1(double x1, double x2, double x3) {
        return 4 * x2 * x2 + 8 * x2 - 26 * x1 + 17.75;
    }

    public static double f2(double x1, double x2, double x3) {
        return 4 * x3 * x3 + 4 * x1 * x1 - 8 * x1 * x3 + 16 * x3 - 26 * x2 + 16 * x1 - 2.5;
    }

    public static double f3(double x1, double x2, double x3) {
        return 4 * x2 * x2 + 12 * x2 - 26 * x3 + 6.25;
    }
}
