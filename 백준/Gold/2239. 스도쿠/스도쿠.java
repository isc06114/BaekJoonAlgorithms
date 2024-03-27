
import java.util.*;
import java.io.*;

public class Main {
	
	static int[][]map;
	static int[][] zeroPoses;
	public static void main(String[] args) throws IOException {
		initFI();
		map = new int[9][9];
		ArrayList<int[]> list = new ArrayList<>();
		
		for(int r =0;r<9;r++) {
			for(int c =0;c<9;c++) {
				int temp= nextInt();
				if(temp>0) {
					map[r][c]=(1<<(temp-1));
				}else {
					list.add(new int[] {r,c});
				}
			}
		}
		zeroPoses= new int[list.size()][2];
		for(int i =0; i<list.size();i++) {
			zeroPoses[i]=list.get(i);
		}
		
		dfs(0);
		
		StringBuilder sb =new StringBuilder();
		for(int r =0;r<9;r++) {
			for(int c =0;c<9;c++) {
				if(map[r][c]==0) {
					sb.append(0);
				}else {
					sb.append((int)(Math.log(map[r][c]) / Math.log(2))+1);
				}
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	static boolean dfs(int cnt) {
		if(cnt==zeroPoses.length) return true;
		int temp = check(zeroPoses[cnt][0],zeroPoses[cnt][1]);
		while(temp!=0) {
			int bit = (temp&(-temp));
			map[zeroPoses[cnt][0]][zeroPoses[cnt][1]]=bit;
			if(dfs(cnt+1)) {
				return true;
			}
			map[zeroPoses[cnt][0]][zeroPoses[cnt][1]]=0;
			temp-=bit;
		}
		return false;
	}
	
	static int check(int row, int col) {
		int bit=0;
		for(int i=0; i<9;i++) {	//rowCheck
			bit|=map[row][i];
			bit|= map[i][col];
		}
		int rStart=row-row%3;
		int cStart=col-col%3;
		for(int r=0;r<3;r++) {
			for(int c = 0; c<3;c++) {
				bit|=map[rStart+r][cStart+c];
			}
		}
		int temp = ((~bit)&((1<<9)-1));
		return temp;
	}
	static void debug() {
		for(int r =0;r<9;r++) {
			for(int c =0;c<9;c++) {
				if(map[r][c]==0) {
					System.out.print(0);
				}else {
					System.out.print((int)(Math.log(map[r][c]) / Math.log(2))+1);
				}
			}
			System.out.println();
		}
		
	}
	
	 //Fast IO
	 private static final int MAX_BUFFER_SIZE = 90; 
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
	     return (int) c-'0';
	 }
	 
	 private static byte read() throws IOException{
	     if(curIdx == maxIdx){
	         maxIdx = inputStream.read(buffer,curIdx = 0, MAX_BUFFER_SIZE);
	         if(maxIdx == -1) buffer[0] = -1;
	     }
	     return buffer[curIdx++];
	 }
}