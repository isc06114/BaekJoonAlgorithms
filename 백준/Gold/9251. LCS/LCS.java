import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	static char[] string1= new char[1001],string2 = new char[1001];
	static int str1Length,str2Length;
	static Node dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		str1Length = str1.length();
		for(int i =1; i<=str1Length; i++){
			string1[i] = str1.charAt(i-1);
		}
		String str2 = br.readLine();
		str2Length = str2.length();
		for(int i =1; i<=str2Length; i++){
			string2[i] = str2.charAt(i-1);
		}
		if(str1Length ==0 ||str2Length == 0){
			System.out.println(0);
			return;
		}
		dp = new Node[str1Length+1][str2Length+1];
		for(int i = 0; i<=str1Length;i++)
			dp[i][0] = new Node();
		for(int i = 1; i<=str2Length;i++)
			dp[0][i] = new Node();
		if(string1[1]==string2[1]) dp[1][1] = new Node(1,1,1);
		else dp[1][1] = new Node();
		System.out.println(dp(str1Length,str2Length).length);
		//debug();
	}
	static Node dp(int a, int b){
		if(dp[a][b] != null) return dp[a][b];
		Node temp;
		if(dp(a,b-1).compareTo(dp(a-1,b))==0&&dp[a][b-1].start1!=dp[a-1][b].start1&&dp[a][b-1].start2!=dp[a-1][b].start2){
			temp = check(dp[a][b-1],a,b,1);
			temp = temp.compareNode(check(dp[a-1][b],a,b,-1));
		}
		else{
			temp = dp[a][b-1].compareNode(dp[a-1][b]);
			temp = check(temp,a,b,dp[a][b-1].compareTo(dp[a-1][b]));
		}
		return (dp[a][b] = temp);
	}

	static Node check(Node temp,int end1, int end2, int compare){

		if(compare>0){
			for(int i = temp.start1+1; i<=end1; i++){
				if(string2[end2] == string1[i]){
					return new Node(i,end2,temp.length+1);
				}
			}
		}
		else{
			for(int i = temp.start2+1; i<=end2; i++){
				if(string1[end1] == string2[i]){
					return new Node(end1,i,temp.length+1);
				}
			}
		}
		return new Node(temp);
	}

	static void debug(){
		System.out.println("Debug");
		for(int i =0; i<=str1Length; i++){
			for(int j =0; j<=str2Length; j++){
				System.out.print(dp[i][j]+"         ");
			}
			System.out.println();
		}
	}

	static class Node implements Comparable<Node>{
		int start1,start2,length;
		Node(int start1,int start2, int length){
			this.start1 = start1;
			this.start2 = start2;
			this.length = length;
		}
		Node(){
			this(0,0,0);
		}
		Node(Node node){
			this(node.start1,node.start2,node.length);
		}
		public Node compareNode(Node o){
			return compareTo(o)>0?this:o;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.length,o.length);
		}
		@Override
		public String toString(){
			return "start1: "+start1+", start2: "+start2+", length: "+length+"";
		}
	}
}