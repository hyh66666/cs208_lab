import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n=in.nextInt();
        int m=in.nextInt();
        HashMap<Integer,Node> hashMap=new HashMap<>();
        Node head=new Node(0,0);
        Node tail=new Node(100000,10000000);
        head.next=tail;
        tail.pre=head;
        for (int i = 0; i < m; i++) {
            String s=in.next();
            if(s.equals("put")){
                int index=in.nextInt();
                int value=in.nextInt();

                if(hashMap.containsKey(index)){
                    hashMap.get(index).value=value;
                    Node temp=hashMap.get(index);
                    temp.pre.next=temp.next;
                    temp.next.pre=temp.pre;
                    tail.pre.next=temp;
                    temp.pre=tail.pre;
                    temp.next=tail;
                    tail.pre=temp;
                }
                else {
                    Node node =new Node(index,value);
                    hashMap.put(index,node);
                    tail.pre.next=node;
                    node.pre=tail.pre;
                    tail.pre=node;
                    node.next=tail;
                    if(hashMap.size()>n){
                        hashMap.remove(head.next.index);

                        head.next.next.pre=head;
                        head.next=head.next.next;
                    }
                }
            }
            else if(s.equals("get")){
                int index=in.nextInt();
                if(!hashMap.containsKey(index)){
                    out.println(-1);
                }
                else {
                    out.println(hashMap.get(index).value);
                    Node temp=hashMap.get(index);
                    temp.pre.next=temp.next;
                    temp.next.pre=temp.pre;
                    tail.pre.next=temp;
                    temp.pre=tail.pre;
                    temp.next=tail;
                    tail.pre=temp;
                }
            }
        }
        out.close();
    }
//    public void swap(Node temp,)
    static class Node{
        Node pre;
        Node next;
        int index;
        int value;
        public Node(int index,int value){
            this.index=index;
            this.value=value;
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
