import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static StringTokenizer st;
    static int N, fsr, fsc, ssr, ssc,fst, sst,ans;
    static ArrayList<Person> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case =1;test_case<=T; test_case++){
            fsr = -1; fsc = -1; ssr = -1; ssc = -1;
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            people = new ArrayList<>();
            for(int r = 0; r < N; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++){
                    int k = Integer.parseInt(st.nextToken());
                    if(k==1){
                        people.add(new Person(r, c));
                    }else if(k>1){
                        if(fsr==-1){
                            fsr = r;
                            fsc = c;
                            fst = k;
                        }
                        else{
                            ssr = r;
                            ssc = c;
                            sst = k;
                        }
                    }
                }
            }
            for(Person p : people){
                p.fT = Math.abs(p.r-fsr)+Math.abs(p.c-fsc);
                p.sT = Math.abs(p.r-ssr)+Math.abs(p.c-ssc);
            }
            comb(0,0);
            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
        
    }
    static void comb(int cnt,int bitMask){
        if(cnt == people.size()){
            startTime(bitMask);
            return;
        }
        comb(cnt+1,bitMask|(1<<cnt));
        comb(cnt+1,bitMask);
    }

    static void startTime(int bitMask){
        PriorityQueue<Person> fpq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.fT,o2.fT));
        PriorityQueue<Person> spq = new PriorityQueue<>((o1,o2)->Integer.compare(o1.sT,o2.sT));
        
        for(int i = 0; i<people.size(); i++){
            if((bitMask&(1<<i))>0){
                fpq.add(people.get(i));
            }
            else spq.add(people.get(i));
        }
        int timer = 0;
        Queue<Integer>fQueue = new LinkedList<>();
        Queue<Integer>sQueue = new LinkedList<>();
        while(!(fpq.isEmpty()&&spq.isEmpty()&&fQueue.isEmpty()&&sQueue.isEmpty())){
            if(timer == ans) break;
            timer++;
            // System.out.println(timer);
            while(!fQueue.isEmpty()&&fQueue.peek()<=timer) {
                // System.out.println("firstQue is poped");
                fQueue.poll();
            }
            while(!sQueue.isEmpty()&&sQueue.peek()<=timer) {
                // System.out.println("secondQue is poped");
                sQueue.poll();
            }

            while(!fpq.isEmpty()&&fpq.peek().fT <timer&&fQueue.size()<3) {
                fQueue.add(timer+fst);
                // System.out.println(fpq.peek().r+" "+fpq.peek().c+" is onStair");
                fpq.poll();
            }


            while(!spq.isEmpty()&&spq.peek().sT <timer&&sQueue.size()<3) {
                // System.out.println(spq.peek().r+" "+spq.peek().c+" is onStair");
                sQueue.add(timer+sst);
                spq.poll();
                
            }
        }
        ans = timer;
    }

    static class Person{
        int r,c, fT,sT,time;
        boolean isPoped = false;
        Person(int r, int c){
            this.r = r;
            this.c = c;
        }

    }
    



}