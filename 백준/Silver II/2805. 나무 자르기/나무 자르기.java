import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
// 24,224 kb    384 ms
public class Main {
    static int N, M, cnt, trees[];

    public static void main(String[] args) throws IOException {
        initFI();
        N = nextInt();
        M = nextInt();
        trees = new int[N];
        for(int i=0; i<N; i++){
            trees[i] = nextInt();
        }
        Arrays.sort(trees);
        int cnt = 0, temp,height;
        temp = 1_000_000_000;
        height = temp;
        boolean pass = false;
        while(temp>=1||!pass){
            cnt = 0;
            for(int i = N-1; i>=0; i--){
                if(height>=trees[i]) break;
                cnt= cnt+(trees[i]-height);
                if(cnt<0) {
                    cnt = Integer.MAX_VALUE;
                    break;
                }
            }
            //System.out.println("height:"+height+", temp:"+temp+", cnt: "+cnt);
            temp = temp/2;
            if(temp==0){
                if(cnt>M && !pass) break;
                temp=1;
            }
            if(cnt<M){
                height -= temp;
                pass = false;
            }
            else if(cnt>M){
                height+=temp;
                pass = true;
            }
            else break;

        }
        System.out.println(height);
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