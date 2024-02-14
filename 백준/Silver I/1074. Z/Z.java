import java.io.DataInputStream;
import java.io.IOException;

public class Main {
    static int N, R, C, cnt;

    public static void main(String[] args) throws IOException {
        initFI();
        N = (int)Math.pow(2,nextInt());
        R = nextInt();
        C = nextInt();
        cnt = -1;
        divR(1,R+1,N);
        divC(1,C+1,N);
        System.out.println(cnt);
    }
    
    static void divR(int start, int index, int end){
        if(start+1==end){
            cnt+= (index==start?1:3);
            //System.out.println("cnt: "+cnt);
            return;
        }
        int mid = (start+end-1)/2;
        if(index>mid){
            cnt+=(mid-start+1)*(mid-start+1)*2;
            //System.out.println("cnt: "+cnt);
            divR(mid+1,index,end);
        }
        else divR(start,index,mid);
    }

    static void divC(int start, int index, int end){
        if(start+1==end){
            //System.out.println("cnt: "+cnt);
            cnt+= (index==start?0:1);
            return;
        }
        int mid = (start+end-1)/2;
        if(index>mid){
            cnt+=(mid-start+1)*(mid-start+1);
            //System.out.println("cnt: "+cnt);
            divC(mid+1,index,end);
        }
        else divC(start,index,mid);
    }


    	 //Fast IO
    private static final int MAX_BUFFER_SIZE = 17; 
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