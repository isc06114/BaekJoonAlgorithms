import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	static int N;
	static int[] arr = new int[]{0,-1,-1,1,-1,1,2,-1,2,3,2,3,4,3,4};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//while(true){
			N = Integer.parseInt(br.readLine());
			if(N<15){
				System.out.println(arr[N]);
				return;
			}
			int five = (N/5);
			N%=5;
			for(int i =0; i<3; i++){
				if(arr[N+5*i]!=-1){
					System.out.println(five+arr[N+5*i]-i);
					return;
				}
			}
			System.out.println(-1);
		//}
	}
}