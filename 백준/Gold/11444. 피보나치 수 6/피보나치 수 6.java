import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static final long mod = 1000000007;
    static Map<Long,Long> dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp = new TreeMap<>();
        dp.put(0l,0l);
        dp.put(1l,1l);
        dp.put(2l,1l);
        dp.put(3l,2l);
        sb.append(sol(N));
        System.out.println(sb);
    }

    static long sol(long n){
        long temp;
        if(dp.containsKey(n)) return dp.get(n);
        else{
            if(n%2==0){
                temp = sol(n/2)*((sol(n/2+1)+sol(n/2-1))%mod)%mod;
            }else{
                temp = (sol((n+1)/2)*sol((n+1)/2)%mod+sol((n-1)/2)*sol((n-1)/2)%mod)%mod;
            }
            dp.put(n,temp);
        }

        return temp;
    }
}