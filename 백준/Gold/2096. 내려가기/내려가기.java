import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;



public class Main {

	static int N,maxScore=-1,minScore=Integer.MAX_VALUE, g_map[][], dp[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		g_map = new int[N][3];
		dp = new int[N][3][2];
		for(int i =0; i< N; i++){
			st = new StringTokenizer(br.readLine());
			g_map[i][0] = Integer.parseInt(st.nextToken());
			g_map[i][1] = Integer.parseInt(st.nextToken());
			g_map[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<3; i++){
			dp[N-1][i][0] = g_map[N-1][i];
			dp[N-1][i][1] = g_map[N-1][i];
		}
		if(N == 1){
			for(int i=0; i<3; i++){
				maxScore = Math.max(maxScore,g_map[N-1][i]);
				minScore = Math.min(minScore,g_map[N-1][i]);
			}
			System.out.println(maxScore+" "+minScore);
			return;
		}

		for(int i = N-2; i>=0; i--){
			int leftMin = Math.min(dp[i+1][0][0], dp[i+1][1][0]);
			int rightMin = Math.min(dp[i+1][1][0], dp[i+1][2][0]);
			dp[i][0][0] = g_map[i][0]+leftMin;
			dp[i][2][0] = g_map[i][2]+rightMin;
			dp[i][1][0] = g_map[i][1]+Math.min(leftMin, rightMin);

			int leftMax = Math.max(dp[i+1][0][1], dp[i+1][1][1]);
			int rightMax = Math.max(dp[i+1][1][1], dp[i+1][2][1]);
			dp[i][0][1] = g_map[i][0]+leftMax;
			dp[i][2][1] = g_map[i][2]+rightMax;
			dp[i][1][1] = g_map[i][1]+Math.max(leftMax, rightMax);
		}

		for(int i=0; i<3; i++){
			maxScore = Math.max(maxScore,dp[0][i][1]);
			minScore = Math.min(minScore,dp[0][i][0]);
		}
		System.out.println(maxScore+" "+minScore);
	}
}