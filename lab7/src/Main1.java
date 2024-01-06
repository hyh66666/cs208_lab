import java.io.*;
import java.util.StringTokenizer;

public class Main1 {
    static int n;
    static long[] v, pre_sum, min, max, ans;
    static Head[] heads;

    public static void main(String[] args) {
        QReader r = new QReader();
        n = r.nextInt();
        v = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = r.nextLong();
        }

        heads = new Head[n + 1];
        for (int i = 1; i <= n; i++) {
            heads[i] = new Head(i);
        }
        int q = r.nextInt();
        ans = new long[q + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Long.MIN_VALUE;
        }
        for (int i = 0; i < q; i++) {
            int left = r.nextInt(), right = r.nextInt();
            Node cur1 = new Node(left, right, i + 1);
            Node cur2 = new Node(left, right, i + 1);
            if (heads[left].next == null)
                heads[left].next = cur1;
            else {
                Node temp = heads[left].next;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = cur1;
            }
            if (left != right) {
                if (heads[right].next == null)
                    heads[right].next = cur2;
                else {
                    Node temp = heads[right].next;
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = cur2;
                }
            }
        }
        //读取完数据，接下来进行查询
        //首先计算前缀和
        pre_sum = new long[n + 1];
        min = new long[n + 1];
        max = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre_sum[i] = pre_sum[i - 1] + v[i];
        }
        //接下来进行分治
        merge_divide(1, n);
        for (int i = 1; i <= q; i++) {
            out.println(ans[i]);
        }
        out.flush();
    }

    public static long merge_divide(int l, int r) {
        if (r - l < 1) {
            if (heads[l].next != null) {
                Node temp = heads[l].next;
                while (temp != null) {
                    if (temp.left == l)
                        ans[temp.id] = Math.max(v[l], ans[temp.id]);
                    temp = temp.next;
                }
            }
            return v[l];
        }
        int mid = (l + r) / 2;
        long a = merge_divide(l, mid);
        long b = merge_divide(mid + 1, r);
        return merge(l, mid, r,a,b);
    }

    public static long merge(int l, int mid, int r,long left_max,long right_max) {
        int left = mid - 1, right = mid + 1;
        min[left] = pre_sum[left];
        while (left > l - 1 && left > 0) {
            long temp = Math.min(min[left], pre_sum[left - 1]);
            left--;
            min[left] = temp;
        }
        max[right] = pre_sum[right];
        while (right < r) {
            long temp = Math.max(max[right], pre_sum[right + 1]);
            right++;
            max[right] = temp;
        }
        for (int i = l; i <= r; i++) {
            if (heads[i].next != null) {
                Node temp = heads[i].next;
                update(temp, l, mid, r,left_max,right_max);
            }
        }
        long temp = Math.max(left_max, right_max);
        temp = Math.max(temp,max[r] - min[l - 1]);
        return temp;
    }

    public static void update(Node temp, int l, int mid, int r,long left_max,long right_max) {
        while (temp != null) {
            long across;
            if (l < temp.left && temp.left <= mid && mid < temp.right && temp.right < r) {
                across = max[temp.right] - min[temp.left - 1];
                ans[temp.id] = Math.max(across, ans[temp.id]);
            } else if (l <= temp.left && temp.left <= mid && r <= temp.right) {
                across = max[r] - min[temp.left - 1];
                long temp_ans = Math.max(across, right_max);
                ans[temp.id] = Math.max(temp_ans, ans[temp.id]);
            } else if (temp.left <= l && mid <= temp.right && temp.right <= r) {
                across = max[temp.right] - min[l - 1];
                if (across > ans[temp.id] && temp.right != mid)
                    ans[temp.id] = across;
                if (ans[temp.id] < left_max)
                    ans[temp.id] = left_max;
            }
            temp = temp.next;
        }
    }

    static class Node {
        int left, right, id;
        Node next;

        public Node(int l, int r, int i) {
            id = i;
            left = l;
            right = r;
        }
    }

    static class Head {
        int val;
        Node next;

        public Head(int val) {
            this.val = val;
        }
    }


    static class QReader {
        BufferedReader br;
        StringTokenizer st;

        public QReader() {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

}
