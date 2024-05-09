import java.io.*;
import java.util.*;

class Main {

    static int ans, map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = Integer.MAX_VALUE;
        map = new int[10];
        for (int r = 0; r < 10; r++) {
            String str = br.readLine();
            for (int c = 0; c < 10; c++) {
                if (str.charAt(c) == 'O') {
                    map[r] += (1 << c);
                }
            }
        }

        initDfs(-1, 0, map[0], map[1]);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void initDfs(int permutation, int cnt, int zeroBit, int firstBit) {
        // sb.append(String.format("%10d",
        // Integer.parseInt(Integer.toBinaryString(zeroBit)))).append('\n');

        dfs(1, cnt, zeroBit, firstBit, map[2]);
        for (int i = permutation + 1; i < 10; i++) {
            int bit = ((1 << (i + 1)) | (1 << i) | (1 << (i - 1))) & ((1 << 10) - 1);
            initDfs(i, cnt + 1, zeroBit ^ bit, firstBit ^ (1 << i));
        }
    }

    static void dfs(int row, int cnt, int before, int current, int next) {
        next = next ^ before;
        current ^= before;
        current ^= before << 1;
        current ^= before >> 1;
        current &= ((1 << 10) - 1);
        while (before != 0) {
            before -= before & -before;
            cnt++;
        }
        if (row == 8) {
            dfs(row + 1, cnt, current, next, 0);
        } else if (row == 9) {
            if (current == 0) {
                ans = Math.min(ans, cnt);
            }
        } else {
            dfs(row + 1, cnt, current, next, map[row + 2]);
        }
    }

}