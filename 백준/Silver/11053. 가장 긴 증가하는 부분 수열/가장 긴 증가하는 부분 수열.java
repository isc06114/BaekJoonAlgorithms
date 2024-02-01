import java.io.DataInputStream;
import java.io.IOException;


public class Main {
    static int n, list[], maxLength[],cnt;

    public static void main(String[] args) throws Exception {
        initFI();
        n = nextInt();
        list = new int[n];
        maxLength = new int[n+1];
        for(int i=0; i<n; i++){
            list[i] = nextInt();
        }   
        sol();
        System.out.println(cnt);
    }

    static void sol(){
        cnt = 1;
        maxLength[1] = list[0];
        //debug();
        for(int i=1; i<n; i++){
            //System.out.println(list[i]+", "+maxLength[cnt]);
            if(list[i]>maxLength[cnt]) maxLength[++cnt] = list[i];
            else for(int j=cnt-1;j>=0;j--){ 
                if(list[i]>maxLength[j]){
                    maxLength[j+1] = list[i];
                    break;
                }
            }
            //debug();
        }
    }
    static void debug(){
        for(int i = 1; i<=cnt; i++){
            System.out.print(maxLength[i]+" ");
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