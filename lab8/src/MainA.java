import java.io.*;
import java.util.StringTokenizer;

public class MainA {
    public static void main(String[] args) {
        fastReader in =new fastReader();
        int n=in.nextInt();
        int size=(int) Math.pow(2,n);
        complex[] s=new complex[size];
        for (int i = 0; i < size; i++) {
            s[i]=new complex(in.nextDouble(),0);
        }
        complex[] result=FFT(size,s);
        for (int i = 0; i < size; i++) {
            out.println(Math.sqrt(Math.pow(result[i].real,2)+Math.pow(result[i].image,2)));
        }
        out.close();
    }
    public static complex[] FFT(int n,complex[] complexes){
        if(n==1){
            return complexes;
        }
        else {
            complex[] even=new complex[n/2];
            complex[] odd=new complex[n/2];
            int j=0,k=0;
            for (int i = 0; i < complexes.length; i++) {
                if(i%2==0){
                    even[j++]=new complex(complexes[i].real,complexes[i].image);
                }
                else {
                    odd[k++]=new complex(complexes[i].real,complexes[i].image);
                }
            }
            complex[] E=FFT(n/2,even);
            complex[] D=FFT(n/2,odd);
            complex[] y=new complex[n];
            for (int i = 0; i < n / 2; i++) {
                complex w=new complex(Math.cos(2*Math.PI*i/n),Math.sin(2*Math.PI*i/n));
                y[i]=E[i].add(w.mul(D[i]));
                y[i+n/2]=E[i].sub(w.mul(D[i]));
            }
            return y;
        }
    }
    static class complex{
        double real;
        double image;
        public complex(double real,double image){
            this.real=real;
            this.image=image;
        }
        public complex add(complex a){
            return new complex(real+a.real,image+a.image);
        }
        public complex sub(complex a){
            return new complex(real-a.real,image-a.image);
        }
        public complex mul(complex a){
            double new_real=(real*a.real)-(image*a.image);
            double new_image=(a.real*image)+(real*a.image);
            return new complex(new_real,new_image);
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
