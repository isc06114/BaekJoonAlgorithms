import java.util.*;

public class Main {


    private int [][][] _dp; //Third index is pipe`s state. [0] is horizen, [1] is vertical, [2] is cross. 
    private boolean[][] _isEmpty;
    private int _N;
    private List<int[]> _visited = new ArrayList<>();


    	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Main m = new Main(N);
        boolean[][] house = new boolean[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                house[i][j] = (sc.nextInt() == 0)? true : false;
        m.setHouse(house);
        m.start();
        //m.debugDp();
        System.out.print(m.getresult());
		sc.close();
	}

    public Main(int N){
        _N = N;
        _dp = new int[N][N][3];
        _dp[0][1][0] = 1;
        _dp[0][1][1] = 0;
        _dp[0][1][2] = 0;
        _visited.add(new int[]{0,1,0});
        _isEmpty = new boolean[N][N];
    }

    public void setHouse(boolean[][] house){_isEmpty = house.clone();}
    public void start(){
        for(int i = 0; i < _N; i++){
            for(int j = 0; j < _N; j++){
                if(!_isEmpty[i][j]){
                    _visited.add(new int[]{i,j,0});
                    _visited.add(new int[]{i,j,1});
                    _visited.add(new int[]{i,j,2});

                }
            }
        }
        if(_isEmpty[_N-1][_N-1]&&_isEmpty[0][1]&&_isEmpty[0][0])
            dfs(_N-1,_N-1);
    }

    public void dfs(int y,int x){
        _visited.add(new int[]{y,x});
        _dp[y][x][0] = dp(y,x-1,0) + dp(y,x-1,2);
        _dp[y][x][1] = dp(y-1,x,1) + dp(y-1,x,2);
        if(!_isEmpty[y-1][x]||!_isEmpty[y][x-1]||!_isEmpty[y-1][x-1]) _dp[y][x][2] = 0;
        else _dp[y][x][2] = dp(y-1,x-1,0) + dp(y-1,x-1,1) + dp(y-1,x-1,2);
    }

    public int dp(int y, int x, int s){
        if(x<0||x>=_N||y<0||y>=_N||!_isEmpty[y][x])  return 0;
        
        if(isVisited(y, x, s)) return _dp[y][x][s];
        switch(s){
            case 0:
                _dp[y][x][0] = dp(y,x-1,0) + dp(y,x-1,2);
                break;

            case 1:
                _dp[y][x][1] = dp(y-1,x,1) + dp(y-1,x,2);
                break;
            case 2:
                if(x-1<0||y-1<0||!_isEmpty[y-1][x]||!_isEmpty[y][x-1]||!_isEmpty[y-1][x-1]) _dp[y][x][s] = 0;
                else _dp[y][x][2] = dp(y-1,x-1,0) + dp(y-1,x-1,1) + dp(y-1,x-1,2);
                break;
        }
        _visited.add(new int[]{y,x,s});
        return _dp[y][x][s];
    }

    public int getresult(){   return _dp[_N-1][_N-1][0]+_dp[_N-1][_N-1][1]+_dp[_N-1][_N-1][2];}

    public boolean isVisited(int y, int x, int s){
        for(int[] visited : _visited)
            if(visited[0] == y && visited[1] == x && visited[2] == s)
                return true;
        return false;
    }
    
    public void debugDp(){
        for(int i = 0; i<_N; i++){
            for(int j = 0; j<_N; j++)
                System.out.printf("%d\t ",_dp[i][j][0]+_dp[i][j][1]+_dp[i][j][2]);
            System.err.println();
        }
    }
}
