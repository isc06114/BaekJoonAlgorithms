import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {


    static long [][][] dp;
    static boolean isEmpty[][],isVisited[][];
    static int N;
    	public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        isEmpty = new boolean[N][N];
        isVisited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                isEmpty[i][j] = (nextInt() == 0)? true : false;
            }
        }
        dp = new long[N][N][3];
        dp[0][1][0] = 0;
        dp[0][1][1] = 0;
        dp[0][1][2] = 1;
        for(int r =0; r<N; r++){
            for(int c = 0; c<N; c++){
                bfs(r,c,0);
                bfs(r,c,1);
                bfs(r,c,2);
            }
        }

        System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
        // for(int r =0; r<N; r++){
        //     for(int c = 0; c<N; c++){
        //         System.out.print(dp[r][c][2]+ " ");
        //     }
        //     System.out.println();
        // }
    }
    public static void bfs(int r,int c, int s){
        switch (s) {
            case 0:
                if(r+1<N&&isEmpty[r+1][c]){
                    dp[r+1][c][0]+=dp[r][c][0];
                    if(c+1<N&&isEmpty[r+1][c+1]&&isEmpty[r][c+1])
                        dp[r+1][c+1][1]+=dp[r][c][0];
                }
                break;
            case 1:
                if(r+1<N&&isEmpty[r+1][c])
                    dp[r+1][c][0]+=dp[r][c][1];
                
                if(c+1<N&&isEmpty[r][c+1])
                    dp[r][c+1][2]+=dp[r][c][1];
                
                if(c+1<N&&r+1<N&&isEmpty[r+1][c+1]&&isEmpty[r][c+1]&&isEmpty[r+1][c])
                    dp[r+1][c+1][1]+=dp[r][c][1];
                break;
            case 2:
                if(c+1<N&&isEmpty[r][c+1]){
                dp[r][c+1][2]+=dp[r][c][2];
                    if(r+1<N&& isEmpty[r+1][c+1]&&isEmpty[r+1][c])
                        dp[r+1][c+1][1]+=dp[r][c][2];
                }
                break;
            default:
                break;
        }
    
    }

    //Fast IO
    private static final int MAX_BUFFER_SIZE = 1<<11; 
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