import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11,468 kb 340 ms
public class Main {

    static int R,C;
    static int col[];
    static long row[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        row = new long[R];
        col = new int[C];
        int ri1=-1, ri2 = -1;
        long rTemp = -1;
        int ci = -1;
        int cTemp = -1;
        st = new StringTokenizer(br.readLine());
        for(int i  = 0; i<R; i++){
            row[i] = Long.parseLong(st.nextToken())*1_000_000_000;
            if(row[i]>rTemp){
                rTemp = row[i];
                ri1 = i;
                ri2 = -1;
            }
            else if(row[i] == rTemp){
                ri2 = i;
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i  = 0; i<C; i++){
            col[i] = Integer.parseInt(st.nextToken());
            if(col[i]>cTemp){
                cTemp = col[i];
                ci = i;
            }
        }
        long sum = 0;
        for(int r = 0; r<=ri1;r++){
            sum+= row[r]+col[0]; 
        }
        if(ri2!=-1){
            for(int c = 1; c<=ci;c++){
                sum+= row[ri1]+col[c]; 
            }
            for(int r = ri1+1; r<=ri2; r++){
                sum+=row[r]+col[ci]; 
            }
            for(int c = ci+1; c<C;c++){
                sum+= row[ri2]+col[c]; 
            }
            for(int r = ri2+1; r<R; r++){
                sum+=row[r]+col[C-1]; 
            }
        }
        else{
            for(int c = 1; c<C;c++){
                sum+= row[ri1]+col[c]; 
            }
            for(int r = ri1+1; r<R; r++){
                sum+=row[r]+col[C-1]; 
            }
        }
        // debug();
        System.out.println(sum);
    }

}