import java.io.DataInputStream;
import java.io.IOException;

//22,608kb  520ms
public class Main {

	static int R, C, Rotate, minL;
    static int[][] arr;
	public static void main(String[] args) throws IOException {
		initFI();
        StringBuilder sb = new StringBuilder();
        //N, M, R 입력
        R = nextInt(); C = nextInt(); Rotate = nextInt();
        //Array 입력
        arr = new int[R][C];
        for(int row = 0; row < R; row++)
            for(int col = 0; col < C; col++)
                arr[row][col] = nextInt();
        //함수 실행
        rotates(arr);
        //출력
        for(int row = 0; row < R; row++){
            for(int col = 0; col < C; col++){
                sb.append(arr[row][col]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
	}

    static void rotates(int[][]array){
        minL = Math.min(R,C)/2;
        for(int line = 0; line<minL;line++){
            rotateLine(line,array);
        }
    }

    static void rotateLine(int line, int[][]array){
        int top = line;
        int bottom = R-line-1;
        int left = line;
        int right = C-line-1;
        int arrSize = 2*(R+C-4*line-2);
        int[] arr = new int[arrSize];
        int cnt = 0;
        for(int col = left; col <= right-1; col++){ //top
            arr[cnt++] = array[top][col];
        }
        for(int row = top; row <= bottom-1; row++){ //right
            arr[cnt++] = array[row][right];
        }
        for(int col = right; col >=left+1; col--){ //bottom
            arr[cnt++] = array[bottom][col];
        }
        for(int row = bottom; row >=top+1; row--){ //left
            arr[cnt++] = array[row][left];
        }
        cnt=0;
        for(int col = left; col <= right-1; col++){ //top
            array[top][col] = arr[(Rotate+cnt++)%arrSize];
        }
        for(int row = top; row <= bottom-1; row++){ //right
            array[row][right] = arr[(Rotate+cnt++)%arrSize];
        }
        for(int col = right; col >=left+1; col--){ //bottom
            array[bottom][col] = arr[(Rotate+cnt++)%arrSize];
        }
        for(int row = bottom; row >=top+1; row--){ //left
            array[row][left] = arr[(Rotate+cnt++)%arrSize];
        }
        
    }

    static void debug(int[][] arr){
        System.out.println("\ndebug");
        for(int row = 0; row < R; row++){
            for(int col = 0; col < C; col++){
                System.out.print(arr[row][col]+" ");
            }
            System.out.println();
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