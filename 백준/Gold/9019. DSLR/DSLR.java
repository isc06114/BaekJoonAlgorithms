import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static StringTokenizer st;
    static int T,goal;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main user = new Main();
        T = Integer.parseInt(br.readLine());
        for(int i =0; i<T;i++){
            st = new StringTokenizer(br.readLine());
            int register = Integer.parseInt(st.nextToken());
            goal = Integer.parseInt(st.nextToken());
            user.TC(register);
        }
        System.out.println(sb);

    }

    String[] dp;
    char[] mapper = new char[]{'D','S','L','R'};
    void TC(int register){
        dp = new String[10000];
        dp[register] = "";
        Queue<dpTC> queue = new ArrayDeque<>();
        for(char c : mapper){
            queue.add(new dpTC(register,c));
        }
        
        while(dp[goal]==null&&!queue.isEmpty()){
            dpTC cmd = queue.poll();
            int newRegister = -1;
            switch (cmd.cmd) {
                case 'D':
                newRegister=cmdD(cmd.regist);
                    break;
                case 'S':
                newRegister=cmdS(cmd.regist);
                    break;
                case 'L':
                newRegister=cmdL(cmd.regist);
                    break;
                case 'R':
                newRegister=cmdR(cmd.regist);
                    break;
                default:
                    break;
            }
            if(dp[newRegister]==null){
                dp[newRegister] = dp[cmd.regist]+cmd.cmd;
                for(char c : mapper){
                    queue.add(new dpTC(newRegister,c));
                }
            }
        }
        sb.append(dp[goal]).append('\n');
    }

    int cmdL(int register){
        int temp = register/1000;
        int temp2 = register;
        temp2 -=temp*1000;
        temp2*=10;
        temp2+= temp;
        return temp2;
    }

    int cmdR(int register){
        int temp = register%10;
        int temp2 = register;
        temp2 /=10;
        temp2+= temp*1000;
        return temp2;
    }
    int cmdD(int register){
        return (register*2%10000);
    }

    int cmdS(int register){

        return ((register-1)==-1)?9999:(register-1);
    }

    class dpTC{
        int regist;
        char cmd;

        dpTC(int regist, char cmd){
            this.regist = regist;
            this.cmd = cmd;
        }
    }
}