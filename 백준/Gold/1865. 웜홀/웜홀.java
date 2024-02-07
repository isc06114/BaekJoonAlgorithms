import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int dp[][] = new int[500][500], max_warp_time,N,M,W;
    static ArrayList<int[]>[] graph = new ArrayList[500];
    static PriorityQueue<int[]> startQueue = new PriorityQueue<int[]>((o1,o2)-> Integer.compare(o1[1],o2[1]));
    static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)-> Integer.compare(o1[1],o2[1]));
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            solution();
        }
    }

    static void solution()throws IOException{
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        startQueue.clear();
        pq.clear();
        N = Integer.parseInt(st.nextToken());M = Integer.parseInt(st.nextToken());W = Integer.parseInt(st.nextToken());
        max_warp_time=0;
        for(int i = 0; i<N; i++){
            graph[i] = new ArrayList<>();
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i =0; i<M; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken())-1, e = Integer.parseInt(st.nextToken())-1, t = Integer.parseInt(st.nextToken());
            graph[s].add(new int[]{e,t});
            graph[e].add(new int[]{s,t});
        }

        for(int i =0; i<W; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            int s = Integer.parseInt(st.nextToken())-1, e = Integer.parseInt(st.nextToken())-1, t = Integer.parseInt(st.nextToken());
            max_warp_time +=t;
            graph[s].add(new int[]{e,-t});
            startQueue.add(new int[]{e,-t});
        }
        System.out.println(dijk()?"YES":"NO");
    }
    static boolean dijk(){
        while(!startQueue.isEmpty()){
            int[] pNode = startQueue.poll();
            pNode[1] = 0;
            pq.add(new int[]{pNode[0],pNode[1],0});
            while(!pq.isEmpty()){
                int[] startNode = pq.poll();
                if(startNode[2]>N){
                    pq.clear();
                    break;
                }
                for(int[] middleNode : graph[startNode[0]]){
                    //System.out.println("startNode: "+startNode[1]+", middleNode"+middleNode[1]);
                    if(dp[pNode[0]][middleNode[0]]>startNode[1]+middleNode[1]&&startNode[1]+middleNode[1]<max_warp_time) {
                        dp[pNode[0]][middleNode[0]] = startNode[1]+middleNode[1];
                        pq.add(new int[]{middleNode[0],dp[pNode[0]][middleNode[0]], startNode[2]+1});
                    }
                }
                if(dp[pNode[0]][pNode[0]]<0)return true;
                //debug();
            }
        }
        return false;

    }

    static void debug(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(dp[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}