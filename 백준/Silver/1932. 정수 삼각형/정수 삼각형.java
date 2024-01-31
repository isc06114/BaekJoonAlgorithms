import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int tree[][], maxTree[][], n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        tree = new int[n+1][];
         maxTree = new int[n+1][];;
        for(int i = 1; i<=n; i++){
            tree[i] = new int[i];
            maxTree[i] = new int[i];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<i; j++){
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxTree[1][0] = tree[1][0];
        for(int i = 2; i<=n; i++){
            maxTree[i][0] = maxTree[i-1][0]+tree[i][0];
            maxTree[i][i-1] = maxTree[i-1][i-2]+tree[i][i-1];
            for(int j = 1; j<i-1; j++){
                maxTree[i][j] = Math.max(maxTree[i-1][j],maxTree[i-1][j-1])+tree[i][j];
            }
        }

        System.out.println(Arrays.stream(maxTree[n]).max().orElse(0));

        


    }
}