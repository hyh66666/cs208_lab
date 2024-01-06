import java.util.ArrayList;
import java.util.Scanner;

public class MainA {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n=in.nextInt();
        int l=in.nextInt();
        int r=in.nextInt();
        int mod=1000000007;
        long sum=0;
        int[] count=new int[5001];
        ArrayList<Integer> weight=new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            int a=in.nextInt();
            count[a]++;
            if(!weight.contains(a)) {
                weight.add(a);
            }
            sum+=a;
        }
        int min=(int)Math.max(sum-r,l);
        int max=(int) Math.min(sum-l,r);
        long[][] opt=new long[2][max+1];
        opt[0][0]=1;
        for (int i = 0; i < weight.size(); i++) {
            int w=weight.get(i);
            if(i%2==0){
                for (int j = 0; j < max+1; j++) {
                    if(w>j){
                        opt[1][j]=opt[0][j];
                    }
                    else {
                        long sum1=0;
                        for (int k = 1; k <=count[w]&&j-k*w>=0 ; k++) {
                            sum1=(sum1+opt[0][j-k*w])%mod;
                        }
                        opt[1][j]=(sum1+opt[0][j])%mod;
                    }
                }
            }
            else {
                for (int j = 0; j < max+1; j++) {
                    if(w>j){
                        opt[0][j]=opt[1][j];
                    }
                    else {
                        long sum1=0;
                        for (int k = 1; k <=count[w]&&j-k*w>=0 ; k++) {
                            sum1=(sum1+opt[1][j-k*w])%mod;
                        }
                        opt[0][j]=(sum1+opt[1][j])%mod;
                    }
                }
            }
        }
        long ans=0;
        if(weight.size()%2==0){
            for (int i = min; i < max+1; i++) {
                ans=(ans+opt[0][i])%mod;
            }
        }
        else {
            for (int i = min; i < max+1; i++) {
                ans=(ans+opt[1][i])%mod;
            }
        }
        System.out.println(ans);
    }
}
