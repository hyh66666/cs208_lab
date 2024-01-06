import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n=in.nextInt();
        Node[] nodes=new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i]=new Node();
        }
        for (int i = 0; i < n - 1; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            nodes[v-1].edge.add(nodes[u-1]);
            nodes[u-1].edge.add(nodes[v-1]);
        }
        long result=1;
        for (int i = 0; i < n; i++) {

            if(i==0) {
                result = result * count(nodes[i].edge.size())%998244353;

            }
            else {
                result=result*count(nodes[i].edge.size()-1)%998244353;

            }
        }
        out.println(result);
        out.close();

    }
    public static long count(int n){
        long result=1;
        for (int i = n; i >0; i--) {
            result=result*i%998244353;
        }
        return result;
    }
    static class Node{
        ArrayList<Node> edge=new ArrayList<>();
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
