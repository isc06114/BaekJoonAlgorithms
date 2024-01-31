import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M, dp[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][N];
        for(int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N;j++) dp[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<N; i++){
            dp[0][i] += dp[0][i-1];
            dp[i][0] += dp[i-1][0];
        }
        for(int i = 1; i<N;i++) for(int j = 1; j<N;j++)  dp[i][j]+=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1];
        
        int fx,fy,sx,sy,res;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            sx= Integer.parseInt(st.nextToken())-2;
            sy= Integer.parseInt(st.nextToken())-2;
            fx=Integer.parseInt(st.nextToken())-1;
            fy=Integer.parseInt(st.nextToken())-1;
            //System.out.println(dp[fx][fy]+", +"+dp[sx][sy]+", -"+dp[fx][sy]+", -"+dp[sx][fy]);
            res = dp[fx][fy];
            if(sx>=0) res-=dp[sx][fy];
            if(sy>=0) res-=dp[fx][sy];
            if(sx>=0&&sy>=0) res+=dp[sx][sy];
            sb.append(res);
            sb.append('\n');
        }
        System.out.print(sb);
        

        //debug(dp);
        
    }


    static void debug(int[][] f_array){
        for(int i = 0; i<N;i++){
            for(int j = 0; j<N;j++){
                System.out.print(f_array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}