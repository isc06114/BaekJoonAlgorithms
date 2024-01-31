import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,a,b, dp[];
    static StringBuilder sb;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;i++) dp[i] = Integer.parseInt(st.nextToken());
        for(int i=1;i<=N;i++) dp[i]+=dp[i-1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a= Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(dp[b]-dp[a-1]);
            sb.append('\n');
        }
        System.out.println(sb);
        
    }
}