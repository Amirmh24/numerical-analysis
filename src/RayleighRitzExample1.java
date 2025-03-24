public class RayleighRitzExample1{
    static int N = 1;
    static double h = 1.0 / (N + 1);
    static double x[] = new double[N + 2];
    static double Q1[] = new double[N];
    static double Q2[] = new double[N + 1];
    static double Q3[] = new double[N + 1];
    static double Q4[] = new double[N + 2];
    static double Q5[] = new double[N + 1];
    static double Q6[] = new double[N + 1];
    static double A[] = new double[N + 1];
    static double B[] = new double[N + 1];
    static double b[] = new double[N + 1];
    static double a[] = new double[N + 1];
    static double y[] = new double[N + 1];
    static double z[] = new double[N + 1];
    static double c[] = new double[N + 1];
    static int ansAcc = 5;
    static int calAcc = 3;

    public static void main(String[] args) {
        setX();

        Q2[N] = h / 12 * (3 * q( x[N] ) + q( x[N - 1] ));
        Q3[N] = h / 12 * (3 * q( x[N] ) + q( x[N + 1] ));
        Q4[N] = h / 2 * (p( x[N] ) + p( x[N - 1] ));
        Q4[N + 1] = h / 2 * (p( x[N + 1] ) + p( x[N] ));
        Q5[N] = h / 6 * (2 * f( x[N] ) + f( x[N - 1] ));
        Q6[N] = h / 6 * (2 * f( x[N] ) + f( x[N + 1] ));


        A[N] = Q2[N] + Q3[N] + Q4[N] + Q4[N + 1];
        b[N] = Q5[N] + Q6[N];
//        matrix
        a[1]=A[1];
        y[1]=B[1]/A[1];
        z[1]=b[1]/a[1];
        for (int i = 2; i <= N-1 ; i++) {
            a[i]=A[i]-B[i-1]*y[i-1];
            y[i]=B[i]/a[i];
            z[i]=(b[i]-B[i-1]*z[i-1])/a[i];
        }
        a[N]=A[N]-B[N-1]*y[N-1];
        z[N]=(b[N]-B[N-1]*z[N-1])/a[N];
//        Answer
        c[N]=z[N];
        for (int i = N-1; i >=1 ; i--) {
            c[i]=z[i]-y[i]*c[i+1];
        }
        System.out.println("h = "+h);
        System.out.println("Q2 = "+Q2[1]);
        System.out.println("Q3 = "+Q3[1]);
        System.out.println("Q4 = "+Q4[1]);
        System.out.println("Q4 = "+Q4[2]);
        System.out.println("Q5 = "+Q5[1]);
        System.out.println("Q6 = "+Q6[1]);
        System.out.println(  );
        for (int i = 1; i <= N; i++) {
            System.out.println("c"+i+" = "+c[i]);
        }
    }

    public static double p(double x) {
        double ans = 2*(x+1);
        return ans;
    }

    public static double q(double x) {
        double ans = (x+1)*(x+1);
        return ans;
    }

    public static double f(double x) {
        double ans = Math.exp(x+1)+2*x-(x+1)*(x+1)*(x-1);
        return ans;
    }

    public static double rond(double x, int a) {
        double ans = (double) (Math.round( x * ((double) Math.pow( 10, a )) )) / ((double) Math.pow( 10, a ));
        return ans;
    }

    public static void setX() {
        for (int i = 0; i < x.length; i++) {
            x[i] = i * h;
        }
    }
}
