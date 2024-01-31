import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Main {
    static int N,M, arr[];
    public static void main(String[] args) throws Exception {
        initFI();
        N = nextInt();
        M = nextInt();
        arr = new int[M+1];
        sol(1);


        
    }
    static void sol(int cnt){
        if(cnt > M){
            debug();
            return;
        }
        for(int i = arr[cnt-1]+1; i<=N; i++){
            arr[cnt] = i;
            sol(cnt+1);
        }

    } 
    static void debug(){
        for(int i=1; i<=M;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }




    ///////////////////////////Input///////////////////////////
    private static final int INPUT_BUFFER_SIZE = 4; 
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    private static void initFI(){
        inputStream = new DataInputStream(System.in);
        buffer = new byte[INPUT_BUFFER_SIZE];
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
        }while((c=read())>='0' && c<'9');
        if(neg) return -ret;
        return ret;
    }
    private static byte read() throws IOException{
        if(curIdx == maxIdx){
            maxIdx = inputStream.read(buffer,curIdx = 0, INPUT_BUFFER_SIZE);
            if(maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}