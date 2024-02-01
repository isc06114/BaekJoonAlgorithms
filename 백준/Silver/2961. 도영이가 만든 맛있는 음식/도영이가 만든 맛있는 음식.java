import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int fs = 1,fb = 0;
		int N = sc.nextInt();
		int[] ss = new int[N], bs = new int[N];
		for(int i = 0; i < N; i++) {
			ss[i] = sc.nextInt();
			bs[i] = sc.nextInt();
		}
		int result = 100000000;

		for(int i = 1; i < 1<<N; i++) {
			fs = 1;
			fb = 0;
			for(int j = 0; j < N; j++) {
				if((i&1<<j) == 1<<j) {
					
					fs*=ss[j];
					fb+=bs[j];
				}
			}
			result = Math.min(Math.abs(fs-fb), result);
		}
		System.out.print(result);

	}
}
