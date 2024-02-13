import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;



public class Main {
	//mapper
	final static int up[]= {-1,0},down[]= {1,0},right[]= {0,1},left[]= {0,-1},mappers[][] = new int[][] {up,down,right,left};
	final static int INIT = 1_000_003;
	static int R,C,dp[][][];
	static boolean g_map[][];
	
	public static void main(String[] args) throws IOException {
		initFI();
		StringBuilder sb = new StringBuilder();
		R = nextint();
		C = nextint();
		g_map = new boolean[R][C];
		dp = new int[R][C][2];
		for(int r =0; r<R;r++){
			for(int c = 0; c < C; c++){
				dp[r][c][0] = Integer.MAX_VALUE;
			}
		}
		
		for(int row = 0; row<R;row++) for(int col = 0; col<C;col++) 	
				if(nextChar()=='0')
					g_map[row][col] = true; 
		for(int row = 0; row<R;row++) for(int col = 0; col<C;col++) 	
				if(g_map[row][col])
					dp[row][col][0] = INIT;
		dp[0][0][0] = 1;
		move(0,0);
		System.out.println(dp[R-1][C-1][0]==INIT?-1:dp[R-1][C-1][0]);
	}

	static void move(int startR,int startC){
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{startR,startC});
		while(!q.isEmpty()){
			int r = q.peek()[0];
			int c = q.peek()[1];
			q.poll();
			for(int[] mapper : mappers){
				int row = r+mapper[0];
				int col = c+mapper[1];
				if(row<0||row>=R||col<0||col>=C) continue;

				if(g_map[row][col]&&(dp[row][col][0]>dp[r][c][0]+1||(dp[row][col][1]==1&&dp[r][c][1]==0))){
					if(row==R-1&&col == C-1&& dp[row][col][0]<dp[r][c][0]+1) break;
					dp[row][col][0] = dp[r][c][0]+1;
					dp[row][col][1] = dp[r][c][1];
					q.add(new int[]{row,col});
					//debug();
				}
				else if(!g_map[row][col]&&dp[r][c][1] == 0&& dp[row][col][0]>dp[r][c][0]+1){
					dp[row][col][1] =1;
					dp[row][col][0] = dp[r][c][0]+1;
					q.add(new int[]{row,col});
					//debug();
				}
			}
		}
	}

	
	static void debug(){
		System.out.println("debug");
		for(int r =0; r<R;r++){
			for(int c = 0; c < C; c++){
				System.out.print(dp[r][c][0]==Integer.MAX_VALUE?'X'+" ":dp[r][c][0]==INIT?0+" ":dp[r][c][0]+" ");
			}
			System.out.print("   ");
			for(int c = 0; c < C; c++){
				System.out.print(+dp[r][c][1]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	 //Fast IO
    private static final int MAX_BUFFER_SIZE = 1<<21; 
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    private static void initFI(){
        inputStream = new DataInputStream(System.in);
        buffer = new byte[MAX_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    private static int nextint() throws IOException{
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