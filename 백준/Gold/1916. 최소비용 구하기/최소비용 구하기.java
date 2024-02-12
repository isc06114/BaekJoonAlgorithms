import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,M, start,end;
    static long root[];
    static ArrayList<Node> edge[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        edge = new ArrayList[N];
        root = new long[N];
        Arrays.fill(root,Long.MAX_VALUE);
        for(int i = 0; i< N; i++)edge[i] = new ArrayList<Node>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            edge[start].add(new Node(end,w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken())-1;
        end = Integer.parseInt(st.nextToken())-1;
        root[start] = 0;
        dijk(start);
        System.out.println(root[end]);
    }
    static void dijk(int start){
        PriorityQueue <Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node startNode = pq.poll();
            //System.out.println(startNode);
            if(startNode.w>root[end]) continue;
            for(Node n : edge[startNode.city]){
                long newEdge = root[startNode.city]+n.w;
                if(newEdge<root[n.city]){
                    root[n.city] = newEdge;
                    if(n.city!=end)
                        pq.add(new Node(n.city,root[n.city]));
                }    
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int city;
        long w;
        Node(int city, long w){
            this.city = city;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w,o.w);
        }
        @Override
        public String toString(){
            return "city: "+city+", w: "+w+"\n";
        }
    }
}