import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, maxScore[][], tickerScore[][] = new int[2][];
        

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T;test_case++){
            n = Integer.parseInt(br.readLine());
            tickerScore[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.parseInt(x)).toArray();
            tickerScore[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.parseInt(x)).toArray();
            sol();
            //debug();
            System.out.println(Math.max(maxScore[0][n-1],maxScore[1][n-1]));
        }
    }

    static void sol(){
        maxScore = new int[2][n];
        maxScore[0][0] = tickerScore[0][0];
        maxScore[1][0] = tickerScore[1][0];
        if(n == 1) return;
        maxScore[0][1] = tickerScore[0][1]+tickerScore[1][0];
        maxScore[1][1] = tickerScore[1][1]+tickerScore[0][0];
        
        for(int i=2; i<n;i++){
            maxScore[0][i] = Math.max(maxScore[1][i-1],maxScore[1][i-2])+tickerScore[0][i];
            maxScore[1][i] = Math.max(maxScore[0][i-1],maxScore[0][i-2])+tickerScore[1][i];
        }
    }
    static void debug(){
        for(int i = 0; i<n;i++){
            System.out.print(maxScore[0][i]+" ");
        }
        System.out.println();
        for(int i = 0; i<n;i++){
            System.out.print(maxScore[1][i]+" ");
        }
        System.out.println();
    }
}