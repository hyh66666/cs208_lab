import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n=in.nextInt();
        int[] road=new int[n];
        for (int i = 0; i < n; i++) {
            road[i]=in.nextInt();
        }
        Node[] nodes=new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i]=new Node(i);
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(nodes[0]);
        Node temp=queue.poll();
        assert temp != null;
        temp.isVisited=true;
        if(!nodes[temp.index+1].isVisited) {
            nodes[temp.index+1].isVisited = true;
            queue.add(nodes[temp.index+1]);
            nodes[temp.index+1].deep = temp.deep+1;
        }
        if(!nodes[road[0]-1].isVisited){
            nodes[road[0]-1].isVisited=true;
            queue.add(nodes[road[0]-1]);
            nodes[road[0]-1].deep= temp.deep+1;
        }

        while (queue.size()!=0){
            temp= queue.poll();
            if(temp.index==n-1){
                Node temp1;
                for (int i = 0; i < 2; i++) {
                    if(i==0){
                        temp1=nodes[n-2];
                    }
                    else {
                        temp1=nodes[road[n-1]-1];
                    }
                    if(!temp1.isVisited){
                        temp1.isVisited=true;
                        queue.add(temp1);
                        temp1.deep= temp.deep+1;
                    }
                }
            }
            else {
                Node temp1;
                for (int i = 0; i < 3; i++) {
                    if(i==0){
                        temp1=nodes[temp.index-1];
                    }
                    else if(i==1){
                        temp1=nodes[temp.index+1];
                    }
                    else {
                        temp1=nodes[road[temp.index]-1];
                    }
                    if(!temp1.isVisited){
                        temp1.isVisited=true;
                        queue.add(temp1);
                        temp1.deep= temp.deep+1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(nodes[i].deep+" ");
        }
        out.close();
    }
    static class Node{
        int index;
        int deep=0;
        boolean isVisited=false;
        public Node(int n){
            index=n;
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
