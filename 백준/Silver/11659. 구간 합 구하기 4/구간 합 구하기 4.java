import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M, dp[],arr[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i=1;i<=N;i++) dp[i]=dp[i-1]+arr[i];
        int a,b;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a= Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(dp[b]-dp[a-1]);
        }
        
    }


    static void debug(int[] f_array){
        for(int i=1; i<=N;i++){
            System.out.print(f_array[i]+" ");
        }
        System.out.println();
    }
}