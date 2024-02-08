import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

 
    static int N, K;
    static Item[] items;
    static StringBuilder sb;
    static int dp[][];
	public static void main(String[] args) throws IOException {
		initFI();
        sb = new StringBuilder();
        N = nextInt(); K = nextInt();
        items = new Item[N];
        dp = new int[K+1][N];
        int w, v;
        for(int i = 0; i< N; i++){
            w = nextInt(); v= nextInt();
            items[i] = new Item(w, v);
        }
        Arrays.sort(items);
        for(int weight = 1; weight<=K; weight++){
            dp[weight][0] = items[0].w<=weight?items[0].v:0;
        }
        for(int i =1; i<N; i++){
            for(w = 1; w <=K; w++){
                dp[w][i] = Math.max(dp[w][i-1],dp[w-1][i]);
                if(items[i].w<=w){
                    dp[w][i] = Math.max(dp[w][i],dp[w-items[i].w][i-1]+items[i].v);
                }
            }
        }


        //debug();
        
        System.out.println(dp[K][N-1]);
	}
    static void debug(){
        System.out.println();
        for(int i =0; i< N; i++){
            for(int j =0; j<= K; j++){
                System.out.print(dp[j][i]+" ");
            }
            System.out.println();
        }

    }

    static class Item implements Comparable<Item>{
        int w, v;
        Item(int w, int v){
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.w, o.w);
        }
        
        public Item compare(Item o){
            return (this.compareTo(o)>0)?this:o;
        }

    }

	 //Fast IO
    private static final int MAX_BUFFER_SIZE = 1<<13; 
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

    private static byte read() throws IOException{
        if(curIdx == maxIdx){
            maxIdx = inputStream.read(buffer,curIdx = 0, MAX_BUFFER_SIZE);
            if(maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}