public class RayleighRitzMethod {
    static int N = 9;
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
    static double l = 0.000001;

    public static void main(String[] args) {
        setX();
        for (int i = 1; i <= N - 1; i++) {

            if (i < N - 1)
                Q1[i] = (1 / (h * h)) * calQ( i, 1 );
            Q2[i] = (1 / (h * h)) * calQ( i, 2 );
            Q3[i] = (1 / (h * h)) * calQ( i, 3 );
            Q4[i] = (1 / (h * h)) * calQ( i, 4 );
            Q5[i] = (1 / h) * calQ( i, 5 );
            Q6[i] = (1 / h) * calQ( i, 6 );
        }

        Q2[N] = (1 / (h * h)) * calQ( N, 2 );
        Q3[N] = (1 / (h * h)) * calQ( N, 3 );
        Q4[N] = (1 / (h * h)) * calQ( N, 4 );
        Q4[N + 1] = (1 / (h * h)) * calQ( N+1 , 4 );
        Q5[N] = (1 / h) * calQ( N, 5 );
        Q6[N] = (1 / h) * calQ( N, 6 );

        for (int i = 1; i <= N - 1; i++) {
            A[i] = Q2[i] + Q3[i] + Q4[i] + Q4[i + 1];
            B[i] = Q1[i] - Q4[i + 1];
            b[i] = Q5[i] + Q6[i];
        }
        A[N] = Q2[N] + Q3[N] + Q4[N] + Q4[N + 1];
        b[N] = Q5[N] + Q6[N];

//        matrix
        a[1] = A[1];
        y[1] = B[1] / A[1];
        z[1] = b[1] / a[1];
        for (int i = 2; i <= N - 1; i++) {
            a[i] = A[i] - B[i - 1] * y[i - 1];
            y[i] = B[i] / a[i];
            z[i] = (b[i] - B[i - 1] * z[i - 1]) / a[i];
        }
        a[N] = A[N] - B[N - 1] * y[N - 1];
        z[N] = (b[N] - B[N - 1] * z[N - 1]) / a[N];
//        Answer
        c[N] = z[N];
        for (int i = N - 1; i >= 1; i--) {
            c[i] = z[i] - y[i] * c[i + 1];
        }
        System.out.println( h );
        for (int i = 1; i <= N; i++) {
            System.out.println( "c" + i + "= " + c[i] );
        }
    }

    public static double p(double x) {
        double ans = 1;
        return ans;
    }

    public static double q(double x) {
        double ans = Math.PI * Math.PI;
        return ans;
    }

    public static double f(double x) {
        double ans = Math.PI * Math.PI * 2 * Math.sin( Math.PI * x );
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

    public static double calQ(int i, int n) {
        double ans = 0;
        if (n==1 || n== 3 || n== 6) {
            for (double dx = x[i]; dx <= x[i + 1] - l; dx = dx + l) {
                ans = ans + (funcQ( i, n, dx ) + funcQ( i, n, dx + l )) * l / 2;
            }
        }
        if (n==2 || n== 4 || n== 5) {
            for (double dx = x[i-1]; dx <= x[i] - l; dx = dx + l) {
                ans = ans + (funcQ( i, n, dx ) + funcQ( i, n, dx + l )) * l / 2;
            }
        }
        return ans;
    }

    public static double funcQ(int i, int n, double dx) {
        double ans;
        if (n == 1) {
            ans = (x[i + 1] - dx) * (dx - x[i]) * q( dx );
            return ans;
        }
        if (n == 2) {
            ans = (dx - x[i - 1]) * (dx - x[i - 1]) * q( dx );
            return ans;
        }
        if (n == 3) {
            ans = (x[i + 1] - dx) * (x[i + 1] - dx) * q( dx );
            return ans;
        }
        if (n == 4) {
            ans = p( dx );
            return ans;
        }
        if (n == 5) {
            ans = (dx - x[i - 1]) * f( dx );
            return ans;
        }
        if (n == 6) {
            ans = (x[i + 1] - dx) * f( dx );
            return ans;
        }
        return 0;
    }
}
