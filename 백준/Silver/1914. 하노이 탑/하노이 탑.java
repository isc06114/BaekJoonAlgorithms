import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger k = BigInteger.TWO.pow(N).subtract(BigInteger.ONE);
        System.out.println(k);
        if(N<=20)
            hanoi(N,1,2,3);
        
    }


    static void hanoi(int n,int s,int m,int e){
        if (n == 1){
            System.out.println(s+" "+ e);
            return;
        }
        hanoi(n-1,s,e,m);
        System.out.println(s+" "+e);
        hanoi(n-1,m,s,e);

    }
}
