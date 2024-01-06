import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        Random r =new Random();
        int n= r.nextInt(25)+1;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print(r.nextInt(100)-50+" ");
        }
        System.out.println();
        int q=r.nextInt(20)+1;
        System.out.println(q);
        for (int i = 0; i < q; i++) {
            int left=r.nextInt(n)+1;
            System.out.print(left+" ");
            int right=r.nextInt(n)+1;
            while (right<left){
                right=r.nextInt(n)+1;
            }
            System.out.print(right+"\n");
        }
    }
}
