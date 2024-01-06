import java.util.Random;
import java.util.Scanner;

public class sbMain {
    static int mod = 1000000007;

    public static void main(String[] args) {
        Random r=new Random();
        int n = r.nextInt(999999999)+1, m = r.nextInt(49)+1;
        System.out.println(n+" "+m);
//        int n=1000000000;
//        int m=50;
        long[][] f = new long[1][m];
        f[0][0] = 1;
        long[][] matrix = new long[m][m];
        long answer = 0;
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 0; i < m - 1; i++) {
            matrix[i][i + 1] = 1;
        }
        long[][] matrix_pow = mat_pow(matrix,n);
        long[][] ans = mat_mul(f,matrix_pow);
        for (int i = 0; i < ans[0].length; i++) {
            answer += ans[0][i];
            answer %= mod;
        }
        System.out.println(answer);
    }

    public static long[][] mat_pow(long[][] mat, int pow){
        long[][] res = new long[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            res[i][i] = 1;
        }
        while (pow != 0){
            if ((pow&1) == 1){
                res = mat_mul(res,mat);
            }
            mat = mat_mul(mat,mat);
            pow /= 2;
        }
        return res;
    }

    public static long[][] mat_mul(long[][] a, long[][] b){
        int an = a.length,am = a[0].length,bm = b[0].length;
        long[][] res = new long[an][bm];
        for (int i = 0; i < an; i++) {
            for (int j = 0; j < bm; j++) {
                for (int k = 0; k < am; k++) {
                    res[i][j] = (res[i][j] + a[i][k]*b[k][j]%mod)%mod;
                }
            }
        }
        return res;
    }
}
