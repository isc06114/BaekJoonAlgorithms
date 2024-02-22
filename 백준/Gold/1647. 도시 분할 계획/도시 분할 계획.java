import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {
    static int N, M;
    static House[] houses;
    static House[] primHouses;

    public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        M = nextInt();
        houses = new House[N+1];
        primHouses = new House[N+1];
        for(int i = 0; i<= N;i++){
            primHouses[i] = new House(i);
            houses[i] = new House(i);
        } 
        for(int i = 0; i<M; i++){
            int x = nextInt();
            int y = nextInt();
            int w = nextInt();
            houses[x].edges.add(new Edge(y, w));
            houses[y].edges.add(new Edge(x, w));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.w, o2.w));
        for(Edge e: houses[1].edges)
            pq.add(e);
        int weight = 0;
        int maxEdgesWeight = 0;
        while (!pq.isEmpty()) {
            //System.out.println("pq.size:"+pq.size());
            Edge e = pq.poll();
            if(primHouses[e.houseIndex].rep == primHouses[1]) continue;
            primHouses[e.houseIndex].rep = primHouses[1];
            for(Edge ee:houses[e.houseIndex].edges)
                pq.add(ee);
            weight+=e.w;
            maxEdgesWeight = Math.max(maxEdgesWeight,e.w);
            //System.out.println(e.houseIndex+" "+weight);
        }
        weight-= maxEdgesWeight;
        System.out.println(weight);


    }
    
    static class House{
        ArrayList<Edge> edges;
        int index;
        House rep;
        House(int index){
            edges = new ArrayList<>();
            this.index = index;
            this.rep = this;
        }
    }
    static class Edge{
        int houseIndex;
        int w;
        public Edge(int houseIndex, int w){
            this.houseIndex = houseIndex;
            this.w = w;
        }
    }



    	 //Fast IO
    private static final int MAX_BUFFER_SIZE = 1<<16; 
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    private static void initFI(){
        inputStream = new DataInputStream(System.in);
        buffer = new byte[MAX_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    private static int nextInt() throws IOException{
        int ret = 0;
        byte c = read();
        while(c <= ' ') c= read();
        boolean neg = (c == '-');
        if(neg) c = read();
        do{
            ret = ret*10+c-'0';
        }while((c=read())>='0' && c<='9');
        if(neg) return (int) -ret;
        return (int) ret;
    }
    
    private static char nextChar() throws IOException{
        byte c = read();
        while(c <= ' ') c= read();
        return (char)c;
    }
    
    private static byte read() throws IOException{
        if(curIdx == maxIdx){
            maxIdx = inputStream.read(buffer,curIdx = 0, MAX_BUFFER_SIZE);
            if(maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}