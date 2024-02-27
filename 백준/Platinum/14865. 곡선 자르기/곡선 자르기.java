import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		initFI();
		int N = nextInt();
		LinkedList<Mountain> mountains = new LinkedList<>();
		
		boolean isUnder = true;
		int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE, tempStart = Integer.MAX_VALUE;
		int cnt=0;
		while(y>0) {
			x = nextInt();
			y = nextInt();
			cnt++;
			if(tempStart==Integer.MAX_VALUE) tempStart = x;
		}
		int start = x;
		int tempEnd = x;
		int end =Integer.MAX_VALUE;
		for(int i =0;i<N-cnt;i++) {
			x = nextInt();
			y = nextInt();
			if(isUnder) {
				if(y>0) {
					start = x;
					isUnder = false;
				}
			}
			else {
				if(y<0) { 
					end = x;
					isUnder = true;
					if(start>end)
						mountains.add(new Mountain(end,start));
					else
						mountains.add(new Mountain(start,end));
				}
			}
		}
		if(!isUnder) {
			if(start>tempEnd) mountains.add(new Mountain(tempEnd,start));
			else mountains.add(new Mountain(start,tempEnd));
		}
		else if(tempStart!=Integer.MAX_VALUE&&tempEnd!=tempStart){
			if(tempStart>tempEnd) mountains.add(new Mountain(tempEnd, tempStart));
			else mountains.add(new Mountain(tempStart, tempEnd));
		}

		Collections.sort(mountains);
		int maxRightX = Integer.MIN_VALUE;
		int containCount = 0,containedCount =0;
		int numOfMount= mountains.size();
		while(!mountains.isEmpty()) {
			Mountain m = mountains.removeFirst();
			// System.out.println("mountain:"+m.leftX+" "+m.rightX);
			maxRightX = Math.max(m.rightX, maxRightX);
			if(!mountains.isEmpty()&&m.rightX>mountains.peek().leftX) {
				containCount++;
			}
			if(m.rightX<maxRightX) containedCount++;
		}
//		System.out.println(numOfMount);
		System.out.println((numOfMount-containedCount)+" "+(numOfMount-containCount));
		
	}
	static class Mountain implements Comparable<Mountain>{
		int leftX, rightX;
		public Mountain(int leftX, int rightX) {
			this.leftX = leftX;
			this.rightX = rightX;
		}
		@Override
		public int compareTo(Mountain o) {
			return Integer.compare(this.leftX,o.leftX);
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