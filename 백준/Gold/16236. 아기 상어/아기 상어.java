import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static StringTokenizer st;
    static int N,map[][];
    static int sharkR,sharkC, sharkSize;
    static int[] up = {-1,0}, left = {0,-1}, down = {1,0},right = {0,1},mappers[] = {up,left,right,down};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sharkSize= 2;
        map = new int[N][N];
        for(int r =0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 9){
                    map[r][c] =0;
                    sharkR = r;
                    sharkC = c;
                }
            }
        }
        int[] findFish = new int[]{0,0,0};
        int time = 1;
        int bite = 0;
        while(findFish[2] !=-1){
            findFish = findFish();
            time+=findFish[2];
            sharkR = findFish[0];
            sharkC = findFish[1];
            if(++bite == sharkSize){
                bite = 0;
                sharkSize++;
            }
        }
        System.out.println(time);

    }
    
    static boolean[][]visited;
    static int[] findFish(){
        visited = new boolean[N][N];
        PriorityQueue<int[]> visit = new PriorityQueue<>((o1,o2)-> (o1[2]==o2[2]) ? (o1[0]==o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0])) : Integer.compare(o1[2],o2[2]));
        visited[sharkR][sharkC] = true;
        visit.add(new int[]{sharkR, sharkC, 0});
        while(!visit.isEmpty()){
            int[] locate = visit.poll();
            if(map[locate[0]][locate[1]]!=0&&map[locate[0]][locate[1]]!=sharkSize) {
                map[locate[0]][locate[1]] = 0;
                return locate;
            }
            for(int[] mapper: mappers){
                int row = mapper[0]+locate[0];
                int col = mapper[1]+locate[1];
                if(row<0||row>=N||col<0||col>=N||visited[row][col]||map[row][col]>sharkSize) continue;
                visit.add(new int[]{row,col,locate[2]+1});
                visited[row][col] = true;
            }
        }
        return new int[]{-1,-1,-1};
    }
}