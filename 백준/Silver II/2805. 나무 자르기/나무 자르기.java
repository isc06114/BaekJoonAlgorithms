import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, M, cnt, trees[];

    public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        M = nextInt();
        trees = new int[N];
        for(int i=0; i<N; i++){
            trees[i] = nextInt();
        }
        Arrays.sort(trees);
        int cnt = 0, height = trees[N-1], remain;
        while(cnt<M){
            height -=1;
            for(int i = N-1; i>=0; i--){
                if(height>=trees[i]) break;
                cnt++;
            }
        }
        System.out.println(height);
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