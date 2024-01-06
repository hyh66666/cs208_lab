import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  //顶点的个数
        int M = sc.nextInt();  //边的个数
        int max = Integer.MAX_VALUE;  //无穷大，设为不可达
        int[][] map = new int[N][N]; //存储各顶点之间的距离
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = max;
            }
        }
        //输入各点之间的距离
        for (int k = 0; k < M; k++){
            int a = sc.nextInt(); //起点
            int b = sc.nextInt(); //终点
            int c = sc.nextInt(); //距离
            map[a][b] = c;
        }
        int[] dis = new int[N]; //源点到各点的距离，源点设为0
        for (int i = 0; i < N; i++)
            dis[i] = map[0][i];
        boolean[] visited = new boolean[N];
        visited[0] = true;
        //Dijkstra
        for (int i = 0; i < N; i++){
            int min = max;
            int idx = 0;
            //找到距离源点最近的点
            for (int j = 0; j < N; j++){
                if (!visited[j] && dis[j] < min){
                    min = dis[j];
                    idx = j;
                }
            }
            visited[idx] = true;
            //扩展
            for (int k = 0; k < N; k++){
                if (map[idx][k] < max){
                    if (map[idx][k] + dis[idx] < dis[k])
                        dis[k] = map[idx][k] + dis[idx];
                }
            }
        }
        for (int l = 0; l < N; l++){
            System.out.print(dis[l] + " ");
        }
    }
}
