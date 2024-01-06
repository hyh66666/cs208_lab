
import java.io.*;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) {
        fastReader in= new fastReader();
        long n= in.nextLong();
        int k = 0;
        for (int i = 0; i < 61; i++) {
            if(Math.pow(2,i)>n){
                k=i;
                break;
            }
        }
        long r=n;
        while (k>0){
            if(r<Math.pow(2,k-1)){
                out.print(0);
            }
            else {
                out.print(1);
                r= (long) Math.pow(2,k)-1-r;
            }
            k--;
        }


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
    }
}
