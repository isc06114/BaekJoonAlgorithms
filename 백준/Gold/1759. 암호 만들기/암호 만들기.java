import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static List<Character> listN;
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String[] input = br.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        listN = new ArrayList<>();
        while(st.hasMoreTokens()) listN.add(st.nextToken().charAt(0));
        Collections.sort(listN);
        chars = new char[L];
        ndfs(0,0,1,2);
        System.out.print(sb);
    }

    static char[] chars;
    static void ndfs(int start, int cnt,int fcnt,int scnt){
        if(cnt==L){
            if(fcnt<=0&&scnt<=0){
                for(char c : chars)
                    sb.append(c);
                sb.append('\n');
            }
        return;
        }
        for(int i = start; i<listN.size(); i++){
            chars[cnt] = listN.get(i);
            if(vowels.contains(chars[cnt]))
                ndfs(i+1,cnt+1,fcnt-1,scnt);
            else ndfs(i+1,cnt+1,fcnt,scnt-1);
        }
    }
}