import java.io.DataInputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		initFI();
		int fs = 1,fb = 0;
		int N = nextInt();
		int[] ss = new int[N], bs = new int[N];
		for(int i = 0; i < N; i++) {
			ss[i] = nextInt();
			bs[i] = nextInt();
		}
		int result = 100000000;

		for(int i = 1; i < 1<<N; i++) {
			fs = 1;
			fb = 0;
			for(int j = 0; j < N; j++) {
				if((i&1<<j) == 1<<j) {
					
					fs*=ss[j];
					fb+=bs[j];
				}
			}
			result = Math.min(Math.abs(fs-fb), result);
		}
		System.out.print(result);

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