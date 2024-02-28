import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static StringTokenizer st;
    static int R,C, mapNum[];
    static int map[][],memo[][];
    static int[] left = {0,-1},up = {-1,0},leftUp = {-1,-1},mappers[] = {left,up,leftUp};
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
            }
        }
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                int temp = 0;
                for(int[]mapper:mappers){
                    int rr = r+mapper[0];
                    int cc = c+mapper[1];
                    if(rr<0||cc<0) continue;
                    temp = Math.max(temp,memo[rr][cc]);
                }
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