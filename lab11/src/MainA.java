import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainA {
    private static long[][] opt;
    public static void main(String[] args) {
        fastReader in =new fastReader();
        int n = in.nextInt();
        Node[] nodes =new Node[n+1];
        opt = new long[n+1][2];
        for (int i = 0; i < n+1; i++) {
            nodes[i]=new Node(i);
        }
        for (int i = 0; i < n-1; i++) {
            int u = in.nextInt();
            int v=in.nextInt();
            int weight = in.nextInt();
            nodes[u].child.add(nodes[v]);
            nodes[u].weight.add(weight);
            nodes[v].child.add(nodes[u]);
            nodes[v].weight.add(weight);
        }
        nodes[1].isRoot=true;
        dfs(nodes[1]);
        out.println(opt[1][1]);
        out.close();
    }
    public static void dfs(Node node){
        node.isVisited=true;
        if(node.child.size()==1&& !node.isRoot){
            opt[node.index][1]=0;
            return;
        }
        for (int i = 0; i < node.child.size(); i++) {
            Node child=node.child.get(i);
            if(!child.isVisited) {
                dfs(child);
                opt[node.index][0]+=opt[child.index][1];
                child.a=-opt[child.index][1]+opt[child.index][0];
                child.isVisited=false;
            }
        }
        long max=opt[node.index][0];
        for (int i = 0; i < node.weight.size(); i++) {
            if(!node.child.get(i).isVisited){
                int w= node.weight.get(i);
                long c=w+opt[node.index][0]+node.child.get(i).a;
                if(max<c){
                    max=c;
                }
            }
        }
        opt[node.index][1]=max;

    }
    static class Node{
        int index;
        ArrayList<Node> child=new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        boolean isVisited=false;
        boolean isRoot=false;
        long a;
        public Node(int index){
            this.index=index;
        }
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
