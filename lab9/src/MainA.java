import java.io.*;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) {
        fastReader in= new fastReader();
        int n=in.nextInt(); //x
        int m=in.nextInt(); //y
        int depth=(Math.min(n,m)+1)/2;
        long[][] x=new long[depth+1][n+1];
        long[][] y=new long[depth+1][m+1];
        long[][] xSum1=new long[depth+1][n-1];
        long[][] xSum2=new long[depth+1][n-1];
        long[][] ySum1=new long[depth+1][m-1];
        long[][] ySum2=new long[depth+1][m-1];
        long mod= (long) Math.pow(10,9)+7;
        for (int i = 1; i <= n; i++) {
            x[1][i]=1;
            if(i<=n-2){
                xSum1[1][i]=xSum1[1][i-1]+x[1][i];
                xSum2[1][i]=xSum2[1][i-1]+x[1][i]*i;
            }
        }
        for (int i = 1; i <= m; i++) {
            y[1][i]=1;
            if(i<=m-2){
                ySum1[1][i]=ySum1[1][i-1]+y[1][i];
                ySum2[1][i]=ySum2[1][i-1]+y[1][i]*i;
            }
        }
        for (int i = 2; i <= depth; i++) {
            for (int j = 1; j <n+1 ; j++) {
                if(j<=2){
                    x[i][j]=0;
                }
                else {
                    x[i][j]=((j-1)*xSum1[i-1][j-2])%mod-xSum2[i-1][j-2];
                }
                if(j<=n-2) {
                    xSum1[i][j] = (xSum1[i][j - 1] + x[i][j]) % mod;
                    xSum2[i][j] = (xSum2[i][j - 1] + (x[i][j] * j) % mod) % mod;
                }
            }
            for (int j = 1; j <m+1 ; j++) {
                if(j<=2) {
                    y[i][j] = 0;
                }
                else {
                    y[i][j]=((j-1)*ySum1[i-1][j-2])%mod-ySum2[i-1][j-2];
                }
                if(j<=m-2) {
                    ySum1[i][j] = (ySum1[i][j - 1] + y[i][j])%mod;
                    ySum2[i][j] = (ySum2[i][j - 1] + (y[i][j] * j)%mod)%mod;
                }
            }
        }
        long result=0;
        for (int i = 1; i < depth+1; i++) {
            result=(result+(x[i][n]*y[i][m])%mod)%mod;
        }
        if(result<=0){
            result+=mod;
        }
        out.println(result);
        out.close();
    }
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static class fastReader {
        BufferedReader br;
        StringTokenizer st;
        public fastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
    }
}
