import java.util.*;

import java.io.*;
public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int finder, runner;
	static int MAX_LENGTH = 500_001;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		finder = Integer.parseInt(st.nextToken());
		runner = Integer.parseInt(st.nextToken());
		boolean[] map = new boolean[MAX_LENGTH];
		
		int count = 0;
		map[finder] = true;
		while(runner<MAX_LENGTH) {
//			System.out.println(runner);
			if(map[runner]) {
				System.out.println(count);
				return;
			}
			map = updateFinder(map);
			count++;
			runner+=count;
		}
		System.out.println(-1);
	}
	
	public static boolean[] updateFinder(boolean[] map) {
		boolean[] f_map = new boolean[map.length];
		for(int i =0; i<MAX_LENGTH; i++) {
			if(map[i]) {
				if(i+1<MAX_LENGTH)
					f_map[i+1] = true;
				
				if(i-1>=0)
					f_map[i-1] = true;
				
				if(i*2<MAX_LENGTH)
					f_map[i*2] = true;
			}
		}
		return f_map;
	}
	

	

}