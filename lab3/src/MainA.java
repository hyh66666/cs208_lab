import java.io.*;
import java.util.*;

public class MainA {
    public static void main(String[] args) {
        fastReader in=new fastReader();
        int n=in.nextInt();
        int m=in.nextInt();
        Species[] species=new Species[n];
        for (int i = 0; i < n; i++) {
            species[i]=new Species();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            species[v-1].inDegree++;
            species[u-1].reinDegree++;
            species[v-1].reFood.add(species[u-1]);
            species[u-1].food.add(species[v-1]);
        }
        Queue<Species> queue=new LinkedList<>();
        Queue<Species> reQueue=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(species[i].inDegree==0){
                queue.add(species[i]);
                species[i].result=1;
            }
            if(species[i].reinDegree==0){
                reQueue.add(species[i]);
                species[i].result1=1;
            }
        }
        long mod= (long) (Math.pow(10,9)+7);
        BFS(queue,mod);
        BFS1(reQueue,mod);
        for (int i = 0; i < n; i++) {
            out.print(species[i].result *species[i].result1%mod+" ");
        }
        out.close();
    }
    public static void BFS(Queue<Species> queue,long mod){
        while (!queue.isEmpty()){
            Species s= queue.poll();
            for (int i = 0; i <s.food.size(); i++) {
                s.food.get(i).inDegree--;
                s.food.get(i).result=(s.food.get(i).result+s.result)%mod;
                if(s.food.get(i).inDegree==0){
                    queue.add(s.food.get(i));
                }
            }
        }
    }

    public static void BFS1(Queue<Species> queue,long mod){
        while (!queue.isEmpty()){
            Species s= queue.poll();
            for (int i = 0; i <s.reFood.size(); i++) {
                s.reFood.get(i).reinDegree--;
                s.reFood.get(i).result1=(s.reFood.get(i).result1+s.result1)%mod;
                if(s.reFood.get(i).reinDegree==0){
                    queue.add(s.reFood.get(i));
                }
            }
        }
    }

    static class Species{
        int inDegree=0;
        long result=0;
        int reinDegree=0;
        long result1=0;
        ArrayList<Species> food=new ArrayList<>();
        ArrayList<Species> reFood=new ArrayList<>();
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
