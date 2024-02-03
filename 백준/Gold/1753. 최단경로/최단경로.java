import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V,E,start;
	static Integer minDistance[];
	static ArrayList<int[]>[] dijk;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine())-1;
		minDistance = new Integer[V];
		dijk = new ArrayList[V];

		for(int i = 0; i<V; i++){
			dijk[i] = new ArrayList<>();
			minDistance[i]= Integer.MAX_VALUE;
		}

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			dijk[s].add(new int[]{e,w});
		}
		minDistance[start]=0;
		sol(start);

		StringBuilder sb = new StringBuilder();
		for(int i =0; i<V; i++){
			if(minDistance[i]==Integer.MAX_VALUE)sb.append("INF").append('\n');
			else sb.append(minDistance[i]).append('\n');
		}
		System.out.println(sb);
	}
	static void sol(int start) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2) -> (o1[1]<o2[1]?-1:1));

		queue.offer(new int[]{start,0});
		while(!queue.isEmpty()) {
			int[] q = queue.poll();
			for(int[] node:dijk[q[0]]){
				if(minDistance[node[0]]>node[1]+minDistance[q[0]]){
					minDistance[node[0]] = node[1]+minDistance[q[0]];
					queue.offer(new int[]{node[0],minDistance[node[0]]});
				}
			}
		}
	}


}