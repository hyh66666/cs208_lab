import java.util.ArrayList;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n =in.nextInt();
        int m= in.nextInt();
        int mod=1000000007;
        Node[] nodes =new Node[n+1];
        for (int i = 0; i < n + 1; i++) {
            nodes[i]=new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int u =in.nextInt();
            int v=in.nextInt();
            nodes[v].child.add(nodes[u]);
        }
        int size=(int)Math.pow(2,n);
        long[] state=new long[size];
        state[0]=1;
        for (int i = 0; i < n; i++) {
            state[(int) Math.pow(2,i)]=1;
        }
        for (int i = 1; i < size; i++) {
            String s = Integer.toBinaryString(i);
            ArrayList<Node> needAdd = new ArrayList<>();
            ArrayList<Node> haveAdd = new ArrayList<>();
            int x=0;
            for (int j = 0; j <n; j++) {
                if(j<n-s.length()){
                    needAdd.add(nodes[n - j]);
                }
                else if (s.charAt(x) == '0') {
                    needAdd.add(nodes[n - j]);
                    x++;
                }
                else {
                    haveAdd.add(nodes[n-j]);
                    x++;
                }
            }
            for (Node node : needAdd) {
                int index = i + (int) Math.pow(2, node.index - 1);
                int count = 0;
                for (Node added : haveAdd) {
                    if (added.child.contains(node)) {
                        count++;
                    }
                }
                state[index] = (state[i]* (long) Math.pow(2, count)%mod + state[index])%mod ;
            }

        }
        System.out.println(state[size-1]);

    }
    static class Node{
        int index;
        ArrayList<Node> child=new ArrayList<>();
        public Node(int index){
            this.index=index;
        }
    }
}
