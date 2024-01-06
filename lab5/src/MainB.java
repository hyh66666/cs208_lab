import java.io.*;
import java.util.*;

public class MainB {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n= in.nextInt();
        int m=in.nextInt();
        Node[] nodes=new Node[n+1];
        edge[] edges=new edge[m];
        for (int i = 0; i < n+1; i++) {
            nodes[i]=new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            edges[i]=new edge(in.nextInt(),i);
            nodes[u].child.add(nodes[v]);
            nodes[v].child.add(nodes[u]);
            nodes[u].edges.add(edges[i]);
            nodes[v].edges.add(edges[i]);
        }
        int p=in.nextInt();
        for (int i = 0; i < p; i++) {
            int s=in.nextInt();
            int t=in.nextInt();
            dijkstra(nodes[s],nodes[t]);
            bfs(nodes[t],nodes[s]);
            for (int j = 1; j < n+1; j++) {
                nodes[j].isVisit=false;
                nodes[j].isVisit1=false;
                nodes[j].dis=Long.MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            out.println(edges[i].count);
        }
        out.close();
    }
    public static void bfs(Node start,Node end){
        Queue<Node> queue=new LinkedList<>();
        queue.add(start);
        start.isVisit1=true;
        while (!queue.isEmpty()){
            Node cur= queue.poll();
            if(cur.index== end.index){
                continue;
            }
            for (int i = 0; i < cur.child.size(); i++) {
                Node temp=cur.child.get(i);
                if(temp.isVisit) {
                    if (temp.dis + cur.edges.get(i).value == cur.dis) {
                        cur.edges.get(i).count++;
                        if (!temp.isVisit1) {
                            queue.add(temp);
                            temp.isVisit1 = true;
                        }
                    }
                }
            }
        }
    }
    public static void dijkstra(Node start,Node end){
        Queue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> (int) (o1.dis - o2.dis));
        start.dis=0;
        start.isVisit=true;
        do {
            for (int i = 0; i < start.child.size(); i++) {
                if (start.dis + start.edges.get(i).value < start.child.get(i).dis) {
                    priorityQueue.remove(start.child.get(i));
                    start.child.get(i).dis = start.dis + start.edges.get(i).value;
                    priorityQueue.add(start.child.get(i));
                }
                if (!start.child.get(i).isVisit) {
                    priorityQueue.add(start.child.get(i));
                    start.child.get(i).isVisit = true;
                }
            }
            start = priorityQueue.poll();
            assert start != null;
        } while (start.index != end.index);
    }
    static class Node{
        ArrayList<edge> edges=new ArrayList<>();
        ArrayList<Node> child=new ArrayList<>();
        int index;
        long dis=Long.MAX_VALUE;
        boolean isVisit=false;
        boolean isVisit1=false;
        public Node(int index){
            this.index=index;
        }
    }
    static class edge{
        int count=0;
        int value;
        int index;
        public edge(int value,int index){
            this.value=value;
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
    }
}
