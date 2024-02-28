import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static StringTokenizer st;
    static int R,C, mapNum[];
    static int map[][],memo[][];
    static int[] left = {0,-1},up = {-1,0},mappers[] = {left,up};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        memo = new int[R][C];
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                int temp = 0;
                if(r>0) temp = Math.max(temp,memo[r-1][c]);
                if(c>0) temp = Math.max(temp,memo[r][c-1]);
                memo[r][c] = temp+map[r][c];
            }
        }
        // debug();
        System.out.println(memo[R-1][C-1]);
    }
    

    static void debug(){
        System.out.println();
        for(int r = 0; r<R;r++){
            for(int c = 0; c<C; c++){
                System.out.print(memo[r][c]+" ");
            }
            System.out.println();
        }
    }

}