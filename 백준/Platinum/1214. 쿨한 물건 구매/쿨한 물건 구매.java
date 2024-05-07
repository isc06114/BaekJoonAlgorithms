import java.io.*;
import java.util.*;

public class Main {

	static int D, B, S, bPower, sPower, ans;
	static ArrayList<Integer> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		if (B < S) {
			int temp = B;
			B = S;
			S = temp;
		}

		ans = Integer.MAX_VALUE;

		sPower = D / S;
		int tempValue = S * sPower;
		if (tempValue < D) {
			tempValue += S;
			sPower++;
		}
		int sbp = B / S;
		while (sPower >= 0) {
			// System.out.println(tempValue);
			ans = Math.min(ans, tempValue - D);
			if (ans == 0)
				break;
			tempValue -= sbp * S;
			sPower -= sbp;
			tempValue += B;
			if (tempValue - S >= D) {
				tempValue -= S;
				sPower--;
			}
		}
		System.out.println(D + ans);
	}

	static void findPower() {
		int temp;
		long max;
		temp = S;
		for (int i = 2; i <= temp / 2; i++) {
			if (temp % i == 0 && B % i == 0) {
				temp /= i;
			}
		}
		max = temp * B;
		sPower = (int) max / S;
		bPower = (int) max / B;

	}

}