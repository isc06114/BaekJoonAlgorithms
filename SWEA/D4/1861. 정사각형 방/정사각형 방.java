import java.io.DataInputStream;
import java.io.IOException;

public class Solution {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        initFI();
        sb = new StringBuilder();
        int T = nextInt();
        for(int test_case = 1; test_case <= T; test_case++){
            sb.append('#').append(test_case).append(' ');
            TC();
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int N,w[][], MAX_SIZE,cnt,lineNum,dp[];
    static Node[] nodeList;
    static void TC()throws IOException{
        N = nextInt();
        w = new int[N+2][N+2];
        MAX_SIZE = (int)Math.pow(N,2)+1;
        dp = new int[MAX_SIZE];
        nodeList = new Node[MAX_SIZE];
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                int d = nextInt();
                w[r][c] = d;
                nodeList[d] = new Node(r, c);
            }
        }
        cnt = 1;
        while(cnt<MAX_SIZE){
            lineNum=1;
            mapping();
            ++cnt;
        }
        int index = 1;
        int maxline = 1;
        for(int i=1;i<MAX_SIZE;i++){
            if(maxline<dp[i]){
                maxline=dp[i];
                index = i-maxline+1;
            }
        }
        sb.append(index).append(' ').append(maxline);
    }
    static int[][] mappers = {{1,0},{0,1},{-1,0},{0,-1}};
    static void mapping(){
        dp[cnt]=lineNum;
        for(int[] mapper : mappers){
            if(w[mapper[0]+nodeList[cnt].row][mapper[1]+nodeList[cnt].col]==cnt+1){
                cnt++;
                lineNum++;
                mapping();
            }
        }

    }

    static class Node{
        int row,col;
        public Node(int row, int col){
            this.row = row;
            this.col = col;
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

    private static byte read() throws IOException{
        if(curIdx == maxIdx){
            maxIdx = inputStream.read(buffer,curIdx = 0, MAX_BUFFER_SIZE);
            if(maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}