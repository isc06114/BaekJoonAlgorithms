import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M, dp[][],arr[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        for(int i = 1; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N;j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i<=N;i++) for(int j = 1; j<=N;j++)  dp[i][j]=dp[i-1][j]+dp[i][j-1]+arr[i][j]-dp[i-1][j-1];
        
        int fx,fy,sx,sy;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            sx= Integer.parseInt(st.nextToken())-1;
            sy= Integer.parseInt(st.nextToken())-1;
            fx=Integer.parseInt(st.nextToken());
            fy=Integer.parseInt(st.nextToken());
            //System.out.println(dp[fx][fy]+", +"+dp[sx][sy]+", -"+dp[fx][sy]+", -"+dp[sx][fy]);
            sb.append(dp[fx][fy]+dp[sx][sy]-dp[fx][sy]-dp[sx][fy]);
            sb.append('\n');
        }
        System.out.print(sb);
        

        //debug(arr);
        //debug(dp);
        
    }


    static void debug(int[][] f_array){
        for(int i = 1; i<=N;i++){
            for(int j = 1; j<=N;j++){
                System.out.print(f_array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}