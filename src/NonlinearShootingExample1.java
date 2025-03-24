public class NonlinearShootingExample1 {
    public static void main(String[] args) {

        int N = 2;
        double a=1.0;
        double b=2.0;
        double A=1;
        double B=0.5;
        double Tol=0.01;
        double h=(b-a)/N;
        double TK=(B-A)/(b-a);
        double v[]= new double[N+1];
        double w[]= new double[N+1];
        double u1;
        double u2;
        int k=1;
        TK=-2;
        while (k<=40){
            System.out.println("______________________________________________________________________________________________");
            System.out.println("STEP :"+k+"");
            System.out.println("______________________________________________________________________________________________");
            System.out.println("TK :"+TK);
            v[0]=A;
            w[0]=TK;
            u1=0;
            u2=1;
            for (int i = 1; i <=N ; i++) {
                double x=a+rond((i-1)*h,5);
                double k1=h*w[i-1];           double l1=h*f(x,v[i-1],w[i-1]);
                double k2=h*(w[i-1]+l1/2);    double l2=h*f(x+h/2,v[i-1]+k1/2,w[i-1]+l1/2);
                double k3=h*(w[i-1]+l2/2);    double l3=h*f(x+h/2,v[i-1]+k2/2,w[i-1]+l2/2);
                double k4=h*(w[i-1]+l3);      double l4=h*f(x+h,v[i-1]+k3,w[i-1]+l3);
                v[i]=v[i-1]+(k1+2*k2+2*k3+k4)/6;
                w[i]=w[i-1]+(l1+2*l2+2*l3+l4)/6;
                System.out.println("k_1="+h+"("+ rond( w[i-1] ,3)+")="+rond( k1,5 ));
                System.out.println("l_1="+h+"f("+rond( x ,3)+","+rond( v[i-1] ,3)+","+rond( w[i-1] ,3)+")="+rond( l1,5) );
                System.out.println("k_2="+h+"("+ rond( w[i-1]+l1/2,3 )+")="+rond( k2,5));
                System.out.println("l_2="+h+"f("+rond( x+h/2,3 )+","+rond( v[i-1]+k1/2 ,3)+","+rond( w[i-1]+l1/2 ,3)+")="+rond( l2,5 ));
                System.out.println("k_3="+h+"("+ rond( w[i-1]+l2/2 ,3)+")="+rond( k3,5));
                System.out.println("l_3="+h+"f("+rond( x+h/2 ,3)+","+rond( v[i-1]+k2/2 ,3)+","+rond( w[i-1]+l2/2,3 )+")="+rond( l3,5 ));
                System.out.println("k_4="+h+"("+ rond( w[i-1]+l3 ,3)+")="+rond( k4,5));
                System.out.println("l_4="+h+"f("+rond( x+h ,3)+","+rond( v[i-1]+k3 ,3)+","+rond( w[i-1]+l3 ,3)+")="+rond( l4,5 ));
                System.out.println(  );
                System.out.println("v"+i+"="+v[i]+"     w"+i+"="+w[i]);
                System.out.println(  );

                double kk1=h*u2;            double ll1=h*(fy(x,v[i-1],w[i-1])*u1+fyy(x,v[i-1],w[i-1])*u2);
                double kk2=h*(u2+ll1/2);    double ll2=h*(fy(x+h/2,v[i-1],w[i-1])*(u1+kk1/2)+fyy(x+h/2,v[i-1],w[i-1])*(u2+ll1/2));
                double kk3=h*(u2+ll2/2);    double ll3=h*(fy(x+h/2,v[i-1],w[i-1])*(u1+kk2/2)+fyy(x+h/2,v[i-1],w[i-1])*(u2+ll2/2));
                double kk4=h*(u2+ll3);      double ll4=h*(fy(x+h,v[i-1],w[i-1])*(u1+kk3)+fyy(x+h,v[i-1],w[i-1])*(u2+ll3));
                System.out.println("k_1^'="+h+"("+ rond( u2,3 )+")="+rond( kk1,5 ));
                System.out.println("l_1^'="+h+"[f_y("+rond( x ,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u1 ,3)+")+f_(y')("+rond( x,3 )+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u2,3 )+")"+"]="+rond( ll1,5) );
                System.out.println("k_2^'="+h+"("+ rond( u2+ll1/2,3 )+")="+rond( kk2,5 ));
                System.out.println("l_2^'="+h+"[f_y("+rond( x +h/2,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u1+kk1/2,3 )+")+f_(y')("+rond( x+h/2 ,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u2+ll1/2 ,3)+")"+"]="+rond( ll2,5) );
                System.out.println("k_3^'="+h+"("+ rond( u2+ll2/2 ,3)+")="+rond( kk3 ,5));
                System.out.println("l_3^'="+h+"[f_y("+rond( x +h/2,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u1+kk2/2 ,3)+")+f_(y')("+rond( x+h/2,3 )+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u2+ll2/2 ,3)+")"+"]="+rond( ll3,5) );
                System.out.println("k_4^'="+h+"("+ rond( u2+ll3 ,3)+")="+rond( kk4 ,5));
                System.out.println("l_4^'="+h+"[f_y("+rond( x +h,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u1+kk3 ,3)+")+f_(y')("+rond( x+h ,3)+","+rond( v[i-1],3 )+","+rond( w[i-1],3 )+")("+rond( u2+ll3 ,3)+")"+"]="+rond( ll4,5) );
                System.out.println(  );
                u1=u1+(kk1+2*kk2+2*kk3+kk4)/6;
                u2=u2+(ll1+2*ll2+2*ll3+ll4)/6;
                System.out.println("u1="+u1+"     u2="+u2);

            }
            TK=TK-(v[N]-B)/u1;
            if(Math.abs(  v[N]-B) < Tol){
                System.out.println( "braak at STEP : "+k );
                break;
            }

            k++;
        }
        System.out.println("______________________________________________________________________________________________");
        System.out.println("ANSWER");
        System.out.println("______________________________________________________________________________________________");
        for (int i = 0; i <= N; i++) {
            System.out.println("x"+i+"="+(a+rond( i*h,5 )) + "    v"+i+" = "+rond(  v[i],2) );
        }

    }
    public static double f(double x, double v, double w){
        double ans=(1/x)*w+8*x*v*v*v;
        return ans;
    }
    public static double fy(double x,double v ,double w){
        double ans=24*x*v*v;
        return ans;
    }
    public static double fyy(double x,double v ,double w){
        double ans=(1/x);
        return ans;
    }
    public static double rond(double x,int a){
        double ans=(double)(Math.round( x*((double)Math.pow( 10,a )) ))/((double)Math.pow( 10,a ));
        return ans;
    }
}
