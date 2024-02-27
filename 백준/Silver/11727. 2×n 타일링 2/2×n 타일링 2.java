import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int dp[] = new int [1001];
	static{
		dp[0]=0;
		dp[1] = 1;
		dp[2] = 3;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println(dp(N));
	}
	static int dp(int n){
		if(dp[n]!=0) return dp[n];
		dp[n] = (dp(n-1)+dp(n-2)*2)%10007;
		return dp[n];
	}


	
	
}