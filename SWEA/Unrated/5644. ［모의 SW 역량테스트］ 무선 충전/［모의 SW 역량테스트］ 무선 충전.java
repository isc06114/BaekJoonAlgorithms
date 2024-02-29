import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

    static StringTokenizer st;
    static int M,A,ans;
    static char aMove[],bMove[];
    static int map[][];
    static Charger[] chargers;
    static Person personA, personB;

    static int[][]  mapperFor1 = {{0,0},{1,0},{-1,0},{0,1},{0,-1}},
                    mapperFor2 = {{2,0},{-2,0},{0,2},{0,-2},{1,1},{1,-1},{-1,1},{-1,-1}},
                    mapperFor3 = {{3,0},{-3,0},{0,3},{0,-3},{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}},
                    mapperFor4 = {{4,0},{-4,0},{0,4},{0,-4},{2,2},{2,-2},{-2,2},{-2,-2},{1,3},{1,-3},{-1,3},{-1,-3},{3,1},{3,-1},{-3,1},{-3,-1}},
                    mappers[] = {mapperFor1,mapperFor2,mapperFor3,mapperFor4};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case =1;test_case<=T; test_case++){
            ans=0;
            personA = new Person(0,0);
            personB = new Person(9, 9);
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            chargers = new Charger[A];
            map = new int[10][10];

            st = new StringTokenizer(br.readLine());
            aMove = new char[M];
            for(int i =0; i<M; i++){
                aMove[i] = st.nextToken().charAt(0);
            }
            st = new StringTokenizer(br.readLine());
            bMove = new char[M];
            for(int i =0; i<M; i++){
                bMove[i] = st.nextToken().charAt(0);
            }

            for(int i = 0; i<A; i++){
                st = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(st.nextToken())-1;
                int row = Integer.parseInt(st.nextToken())-1;
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                chargers[i] = new Charger(row,col,range,power);
            }
            for(int k = 0; k < A; k++){
                Charger charger = chargers[k];
                for(int i =0; i<charger.range;i++){
                    for(int[] mapper:mappers[i]){
                        int r= charger.r+mapper[0];
                        int c= charger.c+mapper[1];
                        if(r<0||c<0||r>=10||c>=10) continue;
                        map[r][c]|= 1<<k;
                    }   
                }
            }
            // for(int i = 0; i<10;i++){
            //     for(int j = 0; j<10;j++){
            //         System.out.printf(Integer.toBinaryString(map[i][j])+"\t");
            //     }
            //     System.out.println();
            // }
            if(map[personA.r][personA.c]>0||map[personB.r][personB.c]>0){
                int max1ChargeA = 0;
                int max2ChargeA = 0;
                int max1ChargeB = 0;
                int max2ChargeB = 0;
                int index1A=-1;
                int index2A=-1;
                int index1B=-1;
                int index2B=-1;

                for(int i =0; i<A;i++){
                    if((map[personA.r][personA.c]&(1<<i))>0){
                        if(chargers[i].power>max1ChargeA){
                            max2ChargeA= max1ChargeA;
                            index2A = index1A;
                            max1ChargeA=chargers[i].power;
                            index1A = i;
                        }  
                    }
                    if((map[personB.r][personB.c]&(1<<i))>0){
                        if(chargers[i].power>max1ChargeB){
                            max2ChargeB= max1ChargeB;
                            index2B = index1B;
                            max1ChargeB=chargers[i].power;
                            index1B = i;
                        }  
                    }
                }
                if(index1A==index1B){
                    ans+=max1ChargeA;
                    if(max2ChargeA>max2ChargeB){
                        ans+=max2ChargeA;
                    }
                    else if(max2ChargeB>max2ChargeA){
                        ans+= max2ChargeB;
                    }
                    else{
                        if(index2A==index2B){
                            if(index2A != -1){
                                ans+=max2ChargeA;
                            }
                        }
                        else{
                            ans+=max2ChargeA;
                        }
                    }
                }
                else{
                    ans+=max1ChargeA;
                    ans+=max1ChargeB;
                }
            }
            // System.out.println("time:"+ 0);
            // System.out.print("A: "+personA.r+" "+personA.c);
            // System.out.println(", B: "+personB.r+" "+personB.c);
            // System.out.println("total power:"+ans);
            for(int m = 0; m<M; m++){
                move(personA,aMove[m]);
                move(personB,bMove[m]);
                if(map[personA.r][personA.c]>0||map[personB.r][personB.c]>0){
                    int max1ChargeA = 0;
                    int max2ChargeA = 0;
                    int max1ChargeB = 0;
                    int max2ChargeB = 0;
                    int index1A=-1;
                    int index2A=-1;
                    int index1B=-1;
                    int index2B=-1;

                    for(int i =0; i<A;i++){
                        if((map[personA.r][personA.c]&(1<<i))>0){
                            if(chargers[i].power>=max1ChargeA){
                                max2ChargeA= max1ChargeA;
                                index2A = index1A;
                                max1ChargeA=chargers[i].power;
                                index1A = i;
                            }  
                            else if(chargers[i].power>=max2ChargeA){
                                max2ChargeA = chargers[i].power;
                                index2A = i;
                            }
                        }
                        if((map[personB.r][personB.c]&(1<<i))>0){
                            if(chargers[i].power>=max1ChargeB){
                                max2ChargeB= max1ChargeB;
                                index2B = index1B;
                                max1ChargeB=chargers[i].power;
                                index1B = i;
                            }  
                            else if(chargers[i].power>=max2ChargeB){
                                max2ChargeB = chargers[i].power;
                                index2B = i;
                            }
                        }
                    }
                    if(index1A==index1B){
                        ans+=max1ChargeA;
                        if(max2ChargeA>max2ChargeB){
                            ans+=max2ChargeA;
                        }
                        else if(max2ChargeB>max2ChargeA){
                            ans+= max2ChargeB;
                        }
                        else{
                            if(index2A==index2B){
                                if(index2A != -1){
                                    ans+=max2ChargeA;
                                }
                            }
                            else{
                                ans+=max2ChargeA;
                            }
                        }
                    }
                    else{
                        ans+=max1ChargeA;
                        ans+=max1ChargeB;
                    }
                }
                // System.out.println("time:"+(m+1));
                // System.out.print("A: "+personA.r+" "+personA.c);
                // System.out.println(", B: "+personB.r+" "+personB.c);
                // System.out.println("total power:"+ans);
            }
            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
    static void move(Person p, char cmd){
        if(cmd == '0') return;
        if(cmd == '3'&&p.r+1<10){ 
            p.r+=1;
            return;
        }
        if(cmd == '2'&&p.c+1<10){ 
            p.c+=1;
            return;
        }
        if(cmd == '1'&&p.r-1>=0){ 
            p.r-=1;
            return;
        }
        if(cmd == '4'&&p.c-1>=0){ 
            p.c-=1;
            return;
        }
            
        
    }
    static class Charger{
        int r,c, range,power;
        Charger(int r, int c,int range, int power){
            this.r = r;
            this.c = c;
            this.range = range;
            this.power = power;
        }

    }
    static class Person{
        int r,c;
        Person(int r, int c){
            this.r = r;
            this.c = c;
        }

    }

}