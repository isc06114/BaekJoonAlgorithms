import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Solution {

    static StringTokenizer st;
    static int N, mapNum[];
    static ArrayList<int[]> nodes;
    static int[] up = {-1,0},down = {1,0},left = {0,-1},right = {0,1},mappers[] = {up,down,left,right};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case =1;test_case<=T; test_case++){
            N = Integer.parseInt(br.readLine());
            boolean[][]map = new boolean[N][N];
            nodes = new ArrayList<>();

            for(int r = 0; r<N; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c<N; c++){
                    if(st.nextToken().charAt(0)=='1'){
                        if(r==0||c==0||r==N-1||c==N-1)continue;
                        nodes.add(new int[]{r,c});
                    }
                    else{
                        map[r][c] = true;
                    }
                }
            }
            sb.append('#').append(test_case).append(' ').append(TC(map)).append('\n');
        }
        System.out.println(sb);
    }
    static int ans1;
    static int ans;
    static int TC(boolean[][] map){
        ans = Integer.MAX_VALUE;
        ans1 = 0;
        dfs(0,map,0,0);
        return ans==Integer.MAX_VALUE?0:ans;
    }
    static int count;
    static void dfs(int nodeIndex, boolean[][] map,int moved, int nNodes){
        if(nodeIndex == nodes.size()){
            if(ans1<nNodes){
                ans1 = nNodes;
                ans = moved;
            }
            else if(ans1==nNodes){
                ans = Math.min(ans,moved);
            }
            return;
        }

        boolean[][] tempMap = new boolean[N][N];
        int[] node = nodes.get(nodeIndex);
        //System.out.println(node[0]+" "+node[1]);
        for(int[] mapper :mappers){
            for(int r = 0; r<N; r++){
                for(int c = 0; c<N; c++){
                    tempMap[r][c] = map[r][c];
                }
            }
            count = 0;
            boolean isMovable =  move(node[0],node[1],mapper,tempMap);
           // System.out.println(mapper[0]+" "+mapper[1]+" isMoveable?:"+ isMovable);

            if(isMovable) {
                dfs(nodeIndex+1, tempMap,moved+count, nNodes+1);
            }
        }
        dfs(nodeIndex+1, map,moved, nNodes);
    }

    static boolean move(int r,int c,int[] dir,boolean[][]map){
        int rr = r+dir[0];
        int cc = c+dir[1];
        if(rr<0||cc<0||rr>=N||cc>=N) return true;
        if(map[rr][cc]){
            map[rr][cc] = false;
            count++;
            return move(rr,cc,dir,map);
        }

        return false;
    }

}