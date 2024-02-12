import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, digit, ans;
    static int canUse[], tempDigits[] = new int[7], digits[]= new int[7];
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[] brokenButton = new int[M]; 
        canUse = new int[10-M];
        ans =Math.abs(N-100);
        if(M!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());    
            for(int i = 0; i< M; i++){
                brokenButton[i] = Integer.parseInt(st.nextToken());
            }
        }
        if(M == 10||N==100) {
            System.out.println(Math.abs(N-100));
            return;
        }

        int cnt = 0;
        for(int i =0; i<10; i++){
            boolean isContains = false;
            for(int j : brokenButton)
                if(i==j) isContains = true;
            if(!isContains)
                canUse[cnt++] = i;
            
        }

        int temp = N;
        digit = 0;
        while(temp != 0){
            digits[digit++] = temp%10;
            temp/=10;
        }
        combination(0);
        System.out.println(ans);
        
    }

    static void combination(int cnt){

        int intTempDigits = 0;
        for(int i = cnt-1; i>=0; i--){
            intTempDigits+= tempDigits[i]*Math.pow(10,i);
        }
        //if(ans >cnt+Math.abs(N-intTempDigits) ){System.err.println(N+" "+intTempDigits+" "+cnt+" "+ ans+" "+ cnt+Math.abs(N-intTempDigits));}
        if(cnt>0)ans = Math.min(ans, cnt+Math.abs(N-intTempDigits));
        if(cnt<digit+1)
            for(int i : canUse){
                tempDigits[cnt] = i;
                combination(cnt+1);
            }
    }
}