import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static StringTokenizer st;
    static int R,C, mapNum[];
    static int map[][];
    static int piece;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R+2][C+2];
        for(int r = 1; r <= R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        check = true;
        int updatedPiece = 0, timer = 1;
        while(check){
            check = false;
            piece = 0;
            timer--;
            melt(timer,0,0);
            //debug();
            if(!check) break;
            updatedPiece = piece;
        }
        System.out.println(-timer);
        System.out.println(updatedPiece);
    }


    static int[] up = {-1,0},down = {1,0}, left = {0,-1}, right={0,1},mappers[] = {up,down,left,right};
    static void melt(int time,int r,int c){
        for(int[] mapper : mappers){
            int row = r+mapper[0];
            int col = c+mapper[1];
            if(row<0||col<0||row>R+1||col>C+1) continue;
            if(map[row][col]>=time){
                if(map[row][col]<=0){
                    map[row][col] = time-1;
                    melt(time,row,col);
                }
                else{
                    map[row][col] = time-1;
                    piece++;
                    check = true;
                }
            }
        }
    }

    static void debug(){
        System.out.println("debug");
        for(int r = 1; r<=R; r++){
            for(int c = 1; c<=C; c++){
                System.out.print(map[r][c]+"\t");
            }
            System.out.println();
        }
    }

}