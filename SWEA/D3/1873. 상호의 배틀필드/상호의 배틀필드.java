import java.io.*;
import java.util.*;

public class Solution {
    static final byte STEAL = 0, BRICK = 1;
    static final byte WATER = 2;
    static final byte BLOCKER = 3;
    static final byte PLAIN = 4;
    static final byte PLAYER=6;
    static final byte UP = 8, DOWN = 16, LEFT = 32, RIGHT = 64;
    static HashMap<Character,Byte> worldMap = new HashMap<>();
    static HashMap<Byte,Character> worldByte = new HashMap<>();
    static HashMap<Byte,Integer[]> moveMap = new HashMap<>();
    static{
        worldMap.put('.',PLAIN);
        worldMap.put('*',BRICK);
        worldMap.put('#',STEAL);
        worldMap.put('-',WATER);
        worldMap.put('^',UP);
        worldMap.put('v',DOWN);
        worldMap.put('<',LEFT);
        worldMap.put('>',RIGHT);
        worldByte.put(PLAIN,'.');
        worldByte.put(BRICK,'*');
        worldByte.put(STEAL,'#');
        worldByte.put(WATER,'-');
        worldByte.put(UP,'^');
        worldByte.put(DOWN,'v');
        worldByte.put(LEFT,'<');
        worldByte.put(RIGHT,'>');
        moveMap.put(UP,new Integer[]{-1,0});
        moveMap.put(DOWN,new Integer[]{1,0});
        moveMap.put(LEFT,new Integer[]{0,-1});
        moveMap.put(RIGHT,new Integer[]{0,1});

    }
    static byte[][] globalMap; 
    static BufferedReader br;
    static int Row,Col;
    static StringTokenizer st;
    static Tank tank;
    static Bullet bullet;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            gameStart();
            sb.append('#').append(test_case).append(' ');
            for(int r=1;r<=Row;r++){
                for(int c=1;c<=Col;c++){
                    sb.append(worldByte.get(globalMap[r][c]));
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
    static void gameStart()throws IOException{
        input();
        for(int i = 0; i < cmd.length(); i++){
            command(cmd.charAt(i));
        }
        globalMap[tank.row][tank.col] = tank.direct;
    }

    static String cmd;
    static void input()throws IOException{
        tank = new Tank();
        bullet = new Bullet();
        st = new StringTokenizer(br.readLine());
        Row = Integer.parseInt(st.nextToken());
        Col = Integer.parseInt(st.nextToken());
        globalMap = new byte[Row+2][Col+2];
        for(int r = 1; r <= Row; r++){
            String str = br.readLine();
            for(int c = 1; c <= Col; c++){
                globalMap[r][c] = worldMap.get(str.charAt(c-1));
                if(globalMap[r][c]>PLAYER) {
                    tank.setPosition(r, c);
                    globalMap[r][c] = PLAIN;
                }
            }
        }
        br.readLine();
        cmd = br.readLine();
        
    }

    static void command(char c){
        switch (c) {
            case 'S':
                tank.shoot();
                break;
            case 'U':
                tank.up();
                break;
            case 'R':
                tank.right();
                break;
            case 'L':
                tank.left();
                break;
            case 'D':
                tank.down();
                break;
            default:

                break;
        }
    }
    
    static class Bullet{
        int row,col;
        byte direct;
        void set(int row, int col, byte direct){
            this.row = row;
            this.col = col;
            this.direct = direct;
        }
        void move(){
            if(globalMap[row+moveMap.get(this.direct)[0]][col+moveMap.get(this.direct)[1]]>1){
                this.row+=moveMap.get(direct)[0];
                this.col+=moveMap.get(direct)[1];
                move();
                return;
            }
            if(globalMap[row+moveMap.get(direct)[0]][col+moveMap.get(direct)[1]]==BRICK){
                globalMap[row+moveMap.get(direct)[0]][col+moveMap.get(direct)[1]] = PLAIN;
            }
        }
    }

    static class Tank{
        int row,col;
        byte direct;
        void setPosition(int row, int col){
            this.row = row;
            this.col = col;
            this.direct = globalMap[row][col];
        }
        void shoot(){
            bullet.set(this.row,this.col,this.direct);
            bullet.move();
        }
        void left(){
            direct = LEFT;
            move();
        }
        void right(){
            direct = RIGHT;
            move();
        }
        void up(){
            direct = UP;
            move();
        }
        void down(){
            direct = DOWN;
            move();
        }
        void move(){
            if(globalMap[row+moveMap.get(direct)[0]][col+moveMap.get(direct)[1]]>3){
                row+=moveMap.get(direct)[0];
                col+=moveMap.get(direct)[1];
            }
        }
    }

}