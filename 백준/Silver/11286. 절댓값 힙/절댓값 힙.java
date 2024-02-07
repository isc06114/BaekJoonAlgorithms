import java.io.DataInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        initFI();
        int T = nextInt();
        int cmd;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int test_case = 1; test_case <= T; test_case++){
            cmd = nextInt();
            if(cmd == 0){
                if(pq.isEmpty())
                    sb.append(0);
                else
                    sb.append(pq.poll().dat);
                sb.append('\n');
            }else{
                pq.add(new Node(cmd));
            }
        }
        System.out.println(sb);

    }

    static class Node implements Comparable<Node>{
        int dat;

        @Override
        public int compareTo(Node o) {
            return (Math.abs(this.dat)==Math.abs(o.dat))?Integer.compare(this.dat,o.dat):Integer.compare(Math.abs(this.dat),Math.abs(o.dat));
        }
        Node(int dat){
            this.dat = dat;
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