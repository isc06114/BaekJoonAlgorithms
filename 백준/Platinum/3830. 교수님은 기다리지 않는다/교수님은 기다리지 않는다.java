import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {

		initFI();
		StringBuilder sb = new StringBuilder();
		while (true) {
			N = nextInt();
			M = nextInt();
			if (N == 0 && M == 0) {
				System.out.print(sb);
				return;
			}
			nodes = new Node[N + 1];

			for (int i = 1; i <= N; i++) {
				nodes[i] = new Node(i);
			}
			for (int i = 0; i < M; i++) {
				char cmd = nextChar();
				int frist = nextInt();
				int second = nextInt();
				if (cmd == '!') {
					union(nodes[frist], nodes[second], nextInt());
				} else {
					int w = findWeight(nodes[frist], nodes[second]);
					sb.append(w == Integer.MAX_VALUE ? "UNKNOWN" : w).append('\n');
				}
			}
			// System.out.println(Arrays.toString(nodes));
		}
	}

	static void union(Node first, Node second, int w) {
		int[] firstRank = findRank(first.index, 0);
		int[] secondRank = findRank(second.index, 0);
		if (nodes[firstRank[0]].rankLength > nodes[secondRank[0]].rankLength) {
			nodes[secondRank[0]].rank = nodes[firstRank[0]].rank;
			nodes[secondRank[0]].weight = firstRank[1] - secondRank[1] + w;

		} else {
			nodes[firstRank[0]].rank = nodes[secondRank[0]].rank;
			nodes[firstRank[0]].weight = secondRank[1] - firstRank[1] - w;
			if (nodes[firstRank[0]].rankLength == nodes[secondRank[0]].rankLength) {
				nodes[secondRank[0]].rankLength++;
			}
		}
	}

	static int[] findRank(int index, int cnt) {
		if (index == nodes[index].rank) {
			return new int[] { index, cnt };
		}
		cnt += nodes[index].weight;
		return findRank(nodes[index].rank, cnt);
	}

	static int findWeight(Node first, Node second) {
		int[] firstRank = findRank(first.index, 0);
		int[] secondRank = findRank(second.index, 0);
		if (firstRank[0] == secondRank[0]) {
			return secondRank[1] - firstRank[1];
		}
		return Integer.MAX_VALUE;
	}

	static class Node {
		int index, weight, rank, rankLength;

		Node(int index) {
			this.index = index;
			this.rank = index;
		}

		@Override
		public String toString() {
			return "[index: " + index + ", rank: " + rank + ", weight: " + weight + "],\n";
		}
	}

	// Fast IO
	private static final int MAX_BUFFER_SIZE = 1 << 16;
	private static DataInputStream inputStream;
	private static byte[] buffer;
	private static int curIdx, maxIdx;

	private static void initFI() {
		inputStream = new DataInputStream(System.in);
		buffer = new byte[MAX_BUFFER_SIZE];
		curIdx = maxIdx = 0;
	}

	private static int nextInt() throws IOException {
		int ret = 0;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');
		if (neg)
			return (int) -ret;
		return (int) ret;
	}

	private static char nextChar() throws IOException {
		byte c = read();
		while (c <= ' ')
			c = read();
		return (char) c;
	}

	private static byte read() throws IOException {
		if (curIdx == maxIdx) {
			maxIdx = inputStream.read(buffer, curIdx = 0, MAX_BUFFER_SIZE);
			if (maxIdx == -1)
				buffer[0] = -1;
		}
		return buffer[curIdx++];
	}
}