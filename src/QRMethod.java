import java.util.ArrayList;

public class QRMethod {
    static int digit=10;
    public static void main(String[] args) {
        int iteration=1;
        double[][] A =
                {
                        {20, 8, 0},
                        {8, 10, -8},
                        {0, -8, 21},

                };

        double[][] padding = new double[1][1];
        for (int iter = 1; iter <= iteration; iter++) {
            System.out.println("step "+iter+":");
            ArrayList<double[][]> Ak = new ArrayList<>();
            ArrayList<double[][]> Pk = new ArrayList<>();
            Pk.add(padding);
            Ak.add(A);
            double[][] R ;
            double[][] Q ;
            for (int k = 0; k < A.length - 1; k++) {
                double sin = Ak.get(k)[k + 1][k] / Math.sqrt(Math.pow(Ak.get(k)[k + 1][k], 2) + Math.pow(Ak.get(k)[k][k], 2));
                System.out.println("sin θ_"+(k+2)+"=b_"+(k+2)+"/√(b_"+(k+2)+"^2+x_"+(k+1)+"^2 )="+round(Ak.get(k)[k + 1][k],digit)+"/√(("+round(Ak.get(k)[k + 1][k],digit)+")^2+("+round(Ak.get(k)[k][k],digit)+")^2 ) ="+round(sin,digit));
                double cos = Ak.get(k)[k][k] / Math.sqrt(Math.pow(Ak.get(k)[k + 1][k], 2) + Math.pow(Ak.get(k)[k][k], 2));
                System.out.println("cos θ_"+(k+2)+"=x_"+(k+1)+"/√(b_"+(k+2)+"^2+x_"+(k+1)+"^2 )="+round(Ak.get(k)[k][k],digit)+"/√(("+round(Ak.get(k)[k + 1][k],digit)+")^2+("+round(Ak.get(k)[k][k],digit)+")^2 ) ="+round(cos,digit));
                double[][] P = new double[A.length][A.length];

                for (int i = 0; i < P.length; i++) {
                    for (int j = 0; j < P.length; j++) {
                        if (i == j) {
                            P[i][j] = 1;
                        } else {
                            P[i][j] = 0;
                        }
                    }
                }
                P[k][k] = cos;
                P[k + 1][k] = -sin;
                P[k][k + 1] = sin;
                P[k + 1][k + 1] = cos;
                Pk.add(P);
                System.out.println("P_"+(k+2)+"= "+printMatrix(P));
                Ak.add(mul(Pk.get(k + 1), Ak.get(k)));
                System.out.println("A^(("+(iter)+"))_"+(k+2)+"="+"P_"+(k+2)+" A^(("+iter+"))_"+(k+1)+"="+printMatrix(Pk.get(k + 1))+printMatrix(Ak.get(k))+"="+printMatrix(Ak.get(k+1)));
            }
            R = Ak.get(A.length - 1);
            System.out.println("R^(("+(iter)+"))="+printMatrix(R));
            Q = t(Pk.get(1));
            String QForm="P^t_2";
            String QCal=printMatrix(t(Pk.get(1)));
            for (int i = 2; i < A.length; i++) {
                Q = mul(Q, t(Pk.get(i)));
                QForm=QForm+" P^t_"+(i+1);
                QCal=QCal+printMatrix(t(Pk.get(i)));
            }
            System.out.println("Q^(("+(iter)+"))="+QForm+"="+QCal+"="+printMatrix(Q));
            Ak.clear();
            Pk.clear();
            A = mul(R, Q);
            System.out.println("A^(("+(iter+1)+"))="+"R^(("+(iter)+")) Q^(("+(iter)+"))="+printMatrix(R)+printMatrix(Q)+"="+printMatrix(A));
        }
    }


    public static double[][] t(double[][] AA) {
        double[][] AAt = new double[AA[0].length][AA.length];
        for (int i = 0; i < AA.length; i++) {
            for (int j = 0; j < AA[0].length; j++) {
                AAt[j][i] = AA[i][j];
            }
        }
        return AAt;
    }

    public static String printMatrix(double[][] AA) {
        String printedAA = "[■(";
        for (int i = 0; i < AA.length; i++) {
            for (int j = 0; j < AA[0].length; j++) {
                printedAA = printedAA + round(AA[i][j],digit);
                if (j < AA[0].length - 1) {
                    printedAA = printedAA + "&";
                }
            }
            if (i < AA.length - 1) {
                printedAA = printedAA + "@";
            }
        }
//        [■(1&2&3@4&5&6@7&8&9)]
        printedAA = printedAA + ")]";
        return printedAA;
    }

    public static double[][] mul(double[][] AA1, double[][] AA2) {
        double sum;
        double[][] AA = new double[AA1.length][AA2[0].length];
        for (int i = 0; i < AA.length; i++) {
            for (int j = 0; j < AA[0].length; j++) {
                sum = 0;
                for (int k = 0; k < AA.length; k++) {
                    sum = sum + AA1[i][k] * AA2[k][j];
                }
                AA[i][j] = sum;
            }
        }
        return AA;
    }
    public static double round(double x, int a) {
        double ans = (double) (Math.round( x * ((double) Math.pow( 10, a )) )) / ((double) Math.pow( 10, a ));
        return ans;
    }
}
