import java.io.*;
import java.util.*;

public class MainB {
    public static void main(String[] args) {
        fastReader in = new fastReader();
        int n = in.nextInt(), m = in.nextInt();
        int[] log = new int[n + 1];
        long[][] dp = new long[n + 1][22];
        HashMap<Long, Integer> getIndex = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dp[i][0] = in.nextLong();
            getIndex.put(dp[i][0], i);
        }
        log[0] = -1;
        for (int i = 1; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + (1 << j - 1)][j - 1]);
            }
        }
        long[] presum_income = new long[n + 1];
        presum_income[0] = 0;
        for (int i = 1; i <= n; i++) {
            presum_income[i] = in.nextLong() + presum_income[i - 1];
        }
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>(n + 1, (o1, o2) -> (int) (o2.income - o1.income)
        );
        Interval interval = new Interval(1, n, presum_income[n] - presum_income[0], 0);
        priorityQueue.add(interval);
        long sum=Sum(m,priorityQueue,getIndex,log,dp,presum_income);
        out.println(sum);
        out.close();
    }
    public static long Sum(int m,PriorityQueue<Interval> priorityQueue,HashMap<Long, Integer> getIndex,
                           int[] log,long [][] dp,long[] presum_income){
        long sum = 0,leftTime = m,min_a;
        int index;
        while (leftTime > 0) {
            Interval cur = priorityQueue.poll();
            assert cur != null;
            int time1 = (int) log[cur.right - cur.left + 1];
            min_a = Math.min(dp[cur.left][time1], dp[cur.right - (1 << time1) + 1][time1]);
            index = getIndex.get(min_a);
            min_a -= cur.basicValue;
            if (leftTime >= min_a) {
                sum += cur.income * min_a;
            }
            else {
                sum += cur.income * leftTime;
            }
            leftTime -= min_a;
            if (cur.left != index) {
                Interval temp = new Interval(cur.left, index - 1, presum_income[index - 1] - presum_income[cur.left - 1], cur.basicValue + min_a);
                priorityQueue.add(temp);
            }
            if (cur.right != index) {
                Interval temp = new Interval(index + 1, cur.right, presum_income[cur.right] - presum_income[index], cur.basicValue + min_a);
                priorityQueue.add(temp);
            }
        }
        return sum;
    }
    static class Interval {
        int left, right;
        long income, basicValue;

        public Interval(int left, int right, long income, long basicValue) {
            this.left = left;
            this.right = right;
            this.income = income;
            this.basicValue = basicValue;
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
