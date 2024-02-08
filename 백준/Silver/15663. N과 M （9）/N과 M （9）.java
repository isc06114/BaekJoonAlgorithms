import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {


    static int N, M;
    static ArrayList<Integer> arr;
    static int[] visit;
    static Map<Integer,Integer> visited;
    static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		initFI();
        sb = new StringBuilder();
        N = nextInt(); M = nextInt();
        visited = new HashMap<Integer,Integer>();
        arr = new ArrayList<>();
        visit = new int[M];
        for(int i =0; i<N;i++){
            int x = nextInt();
            if(visited.containsKey(x)) visited.put(x,visited.get(x)+1);
            else {
                visited.put(x,1);
                arr.add(x);
            }
        }
        Collections.sort(arr);
        combination(0);
        System.out.println(sb);
	}

    static void combination(int cnt){
        if(cnt == M){
            for(int i =0; i<M; i++)
                sb.append(visit[i]).append(' ');
            sb.append('\n');
            return;
        }
        for(int n :arr){

            if(visited.get(n)==0) continue;
            visit[cnt] = n;
            visited.put(n,visited.get(n)-1);
            combination(cnt+1);
            visited.put(n,visited.get(n)+1)   ;        
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