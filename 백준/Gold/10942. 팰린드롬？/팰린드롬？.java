import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static StringTokenizer st;
    static int N,arr[],memo[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        memo = new int[2*N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<2*N-1; i++){
            if(i%2==0)
                memo[i] = check(i/2,1);
            else
                memo[i] = check((i-1)/2,(i-1)/2+1,0);
        }
        // System.out.println(Arrays.toString(memo));
        int M = Integer.parseInt(br.readLine());
        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            // sb.append(memo[a+b]+" "+((b-a-1)/2)+"\n");
            if((b+a)%2==0)
                sb.append((memo[a+b]>=(b-a)/2)?1:0).append('\n');
            else
                sb.append((memo[a+b]>=(b-a-1)/2)?1:0).append('\n');
        }
        System.out.print(sb);
        

    }
    static int check(int n,int k){
        if(n-k<0||n+k>=N) return k-1;
        if(arr[n-k]==arr[n+k]) return check(n,k+1);
        return k-1;
    }
    static int check(int a,int b,int k){
        if(a-k<0||b+k>=N) return k;
        if(arr[a-k]==arr[b+k]) return check(a,b,k+1);
        return k-1;
    }
}