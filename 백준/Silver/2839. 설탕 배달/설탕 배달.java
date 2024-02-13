import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	static int N;
	static int[] arr = new int[]{0,-1,-1,1,-1,1,2,-1,2,3,2,3,4,3,4};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N<15){
			System.out.println(arr[N]);
			return;
		}
		int five = (N/5);
		N%=5;
		for(int i =0; i<3; i++){
			if((N+5*i)%3==0){
				System.out.println(five+((N+5*i)/3)-i);
				return;
			}
		}
		System.out.println(-1);
	}
}