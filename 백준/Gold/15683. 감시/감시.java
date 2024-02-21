import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//12,736 kb 96 ms
public class Main {

    static int R,C,ans;
    static int[][] map;
    static ArrayList<int[]> five, others;
    static int[] dr = {0,1,0,-1};   //동남서북
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        five = new ArrayList<>();
        others = new ArrayList<>();
        ans = R*C;
        map = new int[R][C];
        long iswatch = 0;
        for(int r = 0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c<C;c++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 6){
                    map[r][c] = temp;
                    iswatch|=((long)1<<(r*8+c));
                    ans--;
                }
                else{
                    if(temp == 5)
                        five.add(new int[]{r,c});
                    else if(temp == 0);
                    else others.add(new int[]{r,c,temp});
                    map[r][c] = 0;
                }
            }
        }

        for(int[] position:five){
            for(int d= 0; d<4; d++){
                iswatch = move(position[0],position[1],dr[d],dc[d],iswatch);
            }
        }

        dfs(0,iswatch);
        System.out.println(ans);
    }

    static void dfs(int cnt, long watched){
        if(cnt == others.size()){
            int count = R*C;
            String s = Long.toBinaryString(watched);
            for(int i = 0; i<s.length(); i++){
                if(s.charAt(i)=='1') count--;
            }
            //System.out.println(Long.toBinaryString(watched));
            ans = Math.min(ans,count);

            return;
        }
        int[] data = others.get(cnt);
        int row = data[0];
        int col = data[1];
        long temps[]=move1(row, col, watched, data[2]);
        for(long temp:temps){
            dfs(cnt+1,temp);
        }
    }

    static long[] move1(int r, int c,long watch,int type){
        long[] temp = new long[4];
        for(int d = 0; d<4; d++){
            temp[d] = move(r,c,dr[d],dc[d],watch);
        }
        long[]temp1;
        if(type == 1) return temp;
        if(type == 2){
            temp1 = new long[2];
            for(int d = 0; d<2; d++){
                temp1[d]= temp[d]|temp[d+2];
            }
            return temp1;
        }
        if(type == 3){
            temp1 = new long[4];
            for(int d = 0; d<4; d++){
                temp1[d]= temp[d]|temp[(d+1)%4];

            }
            return temp1;
        }
        if(type == 4){
            temp1 = new long[4];
            for(int d = 0; d<4; d++){
                temp1[d]= temp[d]|temp[(d+1)%4]|temp[(d+2)%4];
            }
            return temp1;
        }
        return temp;
    }

    static long move(int r, int c,int dr, int dc,long watch){
        int row = r+dr;
        int col = c+dc;
        if(row<0||col<0||row>=R||col>=C) return  (watch|((long)1<<(r*8+c)));
        if(map[row][col]==6) return  (watch|((long)1<<(r*8+c)));
        return move(row,col,dr,dc, watch|((long)1<<(r*8+c)));
    }
}