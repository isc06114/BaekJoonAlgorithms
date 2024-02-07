import java.io.DataInputStream;
import java.io.IOException;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        System.out.println(simulate());
    }

    static int count, visited[] = new int[15];
    static boolean board[][];
    static int simulate(){
        //if(N==1) return 1;
        //if(N==2||N==3) return 0;
        count = 0;
        board = new boolean[N][N];
        greedy(0);
        return count;
    }

    static void greedy(int n){
        if(n==N){
            ++count;
            return;
        }
        for(int i = 0; i<N; i++){
            if(board[n][i] == false){
                visited[n] = i;
                updateBoard(n);
                //debug(n);
                greedy(n+1);

            
            }
        }
    }

    static void updateBoard(int row){
        for(int f_row = 0; f_row <N; f_row++){
            for(int f_col = 0; f_col <N; f_col++){
                board[f_row][f_col] = false;
            }
        }
        for(int i = 0; i<= row; i++){
            updateBoard(i, visited[i]);
        }
    }

    static int mappers[][] = {{1,0},{1,1},{1,-1}};
    static void updateBoard(int row, int col){
        board[row][col] = true;
        for(int[] mapper:mappers){
            int f_row = row+mapper[0]; int f_col = col+mapper[1];
            while(!(f_row<0||f_row>=N||f_col<0||f_col>=N)){
                board[f_row][f_col] = true;
                f_row+=mapper[0];f_col+=mapper[1];
            }
        }
    }

    static void debug(int n){
        System.out.println("Debug "+n);
        for(int i =0; i<N;i++)
            System.out.print(visited[i]+" ");
        System.out.println();
        for(int row = 0; row<N;row++){
            for(int col =0; col<N; col++){
                System.out.print(board[row][col]?"O ":"X ");
            }
            System.out.println();
        }
        System.out.println();
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