import java.io.*;
import java.util.*;

class Main {

    static int N, K;

    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[100_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        bfs();
        System.out.println(dp[K]);
    }

    static void bfs() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(dp[o1], dp[o2]));
        int temp = N;
        if (N > 0) {
            while (temp < 100_001) {
                pq.add(temp);
                dp[temp] = 0;
                temp += temp;
            }
        } else {
            pq.add(N);
            dp[N] = 0;
        }
        while (!pq.isEmpty()) {
            int pos = pq.poll();
            int time = dp[pos];
            // System.out.println(pos);
            if (pos == K)
                return;

            if (pos - 1 >= 0 && dp[pos - 1] > time + 1) {
                temp = pos - 1;
                if (temp == 0) {
                    pq.add(temp);
                    dp[temp] = time + 1;
                } else
                    while (temp < 100_001) {
                        if (dp[temp] > time + 1) {
                            pq.add(temp);
                            dp[temp] = time + 1;
                        }
                        temp += temp;
                    }
            }
            if (pos + 1 <= 100_000 && dp[pos + 1] > time + 1) {
                temp = pos + 1;
                while (temp < 100_001) {
                    if (dp[temp] > time + 1) {
                        pq.add(temp);
                        dp[temp] = time + 1;
                    }
                    temp += temp;
                }
            }

        }
        return;
    }

    static void power(int n, int time) {
        int temp = n;
        while (temp < 100_001) {
            dp[temp] = time;
            temp += n;
        }
    }

}