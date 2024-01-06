import java.io.*;
import java.util.StringTokenizer;

public class MainB {
    public static void main(String[] args) {
        fastReader in = new fastReader();
        int n=in.nextInt();
        int q=in.nextInt();
        int[] a=new int[n];
        int max=0;
        for (int i = 0; i < n; i++) {
            a[i]=in.nextInt();
            if(a[i]>max){
                max=a[i];
            }
        }
        node[] root =new node[n+1];
        for (int i = 0; i <= n; i++) {
            root[i]=new node(1,max);
        }
        for (int i = 1; i <=n ; i++) {
            build(root[i],root[i-1],1,max,a[i-1]);
        }
        int[] l=new int[q];
        int[] r=new int[q];
        for (int i = 0; i < q; i++) {
            l[i]=in.nextInt();
            r[i]=in.nextInt();
        }
        int median;
        for (int i = 0; i < q; i++) {
            median=getMedian(root[l[i]-1],root[r[i]],1,max,(r[i]-l[i]+2)/2);
            out.println(median);
        }
        out.close();

    }
    public static void build(node new_node,node pre_node,int low_bound,int high_bound,int value){
        if(pre_node!=null) {
            new_node.left = pre_node.left;
            new_node.right = pre_node.right;
            new_node.low_bound = pre_node.low_bound;
            new_node.high_bound = pre_node.high_bound;
            new_node.cnt = pre_node.cnt + 1;
        }
        else {
            new_node.cnt++;
        }
        if(low_bound==high_bound){
            return;
        }
        int mid=low_bound+(high_bound-low_bound)/2;
        if(value<=mid){
            new_node.left=new node(low_bound,mid);
            if(pre_node!=null) {
                build(new_node.left, pre_node.left, low_bound, mid, value);
            }
            else {
                build(new_node.left, null, low_bound, mid, value);
            }
        }
        else {
            new_node.right=new node(mid+1,high_bound);
            if(pre_node!=null) {
                build(new_node.right, pre_node.right, mid + 1, high_bound, value);
            }
            else {
                build(new_node.right, null, mid + 1, high_bound, value);
            }
        }
    }

    public static int getMedian(node left,node right,int low_bound,int high_bound,int rank){
        if(low_bound==high_bound){
            return low_bound;
        }
        int mid=low_bound+(high_bound-low_bound)/2;
        int left_diff = 0;
        if((left!=null&&left.left!=null)&&right!=null&&right.left!=null) {
            left_diff=right.left.cnt - left.left.cnt;
        }
        else if((left == null || left.left == null)&&right!=null&&right.left!=null){
            left_diff=right.left.cnt;
        }
        assert right != null;
        if(rank<=left_diff){
            if(left!=null) {
                return getMedian(left.left, right.left, low_bound, mid, rank);
            }
            else {
                return getMedian(null, right.left, low_bound, mid, rank);
            }
        }
        else {
            if(left!=null) {
                return getMedian(left.right, right.right, mid + 1, high_bound, rank - left_diff);
            }
            else {
                return getMedian(null, right.right, mid + 1, high_bound, rank - left_diff);
            }
        }
    }
    static class node{
        int low_bound;
        int high_bound;
        node left;
        node right;
        int cnt=0;
        public node(int low_bound,int high_bound){
            this.low_bound=low_bound;
            this.high_bound=high_bound;
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
