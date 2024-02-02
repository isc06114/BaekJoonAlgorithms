import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Main {
	//mapper
	final static int up[]= {-1,0},down[]= {1,0},right[]= {0,1},left[]= {0,-1},mappers[][] = new int[][] {up,down,right,left};
	
	static int R,C,cnt,roomSize[],roomCnt,mapSize[][];
	static boolean g_map[][];
	
	public static void main(String[] args) throws IOException {
		initFI();
		StringBuilder sb = new StringBuilder();
		R = nextint();
		C = nextint();
		roomSize = new int[(R+1)*(C+1)/2+1];
		roomCnt = 0;
		g_map = new boolean[R+2][C+2];
		mapSize = new int[R+2][C+2];
		
		
		for(int row = 1; row<=R;row++) for(int col = 1; col<=C;col++) 	
				if(nextChar()=='0') g_map[row][col] = true; 
		
		for(int row = 1; row<=R;row++) for(int col = 1; col<=C;col++) 
				if(mapSize[row][col] == 0&&g_map[row][col]) {
					roomCnt++;
					calMapSize(row,col);
				}

		for(int row = 1; row<=R;row++) {
			for(int col = 1; col<=C;col++) {
				sb.append(g_map[row][col]?"0":breakWall(row,col));
			}
			sb.append('\n');
		}
		System.out.println(sb);
		

	}
	static void calMapSize(int row, int col) {
		mapSize[row][col] = roomCnt;
		roomSize[roomCnt]++;
		for(int[] mapper : mappers) {
			if(g_map[row+mapper[0]][col+mapper[1]]&& mapSize[row+mapper[0]][col+mapper[1]] == 0)
				calMapSize(row+mapper[0], col+mapper[1]);
		}
	}
	
	static int breakWall(int row, int col) {
		int sum = 1;
		Set<Integer> set = new HashSet<Integer>();
		for(int[] mapper : mappers) {
			int index = mapSize[row+mapper[0]][col+mapper[1]];
			set.add(index);
		}
		for(int i :set) {
			sum+=roomSize[i];
		}
		
		return sum%10;
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