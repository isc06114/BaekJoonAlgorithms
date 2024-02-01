import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, multiInput[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		multiInput = new int[N+1];
		for(int od : new int[]{2,3,5,7}) {
			multiInput[0] = od;
			sol(1);
		}

		
		
	}
	static void sol(int cnt) {
		if(cnt == N) {
			System.out.println(multiInput[N-1]);
			return;
		}
		int let = multiInput[cnt-1]*10;
		for(int i =1; i<10; i++) {
			if(isMinor(let+i)) {
				multiInput[cnt] = let+i;
				sol(cnt+1);
			}
		}
	}
	static boolean isMinor(int n) {
		for(int i =2; i<=Math.sqrt(n); i++) {
			if(n%i==0) return false;
		}
		return true;
	}
}