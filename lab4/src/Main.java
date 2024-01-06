import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n=in.nextInt();
        int m=in.nextInt();
        long c=in.nextInt();
        boolean isOut=false;
        Node[] nodes=new Node[n+1];
        for (int i = 1; i < n+1; i++) {
            nodes[i]=new Node();
            nodes[i].h=in.nextInt();
            nodes[i].a=in.nextInt();
            nodes[i].b=in.nextInt();
            nodes[i].c=in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int u=in.nextInt();
            int v=in.nextInt();
            nodes[u].child.add(nodes[v]);
            nodes[v].inDegree++;
        }
        if(nodes[1].h>c){
            System.out.println(-1);
            return;
        }
        Node node=new Node();
        node.ans=c;
        update(node,nodes[1]);
        Queue<Node> queue=new LinkedList<>();
        queue.add(nodes[1]);
        while (!queue.isEmpty()){
            Node temp= queue.poll();
            for (int i = 0; i < temp.child.size(); i++) {
                Node child=temp.child.get(i);
                if(temp.ans_log>0||temp.ans>=child.h){
                    update(temp,child);
                }
                child.inDegree--;
                if(child.inDegree==0){
                    queue.add(child);
                }
            }
        }
        double ans_log=0;
        long ans=0;
        for (int i = 1; i <nodes.length; i++) {
            if(nodes[i].child.isEmpty()){
                if(nodes[i].ans>0||nodes[i].ans_log>0){
                    isOut=true;
                }
                if(nodes[i].ans_log>ans_log){
                    ans_log=nodes[i].ans_log;
                    ans=nodes[i].ans;
                }
                else if(nodes[i].ans_log<=0&&nodes[i].ans>ans){
                    ans=nodes[i].ans;
                }
            }
        }
        if(isOut){
            out.println(ans);
        }
        else {
            out.println(-1);
        }
        out.close();
    }

    public static void update(Node cur,Node next){
        int mod=1000000007;
        if(cur.ans_log>0){
            if(cur.ans_log+Math.log(next.b)>next.ans_log) {
                next.ans_log = cur.ans_log + Math.log(next.b);
                next.ans = cur.ans * next.b % mod;
            }
        }
        else {
            long max=Math.max(cur.ans+ next.a, cur.ans* next.b );
            max=Math.max(max, next.c);
            if(max>Math.pow(10,9)){
                double temp=Math.log(max);
                if(temp> cur.ans_log){
                    next.ans_log=temp;
                    next.ans=max%mod;
                }
            }
            else {
                if(next.ans_log==0&&max> next.ans){
                    next.ans=max;
                }
            }
        }
    }

    static class Node{
        int inDegree=0;
        int h,a,b;
        long c;
        long ans=0;
        double ans_log=0;
        ArrayList<Node> child=new ArrayList<>();
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
