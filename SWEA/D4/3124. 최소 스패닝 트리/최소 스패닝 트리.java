import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Solution {
    static int N, M;
    static int nodes[];
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        initFI();
        int T = nextInt();
        for(int test_case = 1; test_case <=T; test_case++){

            N = nextInt();
            M = nextInt();
            nodes = new int[N+1];
            for(int i = 0; i<= N;i++){
                nodes[i] = i;
            } 
            edges = new Edge[M];
            for(int i = 0; i< M; i++){
                edges[i] = new Edge(nextInt(),nextInt(),nextInt());
            }
            Arrays.sort(edges);
            long weight = 0;
            for(Edge e:edges){
                int fR = findRep(e.c1);
                int sR = findRep(e.c2);
                if(fR==sR){
                    // System.out.println("remove "+e);
                    continue;
                }
                if(fR>sR) nodes[sR] = fR;
                else nodes[fR] = sR;

                weight+=e.w;
                // System.out.println("add "+e.toString());
                // System.out.println(Arrays.toString(computers));
            }
            System.out.println("#"+test_case+" "+weight);
        }
    }


    static int findRep(int n){
        if(nodes[n] == n) return n;
        return findRep(nodes[n]);
    }
    static class Edge implements Comparable<Edge>{
        int c1, c2, w;
        public Edge(int c1, int c2, int w){
            this.c1 = c1;
            this.c2 = c2;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }

        @Override
        public String toString(){
            return (""+this.c1+" "+this.c2+" "+this.w);
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