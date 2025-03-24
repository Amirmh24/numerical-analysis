public class LinearShootingMethod {
    public static void main(String[] args) {

        int N = 10;
        double a = 1.0;
        double b = 2.0;
        double A = 1.0;
        double B = 2.0;
        double h = (b - a) / N;
        double v[] = new double[N + 1];
        double w[] = new double[N + 1];
        double vv[] = new double[N + 1];
        double ww[] = new double[N + 1];
        double vans[] = new double[N + 1];
        double wans[] = new double[N + 1];
        int ansAcc = 5;
        int calAcc = 3;
        v[0] = A;
        w[0] = 0;
        vv[0] = 0;
        ww[0] = 1;

        for (int i = 0; i <= N - 1; i++) {
            double x = a + i * h;
            double k1 = h * w[i];
            double l1 = h * (p( x ) * w[i] + q( x ) * v[i] + r( x ));
            double k2 = h * (w[i] + l1 / 2);
            double l2 = h * (p( x + h / 2 ) * (w[i] + l1 / 2) + q( x + h / 2 ) * (v[i] + k1 / 2) + r( x + h / 2 ));
            double k3 = h * (w[i] + l2 / 2);
            double l3 = h * (p( x + h / 2 ) * (w[i] + l2 / 2) + q( x + h / 2 ) * (v[i] + k2 / 2) + r( x + h / 2 ));
            double k4 = h * (w[i] + l3);
            double l4 = h * (p( x + h ) * (w[i] + l3) + q( x + h ) * (v[i] + k3) + r( x + h ));
            v[i + 1] = v[i] + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            w[i + 1] = w[i] + (l1 + 2 * l2 + 2 * l3 + l4) / 6;
            System.out.println( "K1=" + h + "(" + rond( w[i], calAcc ) + ")=" + rond( k1, ansAcc ) + "                  L1=" + h + "(" + rond( p( x ), calAcc ) + "(" + rond( w[i], calAcc ) + ")+" + rond( q( x ), calAcc ) + "(" + rond( v[i], calAcc ) + ")+" + rond( r( x ), calAcc ) + ")=" + rond( l1, ansAcc ) );
            System.out.println( "K2=" + h + "(" + rond( w[i] + l1 / 2, calAcc ) + ")=" + rond( k2, ansAcc ) + "                  L2=" + h + "(" + rond( p( x + h / 2 ), calAcc ) + "(" + rond( w[i] + l1 / 2, calAcc ) + ")+" + rond( q( x + h / 2 ), calAcc ) + "(" + rond( v[i] + k1 / 2, calAcc ) + ")+" + rond( r( x + h / 2 ), calAcc ) + ")=" + rond( l2, ansAcc ) );
            System.out.println( "K3=" + h + "(" + rond( w[i] + l2 / 2, calAcc ) + ")=" + rond( k3, ansAcc ) + "                  L3=" + h + "(" + rond( p( x + h / 2 ), calAcc ) + "(" + rond( w[i] + l2 / 2, calAcc ) + ")+" + rond( q( x + h / 2 ), calAcc ) + "(" + rond( v[i] + k2 / 2, calAcc ) + ")+" + rond( r( x + h / 2 ), calAcc ) + ")=" + rond( l3, ansAcc ) );
            System.out.println( "K4=" + h + "(" + rond( w[i] + l3, calAcc ) + ")=" + rond( k4, ansAcc ) + "                  L4=" + h + "(" + rond( p( x + h ), calAcc ) + "(" + rond( w[i] + l3, calAcc ) + ")+" + rond( q( x + h ), calAcc ) + "(" + rond( v[i] + k3, calAcc ) + ")+" + rond( r( x + h ), calAcc ) + ")=" + rond( l4, ansAcc ) );
            System.out.println();
            System.out.println( "v" + (i + 1) + "=" + v[i + 1] + "     w" + (i + 1) + "=" + w[i + 1] );
            System.out.println();

            double kk1 = h * ww[i];
            double ll1 = h * (p( x ) * ww[i] + q( x ) * vv[i]);
            double kk2 = h * (ww[i] + ll1 / 2);
            double ll2 = h * (p( x + h / 2 ) * (ww[i] + ll1 / 2) + q( x + h / 2 ) * (vv[i] + kk1 / 2));
            double kk3 = h * (ww[i] + ll2 / 2);
            double ll3 = h * (p( x + h / 2 ) * (ww[i] + ll2 / 2) + q( x + h / 2 ) * (vv[i] + kk2 / 2));
            double kk4 = h * (ww[i] + ll3);
            double ll4 = h * (p( x + h ) * (ww[i] + ll3) + q( x + h ) * (vv[i] + kk3));
            vv[i + 1] = vv[i] + (kk1 + 2 * kk2 + 2 * kk3 + kk4) / 6;
            ww[i + 1] = ww[i] + (ll1 + 2 * ll2 + 2 * ll3 + ll4) / 6;

            System.out.println( "K'1=" + h + "(" + rond( ww[i], calAcc ) + ")=" + rond( kk1, ansAcc ) + "                  L'1=" + h + "(" + rond( p( x ), calAcc ) + "(" + rond( ww[i], calAcc ) + ")+" + rond( q( x ), calAcc ) + "(" + rond( vv[i], calAcc ) + ")=" + rond( l1, ansAcc ) );
            System.out.println( "K'2=" + h + "(" + rond( ww[i] + ll1 / 2, calAcc ) + ")=" + rond( kk2, ansAcc ) + "                  L'2=" + h + "(" + rond( p( x + h / 2 ), calAcc ) + "(" + rond( ww[i] + ll1 / 2, calAcc ) + ")+" + rond( q( x + h / 2 ), calAcc ) + "(" + rond( vv[i] + kk1 / 2, calAcc ) + ")=" + rond( l2, ansAcc ) );
            System.out.println( "K'3=" + h + "(" + rond( ww[i] + ll2 / 2, calAcc ) + ")=" + rond( kk3, ansAcc ) + "                  L'3=" + h + "(" + rond( p( x + h / 2 ), calAcc ) + "(" + rond( ww[i] + ll2 / 2, calAcc ) + ")+" + rond( q( x + h / 2 ), calAcc ) + "(" + rond( vv[i] + kk2 / 2, calAcc ) + ")=" + rond( l3, ansAcc ) );
            System.out.println( "K'4=" + h + "(" + rond( ww[i] + ll3, calAcc ) + ")=" + rond( kk4, ansAcc ) + "                  L'4=" + h + "(" + rond( p( x + h ), calAcc ) + "(" + rond( ww[i] + ll3, calAcc ) + ")+" + rond( q( x + h ), calAcc ) + "(" + rond( vv[i] + kk3, calAcc ) + ")=" + rond( l4, ansAcc ) );
            System.out.println();
            System.out.println( "v'" + (i + 1) + "=" + vv[i + 1] + "     w'" + (i + 1) + "=" + ww[i + 1] );
            System.out.println();

            System.out.println( "______________________________________________________________________________________________" );
        }
        vans[0]=A;
        wans[0]=(B-v[N])/vv[N];
        for (int i = 0; i <= N; i++) {
            vans[i]=v[i]+wans[0]*vv[i];
            wans[i]=w[i]+wans[0]*ww[i];
            System.out.println( "x" + i + " = " +(a + rond( i * h, 5 )) + "    v" + i + " = " + vans[i] );
        }
    }

    public static double p(double x) {
        double ans = -2 / x;
        return ans;
    }

    public static double q(double x) {
        double ans = 2 / x / x;
        return ans;
    }

    public static double r(double x) {
        double ans = (Math.sin( Math.log( x ) )) / x / x;
        return ans;
    }

    public static double rond(double x, int a) {
        double ans = (double) (Math.round( x * ((double) Math.pow( 10, a )) )) / ((double) Math.pow( 10, a ));
        return ans;
    }

}
