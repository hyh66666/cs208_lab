
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n= in.nextInt();
        int m=in.nextInt();
        Matrix matrix=new Matrix(1,m);
        matrix.f[0][0]=1;
        for (int i = 1; i < m; i++) {
            matrix.f[0][i]=0;
        }
        Matrix base_matrix=new Matrix(m,m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(j==0||j-1==i) {
                    base_matrix.f[i][j]=1;
                }
                else {
                    base_matrix.f[i][j]=0;
                }
            }
        }
        Matrix matrix1=QuickPow(base_matrix,n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix1.f[i][j]+" ");
            }
            System.out.println();
        }
        Matrix res_matrix=matrix.Mul(matrix1);
        for (int i = 0; i < m; i++) {
            System.out.print(res_matrix.f[0][i] +" ");
        }
        System.out.print("\n");
        long res=res_matrix.getValue();
        System.out.println(res);
    }

    static class Matrix{
        int n,m;
        long[][] f;
        long mod=1000000007;
        public Matrix(int n,int m){
            this.n=n;
            this.m=m;
            f=new long[n][m];
        }
        public  Matrix Mul(Matrix matrix){
            Matrix new_matrix=new Matrix(n, matrix.m);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < matrix.m; j++) {
                    for (int k = 0; k < m; k++) {
                        new_matrix.f[i][j]=(new_matrix.f[i][j]+(this.f[i][k]*matrix.f[k][j])%mod)%mod;
                    }
                }
            }
            return new_matrix;
        }

        public long getValue(){
            long res =Long.MIN_VALUE;
            if(n==1) {
                res=0;
                for (int i = 0; i < m; i++) {
                    res=(res+f[0][i])%mod;
                }
            }
            return res;
        }

    }

    static Matrix QuickPow(Matrix a,int b){
        Matrix res=new Matrix(a.n, a.m);
        for (int i = 0; i < a.n; i++) {
            for (int j = 0; j < a.m; j++) {
                if(i==j){
                    res.f[i][j]=1;
                }
            }
        }
        while (b!=0){
            if(b%2==1){
                res=res.Mul(a);
            }
            a=a.Mul(a);
            b/=2;
        }
        return res;
    }
}
