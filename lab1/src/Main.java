import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        fastReader in = new fastReader();
        int n=in.nextInt();
        HashMap<Integer,String> husband= new HashMap<>();
        HashMap<Integer,String> wife= new HashMap<>();
        HashMap<String,Integer> m= new HashMap<>();
        HashMap<String,Integer> w=new HashMap<>();
        ArrayList<Integer> free_man=new ArrayList<>();
        String s;// their name
        int[][] man_list=new int[n][n];// man in order of preference
        int[][] woman_list=new int[n][n];// woman in order of preference
        int[][] inverse_womanlist=new int[n][n];// inverse
        int [] love=new int[n];//   Like the number of the girl
        int [] beloved=new int[n];// Husband's Number
        int [] man_point=new int[n];
        for(int i=0;i<n;i++){
            s=in.next();
            m.put(s,i);
            husband.put(i,s);
        }
        for(int i=0;i<n;i++){
            s=in.next();
            w.put(s,i);
            wife.put(i,s);
        }
        for(int i=0;i<n;i++){
            free_man.add(i);
            love[i]=-1;
            beloved[i]=-1;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                man_list[i][j]=w.get(in.next());
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                woman_list[i][j]=m.get(in.next());
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                inverse_womanlist[i][woman_list[i][j]]=j;
            }
        }

        GS(free_man,love,beloved,inverse_womanlist,man_point,man_list);


        for(int i=0;i<n;i++){
            out.println(husband.get(i)+" "+wife.get(love[i]));
        }
        out.close();
    }
    public static void GS(ArrayList<Integer> free_man,int [] love,int [] beloved,int[][] inverse_womanlist,int[]man_point
                        ,int[][] man_list){
        while(!free_man.isEmpty()){
            int m=free_man.get(0);
            free_man.remove(0);
            while (love[m]==-1) {
                int w = man_list[m][man_point[m]];
                man_point[m]++;
                if (beloved[w] == -1) {
                    love[m] = w;
                    beloved[w] = m;
                } else if (inverse_womanlist[w][beloved[w]] > inverse_womanlist[w][m]) {
                    free_man.add(beloved[w]);
                    love[beloved[w]] = -1;
                    beloved[w] = m;
                    love[m] = w;

                }
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

