import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            queue.offer(i);
        }
        int n=queue.poll();
        while(!queue.isEmpty()){
            queue.offer(queue.poll());
            n=queue.poll();
        }
        System.out.println(n);

        


    }
}