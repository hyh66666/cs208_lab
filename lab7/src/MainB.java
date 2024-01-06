import java.io.*;
import java.util.StringTokenizer;

public class MainB {
    public static long[] preSum;
    public static long[] v;
    public static long[] max;
    public static long[] min;
    public static node[] nodes;
    public static long[] result;

    public static void main(String[] args) {
        fastReader in = new fastReader();
        int n= in. nextInt();
        nodes =new node[n+1];
        preSum=new long[n+1];
        v=new long[n+1];
        max=new long[n+1];
        min=new long[n+1];
        for (int i = 1; i < n+1; i++) {
            v[i]=in.nextLong();
            preSum[i]=preSum[i-1]+v[i];
            nodes[i]=new node(-1,-1);
        }
        int q=in.nextInt();
        result=new long[q+1];
        result[0]=Long.MIN_VALUE;
        for (int i = 0; i < q; i++) {
            result[i+1]=Long.MIN_VALUE;
            int left=in.nextInt();
            int right=in.nextInt();
            node node=new node(left,right);
            node.index=i+1;
            node node1=new node(left,right);
            node1.index=i+1;
            node cur=nodes[left];
            while (cur.next!=null){
                cur=cur.next;
            }
            cur.next=node;
            cur=nodes[right];
            while (cur.next!=null){
                cur=cur.next;
            }
            cur.next= node1;
        }
        long a=divide(1, v.length-1);
        for (int i = 1; i < result.length; i++) {
            out.println(result[i]);
        }
        out.close();
    }

    public static long divide(int left,int right){
        int mid=(left+right)/2;
        if(left!=right){
            long leftSum = divide(left,mid);
            long rightSum = divide(mid+1,right);
            return merge(left,mid,right,leftSum,rightSum);
        }
        else {
            node cur=nodes[left];
            while (cur.next!=null){
                node temp=cur.next;
                if(temp.left==left){
                    result[temp.index]=preSum[left]-preSum[left-1];
                }
                cur=cur.next;
            }
            return preSum[left]-preSum[left-1];
        }
    }

    public static long merge(int l,int mid, int r,long leftSum,long rightSum){
        updateMax(mid,r);
        updateMin(mid,l);
        updateResult(leftSum,rightSum,l,r,mid);
        long max_cross_mid=max[r]-min[l-1];
        return Math.max(Math.max(leftSum,rightSum),max_cross_mid);
    }

    public static void updateResult(long leftSum,long rightSum,int l,int r,int mid){
        for (int i = l; i <= r ; i++) {
            node cur=nodes[i];
            while (cur.next!=null){
                node temp=cur.next;
                int ql=temp.left;
                int qr=temp.right;
                if(l<ql&&ql<=mid&&mid<qr&&qr<r){
                    result[temp.index]=Math.max(result[temp.index], max[qr]-min[ql-1]);
                }
                if(l<=ql && ql<=mid && r<=qr){
                    result[temp.index]=Math.max(result[temp.index],Math.max(rightSum,max[r]-min[ql-1]));
                }
                if(ql<=l && mid<=qr && qr<=r){
                    if (max[qr] - min[l - 1] > result[temp.index] && qr != mid)
                        result[temp.index] = max[qr] - min[l - 1];
                    if (result[temp.index] < leftSum)
                        result[temp.index] = leftSum;
                }
                cur=cur.next;
            }
        }
    }

    public static void updateMax(int mid,int r){
        if(mid+1<=r) {
            max[mid + 1] = preSum[mid + 1];
        }
        int i=mid+1;
        while (i<r){
            max[i+1]=Math.max(max[i],preSum[i+1]);
            i++;
        }
    }

    public static void updateMin(int mid,int l){
        if(mid-1>=l-1) {
            min[mid - 1] = preSum[mid - 1];
        }
        int i=mid-1;
        while (i>l-1){
            min[i-1]=Math.min(min[i],preSum[i-1]);
            i--;
        }
    }

    static class node{
        int left,right,index;
        node next;
        public node(int left,int right){
            this.left=left;
            this.right=right;
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
