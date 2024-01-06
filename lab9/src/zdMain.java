import java.io.*;
import java.util.StringTokenizer;

public class zdMain {
    public static void main(String[] args) {
        QReader sc=new QReader();
        QWriter out=new QWriter();
        int n=sc.nextInt();
        int m=sc.nextInt();
        String num=Integer.toBinaryString(n);// binary number
        int bits=num.length();
        int [] bit=new int[bits];
        int mod=1000000007;
        for(int i=0;i<bits;i++){
            bit[i]= (int) num.charAt(i) -48;
        }
        long[][] ma=new long[m][m];
        long[][] f=new long[1][m];
        f[0][0]=1;
        long [][] now=new long[1][m];
        for(int i=0;i<m;i++){
            ma[i][0]=1;
        }
        for(int i=1;i<m;i++){
            ma[i-1][i]=1;
        }
        long[][] cur=new long[m][m];
        for(int i=bits-1;i>=0;i--){
            if(bit[i]==0) {

                for (int x = 0; x < m; x++) {
                    for (int y = 0; y < m; y++) {
                        for (int z = 0; z < m; z++) {
                            cur[x][y] = (cur[x][y] + (ma[x][z] * ma[z][y]) % mod) % mod;
                        }
                    }
                }
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < m; k++) {
                        ma[j][k] = cur[j][k];
                        cur[j][k]=0;
                    }
                }

//                else {
//                    for (int j = 0; j < m; j++) {
//                        for (int k = 0; k < m; k++) {
//                            cur[j][k] = ma[j][k];
//                        }
//                    }
//                }
            }
            else{
                for (int x = 0; x < 1; x++) {
                    for (int y = 0; y < m; y++) {
                        for (int z = 0; z < m; z++) {
                            now[x][y] = (now[x][y] + (f[x][z] * ma[z][y]) % mod) % mod;
                        }
                    }
                }
                for (int j = 0; j < m; j++) {
                    f[0][j] = now[0][j];
                    now[0][j]=0;
                }

                        for (int x = 0; x < m; x++) {
                            for (int y = 0; y < m; y++) {
                                for (int z = 0; z < m; z++) {
                                    cur[x][y] = (cur[x][y]+(ma[x][z] * ma[z][y]) % mod) % mod;
                                }
                            }
                        }
                        for (int j = 0; j < m; j++) {
                            for (int k = 0; k < m; k++) {
                                ma[j][k] = cur[j][k];
                                cur[j][k]=0;
                            }
                        }



            }
        }
        long re=0;
        for(int i=0;i<m;i++){
            re=(re+f[0][i])%mod;
        }
        out.println(re);
        out.close();
    }
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }}
