import java.io.DataInputStream;
import java.io.IOException;

//11,468 kb 340 ms
public class Solution {
    static int T;
    static int[] computerSet = new int[9];
    static int player, computer, cnt,win,lose;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        initFI();
        sb = new StringBuilder();
        T = nextInt();
        for(int test_case = 1; test_case<=T; test_case++){
            sb.append('#').append(test_case).append(' ');
            TC();
        }
        System.out.println(sb);
    }
    static void TC() throws IOException{
        initInput();
        dfs(player,0,0,171);
        sb.append(win).append(' ').append(lose).append('\n');
    }

    static void initInput()throws IOException{
        cnt =0; win =0; lose = 0;
        computer = 0; 
        for(int i =0; i<9; i++){
            int b = nextInt();
            computer+= 1<<(b-1);
            computerSet[i] = b;
        }
        player = ((~computer)&((1<<18)-1));
    }

    static void dfs(int bitMask, int score, int cnt, int total){
        if(cnt==9){
            if(score>0)
                win++; 
            else if(score<0)
                lose++;
            return;
        }
        if(Math.abs(score)>total){
            int k = factorial(9-cnt);
            if(score>0)
                win+=k;
            else if(score<0)
                lose+=k;
            return;
        }
        int comb = bitMask;

        while(comb>0){
            int cardBit = (comb&-comb)&(1<<18)-1;
            int card = (int)(Math.log(cardBit)/Math.log(2))+1;
            //if(cnt == 0)System.out.println("cnt="+cnt+", comb="+comb+", cardBit="+cardBit+", card="+card+", computerCard="+computerSet[cnt]+", score:"+score);
            dfs(bitMask - cardBit,score+((card-computerSet[cnt])<0?card+computerSet[cnt]:-card-computerSet[cnt]),cnt+1,total-card-computerSet[cnt]);
            comb-=cardBit;
        }
    }

    static int factorial(int n){
        if(n ==1) return 1;
        return n*factorial(n-1);
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