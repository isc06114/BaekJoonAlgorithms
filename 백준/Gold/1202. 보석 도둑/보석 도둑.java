import java.io.DataInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {


    static int N, M;
    static StringBuilder sb;
    static int[][] dp;
    
	public static void main(String[] args) throws IOException {
		initFI();
        sb = new StringBuilder();
        N = nextInt(); M = nextInt();
        PriorityQueue<int[]> jewels = new PriorityQueue<>((o1,o2)-> Integer.compare(o1[0],o2[0]));
        PriorityQueue<int[]>leftJewels = new PriorityQueue<>((o1,o2)-> Integer.compare(o2[1], o1[1]));
        PriorityQueue<Integer> packs = new PriorityQueue<>();
        for(int i =0;i<N;i++){
            jewels.add(new int[]{nextInt(),nextInt()});
        }
        for(int i =0; i<M; i++) packs.add(nextInt());

        long value=0;
        int pack = -1;
        while(!packs.isEmpty()){
            while (!packs.isEmpty()&&(jewels.isEmpty()||pack<jewels.peek()[0])) {
                if(!leftJewels.isEmpty()) {
                    int[] jewel = leftJewels.poll();
                    //System.out.println("pack:"+ pack+"   jewel:"+jewel[0]+" "+jewel[1]);
                    value+= jewel[1];
                }
                pack = packs.poll();
                //System.out.println(pack);
            }
            while (!jewels.isEmpty()&&pack>=jewels.peek()[0]) {
                leftJewels.add(jewels.poll());
            }
            
        }
        if(!leftJewels.isEmpty()) {
            value+=leftJewels.peek()[1];
        }
        System.out.println(value);
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