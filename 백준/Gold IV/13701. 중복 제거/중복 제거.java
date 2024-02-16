import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        int iKey;
        int size = st.countTokens();
        for(int i = 0; i <size; i++){
            iKey = Integer.parseInt(st.nextToken());
            if(!set.contains(iKey)){
                set.add(iKey);
                bw.write(iKey+" ");
            }
        }
        bw.flush();
        bw.close();

    }
}
