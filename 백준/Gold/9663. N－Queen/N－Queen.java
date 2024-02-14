import java.io.DataInputStream;
import java.io.IOException;

public class Main {
    static int N, cnt = 0;
    public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        dfs(0,0,0,0);
        System.out.println(cnt);
    }
    static void dfs(int row, int downBitMask, int leftBitMask, int rightBitMask){
        if(row == N){
            cnt++;
            return;
        }
        int bit = (~(downBitMask|leftBitMask|rightBitMask))&((1<<N)-1);
        int colPos = (bit&-bit);       
        // String debugStr = String.format("row: %d 배치 가능한 col: %05d pos: %05d",row,Integer.parseInt(Integer.toBinaryString(bit)), Integer.parseInt(Integer.toBinaryString(colPos)));
        // System.out.println(debugStr);
        while(bit>0){
            dfs(row+1,downBitMask|colPos,(leftBitMask|colPos)<<1,(rightBitMask|colPos)>>1);
            bit-=colPos;
            colPos = (bit&-bit);
        }

    }


    	 //Fast IO
    private static final int MAX_BUFFER_SIZE = 2; 
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