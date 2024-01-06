public class test{
    public static void main(String[] args) {
        dp(5);
    }
    public static void dp(int n){
        int[][] array = new int[n + 1][n + 1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                array[i][j]=Math.abs(j-i);
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        for(int l = 0; l < n + 1; l++){
            for(int i = 0; i < n - l + 1; i++){
                int j = i + l;
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
    }
}