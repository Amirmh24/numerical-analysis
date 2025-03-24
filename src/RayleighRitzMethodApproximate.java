public class RayleighRitzMethodApproximate {
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
        for (int i = 1; i <= N - 1; i++) {

            if (i < N - 1)
                Q1[i] = h / 12 * (q( x[i] ) + q( x[i + 1] ));
            Q2[i] = h / 12 * (3 * q( x[i] ) + q( x[i - 1] ));
            Q3[i] = h / 12 * (3 * q( x[i] ) + q( x[i + 1] ));
            Q4[i] = h / 2 * (p( x[i] ) + p( x[i - 1] ));
            Q5[i] = h / 6 * (2 * f( x[i] ) + f( x[i - 1] ));
            Q6[i] = h / 6 * (2 * f( x[i] ) + f( x[i + 1] ));
        }

        Q2[N] = h / 12 * (3 * q( x[N] ) + q( x[N - 1] ));
        Q3[N] = h / 12 * (3 * q( x[N] ) + q( x[N + 1] ));
        Q4[N] = h / 2 * (p( x[N] ) + p( x[N - 1] ));
        Q4[N + 1] = h / 2 * (p( x[N + 1] ) + p( x[N] ));
        Q5[N] = h / 6 * (2 * f( x[N] ) + f( x[N - 1] ));
        Q6[N] = h / 6 * (2 * f( x[N] ) + f( x[N + 1] ));
        System.out.println(Q1[N]);

        for (int i = 1; i <= N - 1; i++) {
            A[i] = Q2[i] + Q3[i] + Q4[i] + Q4[i + 1];
            B[i] = Q1[i] - Q4[i + 1];
            b[i] = Q5[i] + Q6[i];
        }
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
        System.out.println(h);
        for (int i = 1; i <= N; i++) {
            System.out.println("c"+i+"= "+c[i]);
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

    public static double PHI(double w, int i) {
        if (1 <= i && i <= N) {
            if (0 <= w && w <= x[i - 1]) {
                return 0;
            }
            if (x[i - 1] <= w && w <= x[i]) {
                return (w - x[i - 1]) / h;
            }
            if (x[i] <= w && w <= x[i + 1]) {
                return (x[i + 1] - w) / h;
            }
            if (x[i + 1] <= w && w <= 1) {
                return 0;
            }
        }
        return 0;
    }

    public static void setX() {
        for (int i = 0; i < x.length; i++) {
            x[i] = i * h;
        }
    }
}
