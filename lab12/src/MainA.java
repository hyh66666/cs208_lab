import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainA {
    private static long result=0;
    public static void main(String[] args) {
        fastReader in= new fastReader();
        int n=in.nextInt();
        int m=in.nextInt();
        int s=in.nextInt();
        int t=in.nextInt();
        Node[] nodes=new Node[n+1];
        for (int i = 0; i < n + 1; i++) {
            nodes[i]=new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            int c=in.nextInt();
            nodes[u].child.add(nodes[v]);
            nodes[v].child.add(nodes[u]);
            nodes[u].capacity.add(c);
            nodes[v].capacity.add(0);
        }
        long result=0;
        while (bfs(nodes[s],nodes[t])){
            int d=dfs(nodes[s],nodes[t],Integer.MAX_VALUE);
            if(d==0){
                break;
            }
            else {
                for (int i = 0; i < n + 1; i++) {
                    nodes[i].isVisited=false;
                    nodes[i].isVisited1=false;
                }
            }
        }
        out.println(result);
        out.close();






    }
    public static boolean bfs(Node start,Node end){
        Queue<Node> queue=new LinkedList<>();
        int depth=0;
        queue.add(start);
        start.depth=depth;
        start.isVisited=true;
        queue.poll();
        for (int i = 0; i < start.child.size(); i++) {
            Node temp=start.child.get(i);
            int c=start.capacity.get(i);
            if(!temp.isVisited&&c>0){
                temp.isVisited=true;
                queue.add(temp);
            }
        }
        depth++;
        int pollTimes=0,childCount=start.child.size(),count=0;
        while (!queue.isEmpty()){
            Node cur= queue.poll();
            cur.depth=depth;
            pollTimes++;
            if(cur.index==end.index){
                return true;
            }
            for (int i = 0; i < cur.child.size(); i++) {
                Node temp=cur.child.get(i);
                int c=cur.capacity.get(i);
                if(!temp.isVisited&&c>0){
                    temp.isVisited=true;
                    queue.add(temp);
                    count++;
                }
            }
            if(pollTimes==childCount){
                pollTimes=0;
                depth++;
                childCount=count;
                count=0;
            }
        }
        return false;
    }

    public static int dfs(Node start,Node end,int flow){
        start.isVisited1=true;
        if(start.index== end.index){
            result+=flow;
            return flow;
        }
        for (int i = 0; i < start.child.size(); i++) {
            Node temp=start.child.get(i);
            if(!temp.isVisited1&& temp.depth==start.depth+1&&start.capacity.get(i)>0){
                int di=dfs(temp,end,Math.min(start.capacity.get(i),flow));
                temp.isVisited1=false;
                if(di>0){
                    int t=start.capacity.get(i)-di;
                    start.capacity.remove(i);
                    start.capacity.add(i,t);
                    int s=temp.capacity.get(i)+di;
                    temp.capacity.remove(i);
                    temp.capacity.add(i,s);
                    return di;
                }
            }
        }
        return 0;
    }
    static class Node{
        ArrayList<Node> child=new ArrayList<>();
        ArrayList<Integer> capacity=new ArrayList<>();
        boolean isVisited=false;
        boolean isVisited1=false;
        int depth=0;
        int index;
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
