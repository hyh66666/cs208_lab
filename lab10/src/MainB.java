import java.util.ArrayList;
import java.util.Scanner;

public class MainB {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> arrayList =new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a=in.nextInt();
            int j= arrayList.size()-1;
            int temp=-1;
            if(j>=0){
                temp=arrayList.get(j);
            }
            if(temp!=a){
                arrayList.add(a);
            }
        }
        int size= arrayList.size();
//        System.out.println(size);
        int[][] opt =new int[size][size];
        for(int l = 1; l < size; l++){
            for(int i = 0; i < size-l; i++){
                int j = i + l;
//                System.out.print(i+", "+j+" ");
                    if(arrayList.get(i).equals(arrayList.get(j))){
                        opt[i][j]=opt[i+1][j-1]+1;
                    }
                    else {
                        opt[i][j]=Math.min(opt[i+1][j]+1,opt[i][j-1]+1);
                    }
//                System.out.print(opt[i][j]);
            }
//            System.out.println();
        }
        System.out.println(opt[0][size-1]);
    }
}
