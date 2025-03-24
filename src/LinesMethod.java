public class LinesMethod {
    static double k=0.005;
    static double h=0.25;
    static double alpha=1;

    public static void main(String[] args) {
        int N = 10;

        double t=0;
        double w1 = Math.sqrt(2)/2;
        double w2 = 1.0;
        double w3 = Math.sqrt(2)/2;
        double k11=k*f1(t,w1,w2,w3);
        double k12=k*f2(t,w1,w2,w3);
        double k13=k*f3(t,w1,w2,w3);
        System.out.println("k11="+k11);
        System.out.println("k12="+k12);
        System.out.println("k13="+k13);
        double k21=k*f1(t+k/2,w1+k11/2,w2+k12/2,w3+k13/2);
        double k22=k*f2(t+k/2,w1+k11/2,w2+k12/2,w3+k13/2);
        double k23=k*f3(t+k/2,w1+k11/2,w2+k12/2,w3+k13/2);
        System.out.println("k21="+k21);
        System.out.println("k22="+k22);
        System.out.println("k23="+k23);
        double k31=k*f1(t+k/2,w1+k21/2,w2+k22/2,w3+k23/2);
        double k32=k*f2(t+k/2,w1+k21/2,w2+k22/2,w3+k23/2);
        double k33=k*f3(t+k/2,w1+k21/2,w2+k22/2,w3+k23/2);
        System.out.println("k31="+k31);
        System.out.println("k32="+k32);
        System.out.println("k33="+k33);
        double k41=k*f1(t+k,w1+k31,w2+k32,w3+k33);
        double k42=k*f2(t+k,w1+k31,w2+k32,w3+k33);
        double k43=k*f3(t+k,w1+k31,w2+k32,w3+k33);
        System.out.println("k41="+k41);
        System.out.println("k42="+k42);
        System.out.println("k43="+k43);
        w1=w1+(k11+2*k21+2*k31+k41)/6;
        w2=w2+(k12+2*k22+2*k32+k42)/6;
        w3=w3+(k13+2*k23+2*k33+k43)/6;
        System.out.println("w1="+w1);
        System.out.println("w2="+w2);
        System.out.println("w3="+w3);
    }

    public static double f1(double t,double w1,double w2,double w3) {
        double ans = alpha*alpha/(h*h)*(w2-2*w1);
        return ans;
    }

    public static double f2(double t,double w1,double w2,double w3) {
        double ans = alpha*alpha/(h*h)*(w3-2*w2+w1);
        return ans;
    }

    public static double f3(double t,double w1,double w2,double w3) {
        double ans = alpha*alpha/(h*h)*(-2*w3+w2);
        return ans;
    }

    public static double rond(double x, int a) {
        double ans = (double) (Math.round( x * ((double) Math.pow( 10, a )) )) / ((double) Math.pow( 10, a ));
        return ans;
    }

}
