import java.util.Random;
import java.util.Scanner;

public class main {
    static int mod = 1000000007;

    public static void main(String[] args) {
        Random r=new Random();
        int n = r.nextInt(19)+1, m = r.nextInt(19)+1;
        int max_nest = (Math.min(n, m) + 1) / 2;
        int max_num = Math.max(n,m);
        System.out.println(n+" "+m);
        long[][] f = new long[max_nest][max_num+1];
        for (int i = 1; i <= max_num; i++) {
            f[0][i] = 1;
        }
        for (int i = 1; i < max_nest; i++) {
            int k = 1;
            long pre1 = 0;
            long pre2 = 0;
            for (int j = 3; j <= max_num; j++,k++) {
                if (k <= j-2) {
                    pre1 += f[i-1][k];
                    pre1 %= mod;
                    pre2 += (f[i-1][k] * k) % mod;
                    pre2 %= mod;
                    f[i][j] = ((j-1) * pre1) % mod - pre2 % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < max_nest; i++) {
            ans += (f[i][n] * f[i][m]) % mod;
            ans %= mod;
        }
        ans = ans > 0 ? ans : ans + mod;
        System.out.println(ans);
    }
}
