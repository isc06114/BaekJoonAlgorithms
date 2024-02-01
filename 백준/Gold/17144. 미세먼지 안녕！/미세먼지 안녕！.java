import java.io.DataInputStream;
import java.io.IOException;


public class Main {
    static int R,C, g_map[][],g_nextMap[][],tFilter,bFilter;
    static int[] up={-1,0},down={1,0},right={0,1},left={0,-1}, mappers[] = {up,down,right,left};
    public static void main(String[] args) throws Exception {
        initFI();
        R = nextInt();
        C = nextInt();
        int T = nextInt();
        g_map = new int[R+2][C+2];
        g_nextMap = new int[R+2][C+2];
        for(int row = 0; row<=R+1; row++){
            g_map[row][0] = -1;
            g_nextMap[row][0] = -1;
            g_map[row][C+1] = -1;
            g_nextMap[row][C+1] = -1;
        }
        for(int col = 0; col<=C+1; col++){
            g_map[0][col] = -1;
            g_nextMap[0][col] = -1;
            g_map[R+1][col] = -1;
            g_nextMap[R+1][col] = -1;
        }
        
        for(int row=1; row<=R; row++){
            for(int col=1; col<=C; col++){
                g_map[row][col] = nextInt();
            }
        }   
        for(int i=1; i<=R; i++){
            if(g_map[i][1] == -1){
                tFilter = i;
                bFilter = i+1;
                break;
            }
        }
        g_nextMap[tFilter][1]= -1;
        g_nextMap[bFilter][1]= -1;

        //debug("", g_map);
        update(T);
        System.out.println(sum());
    }
    static int sum(){
        int sum = 0;
        for(int row = 1; row<=R; row++){
            for(int col = 1; col<=C; col++){
                if(g_nextMap[row][col]>=0)
                    sum+=g_map[row][col];
            }
        }
        return sum;
    }
    static void update(int t){
        for(int cnt =0; cnt<t;cnt++){
            for(int row=1; row<=R; row++){
                for(int col=1; col<=C; col++){
                    g_nextMap[row][col] = 0;
                }
            }
            for(int row=1; row<=R; row++){
                for(int col=1; col<=C; col++){
                    if(g_map[row][col]>=5) spreading(row,col);
                    else g_nextMap[row][col]+=g_map[row][col];
                }
            }
            //debug("spreaded:",g_nextMap);
            for(int i=1; i<=R; i++)
                g_map[i] = g_nextMap[i].clone();
            recursion();
            //debug("recursed:",g_nextMap);
            for(int i=1; i<=R; i++)
                g_map[i] = g_nextMap[i].clone();
        }
    }

    static void spreading(int row, int col){
        int move = g_map[row][col]/5;
        int moved = 0;
        for(int[] mapper : mappers){
            if(g_map[row+mapper[0]][col+mapper[1]]>=0){
                g_nextMap[row+mapper[0]][col+mapper[1]] += move;
                moved+=move;
            }
        }
        g_nextMap[row][col]+=g_map[row][col]-moved;
    }

    static void recursion(){
        g_nextMap[tFilter][2] = 0;
        g_nextMap[bFilter][2] = 0;
        for(int col = 3; col<=C;col++){     //Right
            g_nextMap[tFilter][col] = g_map[tFilter][col-1];
            g_nextMap[bFilter][col] = g_map[bFilter][col-1];
        }
        for(int row = 1; row<=tFilter-1; row++){        //Up
            g_nextMap[row][C] = g_map[row+1][C];
        }
        for(int row = bFilter+1; row<=R; row++){        //Down
            g_nextMap[row][C] = g_map[row-1][C];
        }
        for(int col = 1; col<=C-1;col++){                 //Left
            g_nextMap[1][col] = g_map[1][col+1];
            g_nextMap[R][col] = g_map[R][col+1];
        }
        for(int row = 2; row<=tFilter-1; row++){ 
            g_nextMap[row][1] = g_map[row-1][1];
        }
        for(int row = R-1; row>=bFilter+1; row--){
            g_nextMap[row][1] = g_map[row+1][1];
        }
    }

    static void debug(String str,int[][] arr){
        System.out.println(str);
        for(int row=0; row<=R+1; row++){
            for(int col=0; col<=C+1; col++){
                System.out.print(arr[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();

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
        if(neg) return -ret;
        return ret;
    }
    private static byte read() throws IOException{
        if(curIdx == maxIdx){
            maxIdx = inputStream.read(buffer,curIdx = 0, MAX_BUFFER_SIZE);
            if(maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
    

    

}