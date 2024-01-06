import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in );
        int n = in .nextInt();
        out.println(n);
        for (int i = 0; i < n; i++) {
            out.println(1+" "+(i+1));
            out.print(n-1+" ");
            for (int j = 0; j < n; j++) {
                if(j!=i){
                    out.print((j+1)+" ");
                }
            }
            out.println();
        }
        out.close();
    }
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
}
