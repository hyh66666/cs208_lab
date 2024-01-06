import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainB {
    public static void main(String[] args) {
        fastReader in= new fastReader();
        int n =in.nextInt();
        int m=in.nextInt();
        int c=in.nextInt();
        int t=in.nextInt();
        int[] bunnies=new int[n];
        int[] nests=new int[m];
        for (int i = 0; i < n; i++) {
            bunnies[i]=in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            nests[i]=in.nextInt();
        }
        //sort array
        mergeSort(bunnies);
        mergeSort(nests);
        int curb=0,curn=0;
        int result=0,count=0;
        while (curb<n&&curn<m){
            if(Math.abs(bunnies[curb]-nests[curn])<=t&&count<c){
                count++;
                result++;
                curb++;
            }
            else if(bunnies[curb]-nests[curn]<-t){
                curb++;
            }
            else if(bunnies[curb]-nests[curn]>t||count==c){
                count=0;
                curn++;
            }
        }
        out.println(result);
        out.close();
    }
    public static void mergeSort(int[] arr){
        int length= arr.length;
        int mid=length/2;
        if(length>1){
            int[]left= Arrays.copyOfRange(arr,0,mid);
            int[]right=Arrays.copyOfRange(arr,mid,length);
            mergeSort(left);
            mergeSort(right);
            merge(arr,left,right);
        }
    }
    public static void merge(int[]A,int[]left,int[]right) {
        int i = 0, j = 0;
        for (int k = 0; k < A.length; k++) {
            if (i <= left.length - 1 && (j > right.length - 1 || left[i] <= right[j])) {
                A[k] = left[i];
                i++;
            } else {
                A[k] = right[j];
                j++;
            }
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
