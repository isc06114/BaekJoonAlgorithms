import java.io.DataInputStream;
import java.io.IOException;

public class Main {

	static int R, C, Rotate, minL, maxArrayLength;
    static int[][] arr;
    static boolean isBH;
	public static void main(String[] args) throws IOException {
		initFI();
        StringBuilder sb = new StringBuilder();
        //N, M, R 입력
        R = nextInt(); C = nextInt(); Rotate = nextInt();
        //Array 입력
        if(R>C){
            arr=new int[R][R];
            maxArrayLength=R;
			minL = (R-C)/2;
            isBH = true;
        }else{
            arr = new int [C][C];
            maxArrayLength=C;
            minL = (C-R)/2;
        }

        if(isBH){
            for(int row = 0; row < maxArrayLength; row++){
				 for(int col = minL; col < maxArrayLength-minL; col++){
                    arr[row][col] = nextInt();
				}
			}
		}
        else{
			for(int row = minL; row < maxArrayLength-minL; row++){
				 for(int col = 0; col < maxArrayLength; col++){
						arr[row][col] = nextInt();
				 }
			}
		}
        //debug();
        //연산입력받기
        getRotate();
        //출력
        if(isBH)
            for(int row = 0; row < maxArrayLength; row++){
                for(int col = minL; col < maxArrayLength-minL; col++){
                    sb.append(arr[row][col]).append(" ");
                }
                sb.append('\n');
            }
        else
            for(int row = minL; row < maxArrayLength-minL; row++){
                for(int col = 0; col < maxArrayLength; col++){
                    sb.append(arr[row][col]).append(" ");
                }
                sb.append('\n');
        }
        System.out.println(sb);
	}




    static void getRotate()throws IOException{
        for(int i = 0; i<Rotate; i++){
            int cmd = nextInt();
            switch (cmd) {
                case 1:
                    rotate2();
                    rotate3();
                    rotate3();
                    break;
                case 2:
                    rotate2();
                    break;
                case 3:
                    rotate3();
                    break;
                case 4:
                    rotate3();
                    rotate3();
                    rotate3();
                    break;
                case 5:
                    rotate6(3);
                    break;
                case 6:
                    rotate6(1);
                    break;
                default:
                    break;
            }
        }
    }
    
    static void rotate2(){
        int temp;
        for(int time = 0; time < maxArrayLength/2; time++){
            for(int row = 0; row < maxArrayLength; row++){
                temp = arr[row][time];
                arr[row][time] = arr[row][maxArrayLength-1-time];
                arr[row][maxArrayLength-1-time] = temp;
            }
        }
    }

    static void rotate3(){
        isBH = !isBH;
        for(int row= 0;row<maxArrayLength/2;row++){
            for(int col=0; col<maxArrayLength/2;col++){
                int temp = arr[row][col];
                int f_row = row; int f_col = col;
                for(int i =0; i<3;i++){
                    arr[f_row][f_col] = arr[maxArrayLength-1-f_col][f_row];
                    int temp1= f_row;
                    f_row = maxArrayLength-1-f_col;
                    f_col = temp1;
                }
                arr[f_row][f_col] = temp;
            }
        }
    }

    static void rotate6(int cnt){
        int[][] temp;
        int[][] f_arr;
        if(isBH){
			f_arr = new int[maxArrayLength][maxArrayLength-2*minL];
			temp = new int[maxArrayLength/2][(maxArrayLength-2*minL)/2];
            for(int row = 0; row < maxArrayLength; row++) for(int col = minL; col < (maxArrayLength-minL); col++)
                f_arr[row][col-minL] = arr[row][col];
			
			for(int i = 0; i< cnt; i++){
				for(int row = 0; row<maxArrayLength/2;row++){
					for(int col =0; col<(maxArrayLength-2*minL)/2; col++){
						temp[row][col] = f_arr[row][col];
						f_arr[row][col] = f_arr[row][(maxArrayLength-2*minL)/2+col];
						f_arr[row][(maxArrayLength-2*minL)/2+col] = f_arr[maxArrayLength/2+row][(maxArrayLength-2*minL)/2+col];
						f_arr[maxArrayLength/2+row][(maxArrayLength-2*minL)/2+col] = f_arr[maxArrayLength/2+row][col];
						f_arr[maxArrayLength/2+row][col]=temp[row][col];
					}
				}
			}


        }else{
			f_arr = new int[maxArrayLength-2*minL][maxArrayLength];
			temp = new int[(maxArrayLength-2*minL)/2][maxArrayLength/2];
            for(int row = minL; row < maxArrayLength-minL; row++) for(int col = 0; col < maxArrayLength; col++)
                f_arr[row-minL][col] = arr[row][col];

			for(int i = 0; i< cnt; i++){
				for(int row = 0; row<(maxArrayLength-2*minL)/2;row++){
					for(int col =0; col<maxArrayLength/2; col++){
						temp[row][col] = f_arr[row][col];
						f_arr[row][col] = f_arr[row][maxArrayLength/2+col];
						f_arr[row][maxArrayLength/2+col] = f_arr[(maxArrayLength-2*minL)/2+row][maxArrayLength/2+col];
						f_arr[(maxArrayLength-2*minL)/2+row][maxArrayLength/2+col] = f_arr[(maxArrayLength-2*minL)/2+row][col];
						f_arr[(maxArrayLength-2*minL)/2+row][col]=temp[row][col];
					}
				}
			}

        }

        if(isBH){
            for(int row = 0; row < maxArrayLength; row++) for(int col = minL; col < maxArrayLength-minL; col++)
                arr[row][col] = f_arr[row][col-minL];
        }else{
            for(int row = minL; row < maxArrayLength-minL; row++) for(int col = 0; col < maxArrayLength; col++)
                arr[row][col] = f_arr[row-minL][col];
        }

    }

    static void debug(){
        System.out.println("\ndebug");
        for(int row = 0; row < maxArrayLength; row++){
            for(int col = 0; col < maxArrayLength; col++){
                System.out.print(arr[row][col]+" ");
            }
            System.out.println();
        }
    }
    static void debug(int n){
        System.out.println("\n"+n+"번연산");
        for(int row = 0; row < maxArrayLength; row++){
            for(int col = 0; col < maxArrayLength; col++){
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