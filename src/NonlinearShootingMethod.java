public class NonlinearShootingMethod {
    public static void main(String[] args) {

        int N = 20;
        double a = 1.0;
        double b = 3.0;
        double A = 17.0;
        double B = 43.0 / 3.0;
        double Tol = 0.00001;
        double h = (b - a) / N;
        double TK = (B - A) / (b - a);
        double v[] = new double[N + 1];
        double w[] = new double[N + 1];
        double u1;
        double u2;
        int k = 1;
        int ansAcc = 5;
        int calAcc = 3;
        while (k <= 10) {
            v[0] = A;
            w[0] = TK;
            u1 = 0;
            u2 = 1;
            for (int i = 1; i <= N; i++) {
                double x = a + (i - 1) * h;
                double k1 = h * w[i - 1];
                double l1 = h * f( x, v[i - 1], w[i - 1] );
                double k2 = h * (w[i - 1] + l1 / 2);
                double l2 = h * f( x + h / 2, v[i - 1] + k1 / 2, w[i - 1] + l1 / 2 );
                double k3 = h * (w[i - 1] + l2 / 2);
                double l3 = h * f( x + h / 2, v[i - 1] + k2 / 2, w[i - 1] + l2 / 2 );
                double k4 = h * (w[i - 1] + l3);
                double l4 = h * f( x + h, v[i - 1] + k3, w[i - 1] + l3 );
                v[i] = v[i - 1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
                w[i] = w[i - 1] + (l1 + 2 * l2 + 2 * l3 + l4) / 6;
                System.out.println( "K1=" + h + "(" + rond( w[i - 1], calAcc ) + ")=" + rond( k1, ansAcc ) + "                  L1=" + h + "f(" + rond( x, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")=" + rond( l1, ansAcc ) );
                System.out.println( "K2=" + h + "(" + rond( w[i - 1] + l1 / 2, calAcc ) + ")=" + rond( k2, ansAcc ) + "                  L2=" + h + "f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1] + k1 / 2, calAcc ) + "," + rond( w[i - 1] + l1 / 2, calAcc ) + ")=" + rond( l2, ansAcc ) );
                System.out.println( "K3=" + h + "(" + rond( w[i - 1] + l2 / 2, calAcc ) + ")=" + rond( k3, ansAcc ) + "                  L3=" + h + "f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1] + k2 / 2, calAcc ) + "," + rond( w[i - 1] + l2 / 2, calAcc ) + ")=" + rond( l3, ansAcc ) );
                System.out.println( "K4=" + h + "(" + rond( w[i - 1] + l3, calAcc ) + ")=" + rond( k4, ansAcc ) + "                  L4=" + h + "f(" + rond( x + h, calAcc ) + "," + rond( v[i - 1] + k3, calAcc ) + "," + rond( w[i - 1] + l3, calAcc ) + ")=" + rond( l4, ansAcc ) );
                System.out.println();
                System.out.println( "v" + i + "=" + v[i] + "     w" + i + "=" + w[i] );
                System.out.println();

                double kk1 = h * u2;
                double ll1 = h * (fy( x, v[i - 1], w[i - 1] ) * u1 + fyy( x, v[i - 1], w[i - 1] ) * u2);
                double kk2 = h * (u2 + ll1 / 2);
                double ll2 = h * (fy( x + h / 2, v[i - 1], w[i - 1] ) * (u1 + kk1 / 2) + fyy( x + h / 2, v[i - 1], w[i - 1] ) * (u2 + ll1 / 2));
                double kk3 = h * (u2 + ll2 / 2);
                double ll3 = h * (fy( x + h / 2, v[i - 1], w[i - 1] ) * (u1 + kk2 / 2) + fyy( x + h / 2, v[i - 1], w[i - 1] ) * (u2 + ll2 / 2));
                double kk4 = h * (u2 + ll3);
                double ll4 = h * (fy( x + h, v[i - 1], w[i - 1] ) * (u1 + kk3) + fyy( x + h, v[i - 1], w[i - 1] ) * (u2 + ll3));
                System.out.println( "K'1=" + h + "(" + rond( u2, calAcc ) + ")=" + rond( kk1, ansAcc ) + "                  L'1=" + h + "[f(" + rond( x, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u1, calAcc ) + ")  +  f(" + rond( x, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u2, calAcc ) + ")" + "]=" + rond( ll1, ansAcc ) );
                System.out.println( "K'2=" + h + "(" + rond( u2 + ll1 / 2, calAcc ) + ")=" + rond( kk2, ansAcc ) + "                  L'2=" + h + "[f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u1 + kk1 / 2, calAcc ) + ")  +  f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u2 + ll1 / 2, calAcc ) + ")" + "]=" + rond( ll2, ansAcc ) );
                System.out.println( "K'3=" + h + "(" + rond( u2 + ll2 / 2, calAcc ) + ")=" + rond( kk3, ansAcc ) + "                  L'3=" + h + "[f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u1 + kk2 / 2, calAcc ) + ")  +  f(" + rond( x + h / 2, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u2 + ll2 / 2, calAcc ) + ")" + "]=" + rond( ll3, ansAcc ) );
                System.out.println( "K'4=" + h + "(" + rond( u2 + ll3, calAcc ) + ")=" + rond( kk4, ansAcc ) + "                  L'4=" + h + "[f(" + rond( x + h, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u1 + kk3, calAcc ) + ")  +  f(" + rond( x + h, calAcc ) + "," + rond( v[i - 1], calAcc ) + "," + rond( w[i - 1], calAcc ) + ")(" + rond( u2 + ll3, calAcc ) + ")" + "]=" + rond( ll4, ansAcc ) );
                System.out.println();
                u1 = u1 + (kk1 + 2 * kk2 + 2 * kk3 + kk4) / 6;
                u2 = u2 + (ll1 + 2 * ll2 + 2 * ll3 + ll4) / 6;
                System.out.println( "u1=" + u1 + "     u2=" + u2 );
                System.out.println( "______________________________________________________________________________________________" );
            }
            TK = TK - (v[N] - B) / u1;
            if (Math.abs( v[N] - B ) < Tol) {
                System.out.println( "braak at STEP : " + k );
                break;
            }
            System.out.println( "************************************************************************************************\n" +
                    "************************************************************************************************\n" +
                    "************************************************************************************************\n" +
                    "************************************************************************************************\n" );
            System.out.println( "STEP :" + k + "" );
            System.out.println( "______________________________________________________________________________________________" );
            k++;
        }
        for (int i = 0; i <= N; i++) {
            System.out.println( a + rond( i * h, ansAcc ) + "    v" + i + " = " + v[i] );
        }

    }

    public static double f(double x, double v, double w) {
        double ans = (32 + 2 * x * x * x - v * w) / 8;
        return ans;
    }

    public static double fy(double x, double v, double w) {
        double ans = -w / 8;
        return ans;
    }

    public static double fyy(double x, double v, double w) {
        double ans = -v / 8;
        return ans;
    }

    public static double rond(double x, int acc) {
        double ans = (double) (Math.round( x * ((double) Math.pow( 10, acc )) )) / ((double) Math.pow( 10, acc ));
        return ans;
    }

}
