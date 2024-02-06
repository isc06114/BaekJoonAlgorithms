import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
    static BufferedReader br;
	public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        Solution solution = new Solution();
        int T = 10;
        for(int test_case  = 1; test_case <= T; test_case++){
            sb.append("#"+test_case+" ").append(solution.TC()).append('\n');

        }
        System.out.println(sb);
	}

    public int TC() throws IOException{
        int isPass = 1;
        StringTokenizer st;
        int maxSize = Integer.parseInt(br.readLine());
        int depth = (int)(Math.log(maxSize)/Math.log(2));
        //System.err.println("최대깊이: "+depth+", 중간값: "+ (1<<(depth-1)));
        for(int d = 0; d<(1<<(depth-1)); d++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            char c = st.nextToken().charAt(0);
            if(c<='9'&& c>='0') isPass = 0;
        }
        for(int d = (1<<(depth-1)); d<maxSize; d++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            char c = st.nextToken().charAt(0);
            if(c>'9'||c<'0'){
                if(st.hasMoreTokens()){
                    char c1 = st.nextToken().charAt(0);
                    if(c1>'9'||c1<'0') isPass = 0;
                }
                else isPass = 0;
                if(st.hasMoreTokens()){
                    char c1 = st.nextToken().charAt(0);
                    if(c1>'9'||c1<'0') isPass = 0;
                }
                else isPass = 0;
            }
            else{
                if(st.hasMoreTokens()) isPass = 0;
            }
        }
        return isPass;
    }
}