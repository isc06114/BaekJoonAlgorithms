import java.io.DataInputStream;
import java.io.IOException;

//17,420kb  100ms
public class Main {

	static int R, C, Rotate, minL;
    static int[][] arr, nextarr;
	public static void main(String[] args) throws IOException {
		initFI();
        StringBuilder sb = new StringBuilder();
        R = nextInt(); C = nextInt(); Rotate = nextInt();
        arr = new int[R][C];
        nextarr = new int[R][C];
        for(int row = 0; row < R; row++){
            for(int col = 0; col < C; col++){
                arr[row][col] = nextInt();
            }
        }

        arr=rotates(Rotate,arr);

        for(int row = 0; row < R; row++){
            for(int col = 0; col < C; col++){
                sb.append(arr[row][col]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
	}

    static int[][] rotates(int n,int[][]array){
        minL = Math.min(R,C)/2;
        for(int i = 0; i<n;i++)
            array = rotateArray(array);
        return array;
    }

    static int[][] rotateArray(int[][]array){
        int[][]nextArray = new int[R][C];
        for(int line = 0; line < minL; line++){
            int top = line;
            int bottom = R-line-1;
            int left = line;
            int right = C-line-1;
            for(int col = left; col <= right-1; col++){ //top
                nextArray[top][col] = array[top][col+1];
            }
            for(int row = top+1; row <= bottom; row++){ //left
                nextArray[row][left] = array[row-1][left];
            }
            for(int col = left+1; col <= right; col++){ //bottom
                nextArray[bottom][col] = array[bottom][col-1];
            }
            for(int row = top; row <= bottom-1; row++){ //right
                nextArray[row][right] = array[row+1][right];
            }
        }
        //debug(nextArray);
        return nextArray;
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