import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static int sections[],maxP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sections = new int[N];
        int isMovable[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = Integer.MAX_VALUE;
        maxP=0;
        for(int i = 0; i<N; i++){
            sections[i] = Integer.parseInt(st.nextToken());
            maxP+=sections[i];
        }
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int edgeN = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j<edgeN; j++){
                int bSectionIndex = Integer.parseInt(st.nextToken())-1;
                isMovable[i]|=(1<<bSectionIndex);
                isMovable[bSectionIndex]|=1<<i;
            }
        }

        if(N==2){
            System.out.println(Math.abs(sections[1]-sections[0]));
            return;
        }

        int[] temp = new int[N];
        for(int i = 0; i<N; i++){
            temp[i] = i;
        }

        PriorityQueue<int[]> combs = combination(temp);

        while(!combs.isEmpty()){
            int[] comb = combs.poll();
            //System.out.println(Arrays.toString(comb));
            int isLinked = isMovable[comb[1]];
            int boolCompare = 0;
            for(int i =1; i<comb.length; i++){
                int index = comb[i];
                boolCompare|=(1<<index);
            }
            int boolCompare2 = 0;
            for(int j = 0; j<N; j++){
                if((boolCompare&(1<<j))>0) continue;
                boolCompare2|=(1<<j);
            }
            while((isLinked&boolCompare)>0){
                int tt = 0;
                isLinked&=boolCompare;
                boolCompare-=isLinked;
                while(isLinked!=0){
                    int rightBit = isLinked&-isLinked;
                    tt|=isMovable[(int)(Math.log(rightBit)/Math.log(2))];
                    isLinked=isLinked-rightBit;
                }
                isLinked = tt;
            }
            int isLinked2 = isMovable[(int)(Math.log(boolCompare2&-boolCompare2)/Math.log(2))];

            while(boolCompare2!=0&&(isLinked2&boolCompare2)>0){
                int tt = 0;
                isLinked2&=boolCompare2;
                boolCompare2-=isLinked2;
                while(isLinked2!=0){
                    int rightBit = isLinked2&-isLinked2;
                    tt|=isMovable[(int)(Math.log(rightBit)/Math.log(2))];
                    isLinked2-=rightBit;
                }
                isLinked2 = tt;
            }
            if((boolCompare==0||comb.length==2)&&(boolCompare2==0)){
                ans = comb[0];
                break;
            }
            
        }
        System.out.println(ans==Integer.MAX_VALUE?"-1":ans);

    }

    static int[] arr;
    static int[] output;
    static PriorityQueue<int[]> outputs;
    static PriorityQueue<int[]> combination(int[] array){
        arr = array;
        outputs = new PriorityQueue<>((o1,o2)-> Integer.compare(o1[0],o2[0]));
        output = new int[arr.length];
        dfsC(0,0);
        return outputs;
    }
    static void dfsC(int start,int cnt){
        if(cnt == (arr.length/2)) return;
        for(int i =start; i<arr.length; i++){
            output[cnt] = arr[i];
            int[] o = new int[cnt+2];
            int sum = 0;
            for(int k = 1; k<=cnt+1; k++){
                o[k] = output[k-1];
                sum+=sections[output[k-1]];
            }
            o[0] = Math.abs(maxP-2*sum);
            outputs.add(o);
            dfsC(i+1,cnt+1);
        }
    }

    static class Section{
        int index;
        ArrayList<Section> linkedSections;
        public Section(int index){
            this.index = index;
            this.linkedSections = new ArrayList<>();
        }
    }
}