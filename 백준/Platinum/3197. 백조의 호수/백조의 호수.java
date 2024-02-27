import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static int[] first, second;
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		Queue<int[]> queue= new LinkedList<>();
		Queue<int[]> tempQueue = new LinkedList<>();
		first = new int[]{-1,-1}; second = new int[]{-1,-1};
		for(int r = 0; r<R; r++) {
			String s = br.readLine();
			for(int c = 0; c<C; c++) {
				char ch = s.charAt(c);
				if(ch == '.') {
					map[r][c] = true;
					queue.add(new int[]{r,c});
				}
				else if(ch == 'L') {
					if(first[0]!=-1) {
						second[0] = r;
						second[1] = c;
					}
					else {
						first[0]=r;
						first[1]=c;
					}
					queue.add(new int[]{r,c});
					map[r][c] = true;
				}
			}
			
		}
		int cnt=0;
		visited = new boolean[R][C];
		Queue<int[]> findPathQue1 = new LinkedList<>();
		findPathQue1.add(first);
        Queue<int[]> findPathQue2 = new LinkedList<>();
		Queue<int[]> findPathT1= findPathQue1;
		Queue<int[]> findPathT2= findPathQue2;
		while(!canMove(findPathT1,findPathT2)){
			cnt++;
			if(cnt%2==0){
				update(tempQueue,queue);
				findPathT2 = findPathQue2;
				findPathT1 = findPathQue1;
			}
			else {
				update(queue,tempQueue);
				findPathT2 = findPathQue1;
				findPathT1 = findPathQue2;
			}
			//debug();
			//System.out.println(findPathT1.size());
		}
		System.out.println(cnt);
		
	
	}
	static void update(Queue<int[]> que, Queue<int[]>tempQue){
		while(!que.isEmpty()){
			int[] q = que.poll();
			_update(q[0],q[1], tempQue);
		}
	}

	static int[] up= {-1,0}, down = {1,0}, left = {0,-1}, right = {0,1}, mappers[] = {up,down,left,right};
	static void _update(int r, int c, Queue<int[]>tempQue) {
		for(int[] mapper:mappers){
			int rr = r+mapper[0];
			int cc = c+mapper[1];
			if(rr<0||cc<0||rr>=R||cc>=C)continue;
			if(!map[rr][cc]){
				map[rr][cc] = true;
				tempQue.add(new int[]{rr,cc});
			}
		}
	}
	static boolean[][] visited;
	static boolean canMove(Queue<int[]>Que,Queue<int[]>tempQue){
		while(!Que.isEmpty()){
			int[] t= Que.poll();
			dfs(t[0],t[1],Que,tempQue);
		}
		return visited[second[0]][second[1]];
	}
	
	static void dfs(int r,int c,Queue<int[]>que,Queue<int[]>tempQue){
        boolean check = false;
		if(r==second[0]&&c==second[1]) return;
		for(int[] mapper:mappers){
			int rr = r+mapper[0];
			int cc = c+mapper[1];
			if(rr<0||cc<0||rr>=R||cc>=C)continue;
			if(!visited[rr][cc]&&map[rr][cc]){
                visited[rr][cc] =  true;
				que.offer(new int[]{rr,cc});
			}
			else if(!map[rr][cc]){
				check = true;
			}
		}
        if(check) tempQue.offer(new int[]{r,c});
    }
	static void debug(){
		System.out.println("map             visit");
		for(int r = 0; r<R; r++){
			for(int c = 0; c<C; c++){
				System.out.print(map[r][c]?'O':'X');
				
			}
			System.out.print("-");
			for(int c = 0; c<C; c++){
				System.out.print(visited[r][c]?'O':'X');
				
			}
			System.out.println();
		}
	}

	
	
}