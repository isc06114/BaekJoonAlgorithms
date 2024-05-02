import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static String numbers[];
	static int moded[], numbersLength[], timesMod[][];
	static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new String[N];
		moded = new int[N];
		numbersLength = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = br.readLine();
			numbersLength[i] = numbers[i].length();
		}
		K = Integer.parseInt(br.readLine());
		dp = new long[K][(1 << (N))];
		for (int i = 0; i < N; i++) {
			moded[i] = mod(numbers[i]);
			dp[moded[i]][(1 << i)]++;
		}

		timesMod = new int[K][51];
		for (int i = 0; i < K; i++) {
			timesMod[i][0] = i;
			for (int j = 1; j <= 50; j++) {
				timesMod[i][j] = (timesMod[i][j - 1] * 10) % K;
			}
		}
		numbers = null;
		for (int n = 1; n < N; n++) {
			for (int selected = 0; selected < N; selected++) {
				for (int bit = 1; bit < (1 << N); bit++) {
					int cnt = 0;
					int tempBit = bit;
					while (tempBit != 0) {
						tempBit -= (tempBit & -tempBit);
						cnt++;
					}
					if ((bit & (1 << selected)) == 0 && cnt == n) {
						for (int k = 0; k < K; k++) {
							if (dp[k][bit] != 0) {
								dp[(moded[selected] + timesMod[k][numbersLength[selected]]) % K][bit
										| (1 << selected)] += dp[k][bit];
							}
						}
					}
				}
			}
		}
		long p = dp[0][(1 << N) - 1];
		long q = factorial(N);
		for (int i = 15; i >= 2; i--) {
			while (p % i == 0 && q % i == 0) {
				p /= i;
				q /= i;
			}
		}
		System.out.println(p + "/" + q);
		// System.out.println(Arrays.toString(moded));
		// System.out.println(Arrays.toString(numbersLength));
	}

	static long factorial(int n) {
		if (n == 1)
			return 1;
		return n * factorial(n - 1);
	}

	static void debug() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.println("-------" + i + "-------");
			for (int j = 0; j < K; j++) {
				System.out.println("나머지: " + j);
				for (int bit = 0; bit < (1 << (N)); bit++) {
					System.out.println(Integer.toBinaryString(bit) + ": " + dp[j][bit]);
				}
			}
		}
		System.out.println(Arrays.toString(moded));
		System.out.println(Arrays.toString(timesMod[1]));
	}

	static int mod(String s) {
		if (s.length() > 15) {
			int temp = mod(s.substring(0, s.length() - 15));
			s = temp + s.substring(s.length() - 15, s.length());
		}
		return (int) (Long.parseLong(s) % K);
	}

}