import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char DNA[]= new char[]{'A','C','G','T'},DNAString[];
	static int S,P,window[] = new int[5],neededDna[] = new int[4];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P= Integer.parseInt(st.nextToken());
		DNAString = new char[S];
		String str = br.readLine();
		char c;
		for(int i = 0; i<P; i++) {
			c = str.charAt(i);
			window[match(c)]++;
			DNAString[i] = c;
		}
		for(int i = P; i< S; i++) {
			DNAString[i] = str.charAt(i);
		}
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<4; i++) 
			neededDna[i] = Integer.parseInt(st.nextToken());
		int cnt=0;
		if(isPassed())cnt++;
		//debug();
		for(int i =0; i<S-P; i++){
			window[match(DNAString[i])]--;
			window[match(DNAString[P+i])]++;
			if(isPassed()) cnt++;
			//debug();
		}
		System.out.println(cnt);
		
	}
	static boolean isPassed() {
		if(window[0]<neededDna[0] ||window[1]<neededDna[1] ||
				window[2]<neededDna[2] ||window[3]<neededDna[3]) return false;
		return true;
	}
	
	static int match(char c) {
		switch (c) {
		case 'A': 	
			return 0;
		case 'C': 
			return 1;
		case 'G': 
			return 2;
		case 'T': 
			return 3;
		default:
			return 4;
		}
	}
	
	static void debug() {
		for(int i =0; i<4; i++) {
			System.out.print(window[i]+" ");
		}
		System.out.println();
	}
	

}