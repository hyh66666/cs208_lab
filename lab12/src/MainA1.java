import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainA1 {
    private static int[]depth;
    public static void main(String[] args) {
        fastReader in= new fastReader();
        int n=in.nextInt();
        int m=in.nextInt();
        int s=in.nextInt();
        int t=in.nextInt();
        long[][] matrix=new long[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            long c=in.nextInt();
            matrix[u][v]+=c;
        }
        long result=0;
        while (bfs(matrix,s,t)){
            long flow=dfs(s,t,Long.MAX_VALUE,matrix);
            while (flow>0){
                result+=flow;
                flow=dfs(s,t,Long.MAX_VALUE,matrix);
            }
        }
        out.println(result);
        out.close();
    }
    public static boolean bfs(long[][] matrix,int s,int t){
        int size=matrix.length;
        depth=new int[size];
        int[] isVisited=new int[size];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        isVisited[s]=1;
        while (!queue.isEmpty()){
            int cur=queue.poll();
            if(cur==t){
                return true;
            }
            for (int i = 1; i < size; i++) {
                if(matrix[cur][i]>0&&isVisited[i]==0){
                    queue.add(i);
                    isVisited[i]=1;
                    depth[i]=depth[cur]+1;
                }
            }
        }
        return false;
    }
    public static long dfs(int s,int t,long flow,long[][]matrix){
        if(s==t){
            return flow;
        }
        int size=matrix.length;
        for (int i = 1; i < size; i++) {
            if(depth[i]==depth[s]+1&&matrix[s][i]>0){
                if(flow>matrix[s][i]){
                    flow=matrix[s][i];
                }
                long c=dfs(i,t,flow,matrix);
                if(c>0){
                    matrix[s][i]-=c;
                    matrix[i][s]+=c;
                    return c;
                }

            }
        }
        return -1;
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
