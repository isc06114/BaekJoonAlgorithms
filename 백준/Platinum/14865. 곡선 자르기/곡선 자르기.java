import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Mountain> mountains = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.leftX,o2.leftX));
		
		boolean isUnder = true;
		StringTokenizer st;
		int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
		int cnt=0;
		while(y>0) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			cnt++;
		}
		int start = x;
		int tempEnd = x;
		int end =Integer.MAX_VALUE;
		for(int i =0;i<N-cnt;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
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
		
		int maxRightX = Integer.MIN_VALUE;
		int containCount = 0,containedCount =0;
		int numOfMount= mountains.size();
		while(!mountains.isEmpty()) {
			Mountain m = mountains.poll();
//			System.out.println("mountain:"+m.leftX+" "+m.rightX);
			maxRightX = Math.max(m.rightX, maxRightX);
			if(!mountains.isEmpty()&&m.rightX>mountains.peek().leftX) {
				containCount++;
			}
			if(m.rightX<maxRightX) containedCount++;
		}
//		System.out.println(numOfMount);
		System.out.println((numOfMount-containedCount)+" "+(numOfMount-containCount));
		
	}
	static class Mountain{
		int leftX, rightX;
		public Mountain(int leftX, int rightX) {
			this.leftX = leftX;
			this.rightX = rightX;
		}
	}
	
}