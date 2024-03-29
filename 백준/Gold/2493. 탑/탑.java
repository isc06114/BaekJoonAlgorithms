import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int linkedTower[];
    static tower lis[];
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        linkedTower = new int[N+1]; linkedTower[0] = 0; lis = new tower[N+1];
        lis[0] = new tower(0, 0);
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N;i++){
            lisUpdate(new tower(i,Integer.parseInt(st.nextToken())));
        }

        for(int i = 1; i<=N;i++){
            sb.append(linkedTower[i]).append(' ');
        }
        System.out.println(sb);
    }


    static int cnt = 0, maxHeight = 0;
    static void lisUpdate(tower t){
        if(t.height>maxHeight){
            maxHeight = t.height;
            lis[0] = t;
            cnt = 0;
            return;
        }
        for(int i = cnt; i>=0; i--){
            if(t.height<lis[i].height){
                lis[i+1] = t;
                cnt = i+1;
                linkedTower[t.index] = lis[i].index;
                return;
            }
            if(t.height==lis[i].height){
                lis[i]=t;
                cnt = i;
                return;
            }
        }
    }


    public static class tower{
        int index, height;
        public tower(int index, int height){
            this.index = index;
            this.height = height;
        }


    }
}