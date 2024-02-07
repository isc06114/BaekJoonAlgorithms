import java.io.DataInputStream;
import java.io.IOException;

//17,420kb  100ms
public class Main {

	static int up[] = {-1,0},down[]={1,0},right[]={0,1},left[] = {0,-1}, mappers[][] = {up,down,right,left};
    static int[][] bitMap, path;
    static int R,C,maxInt = 1;
	public static void main(String[] args) throws IOException {
		initFI();
        R = nextInt();
        C = nextInt();
        bitMap = new int[R+2][C+2];
        path = new int[R+2][C+2];
        for(int row = 1; row<=R; row++){
            for(int col = 1; col<=C; col++){
                bitMap[row][col] = bitmask(nextChar());
            }
        }
        path[1][1] = bitMap[1][1];
        findPath(new int[]{1,1});
        System.out.println(maxInt);

	}
    static void findPath(int[]node){
        for(int[]mapper : mappers){
            int row = node[0]+mapper[0];
            int col = node[1]+mapper[1];
            if(bitMap[row][col]!=0&&!isContains(path[node[0]][node[1]],bitMap[row][col])){
                //System.out.print("bitMap: "+bitMap[row][col]+", myPath: "+path[node[0]][node[1]]+"\n");
                
                //debug();
                path[row][col]=checkBit(path[node[0]][node[1]],bitMap[row][col]);
                maxInt = Math.max(maxInt,numberOfBits(path[row][col]));
                findPath(new int[]{row,col});
            }
        }
    }
    
    static void debug(){
        System.out.println("path");
        for(int row = 1; row<=R; row++){
            for(int col = 1; col<=C; col++){
                System.out.print(path[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println("bitMap");
        for(int row = 1; row<=R; row++){
            for(int col = 1; col<=C; col++){
                System.out.print(bitMap[row][col]+" ");
            }
            System.out.println();
        }
    }

    static boolean isContains(int bit1,int bit2){
        return ((bit1&bit2) == bit2);
    }
    
    static int checkBit(int bit1, int bit2){
        return (bit1|bit2);
    }

    static int bitmask(char c){
        return 1<<charToInt(c);
    }

    static int charToInt(char c){
        return c-'A'+1;
    }

    static int numberOfBits(int n){
        int cnt = 0;
        for(int i = 1; i<=26;i++){
            if((n&(1<<i))==(1<<i))
                cnt++;
        }
        return cnt;
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