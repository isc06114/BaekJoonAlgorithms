import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static int R, C;
    static boolean[][] d_map;
    static int[][] time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        d_map = new boolean[R+2][C+2];
        time = new int[R+2][C+2];
        for(int r = 1; r<=R; r++){
            String str = br.readLine();
            for(int c = 1; c<=C; c++){
                if(str.charAt(c-1)=='1')
                    d_map[r][c] = true;
            }
        }
        time[1][1] = 1;
        bfs(1,1);
        System.out.println(time[R][C]);
	}

    static int up[] = {-1,0}, down[] = {1,0}, right[] = {0,1}, left[] = {0,-1}, mappers[][] = {up,down,left,right};
    
    static void bfs(int row, int col){
        PriorityQueue<Point> pq = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.time,o2.time));
        pq.add(new Point(row, col, 1));
        while(!pq.isEmpty()){
            Point p = pq.poll();
            row = p.row;
            col = p.col;
            //System.out.println(row+" "+col);
            for(int[] mapper:mappers){
                int r = row+mapper[0];
                int c = col+mapper[1];
                if(d_map[r][c]&&(time[r][c]==0||time[row][col]>time[r][c]+1)) {
                    time[r][c]= time[row][col]+1;
                    pq.add(new Point(r, c, time[r][c]));
                }
            }

        }
    }


    static class Point{
        int row,col, time;
        public Point(int row, int col, int time){
            this.row= row;
            this.col=col;
            this.time =time;
        }
    }
}