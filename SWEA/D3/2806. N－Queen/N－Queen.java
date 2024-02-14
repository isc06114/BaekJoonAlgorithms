import java.io.DataInputStream;
import java.io.IOException;

public class Solution {
    static int N, cnt;

    public static void main(String[] args) throws IOException {
        initFI();
        StringBuilder sb = new StringBuilder();
        int T = nextInt();
        for(int test_case = 1; test_case<=T; test_case++){
            N = nextInt();
            cnt = 0;
            dfs(0,0,0,0);
            sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
    }
    static void dfs(int row, int downBits, int leftBits, int rightBits){        //각각 열, 아랫방향이동, 왼쪽 이동, 오른쪽 이동
        if(row == N){
            cnt++;
            return;
        }
        int bitMask = (~(downBits|leftBits|rightBits))&((1<<N)-1);    //배치 가능한 장소를 1로 하는 bitMask 만들기
        int colPos = (bitMask&-bitMask);            //1인 비트중 가장 오른쪽 비트 구하기
        ////debug
        // String debugStr = String.format("row: %d 배치 가능한 col: %05d pos: %05d",row,Integer.parseInt(Integer.toBinaryString(bit)), Integer.parseInt(Integer.toBinaryString(colPos)));
        // System.out.println(debugStr);

        while(bitMask>0){       //더이상 방문할 비트가 없을 경우 break;
            dfs(row+1,downBits|colPos,(leftBits|colPos)<<1,(rightBits|colPos)>>1);
            bitMask-=colPos;        //방문한 비트 제거
            colPos = (bitMask&-bitMask);    //방문할 비트 재지정
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