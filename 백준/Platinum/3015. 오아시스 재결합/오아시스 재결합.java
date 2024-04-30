import java.io.*;
import java.util.*;

public class Main {

	static int N, preHe, sameHeight, canSee, isInc;
	static ArrayList<int[]> list;
	static long ans;

	public static void main(String[] args) throws IOException {
		initFI();
		N = nextInt();
		if (N == 1)
			System.out.println(0);
		list = new ArrayList<>();
		preHe = nextInt();
		for (int _i = 1; _i < N; _i++) {
			int height = nextInt();
			if (height == preHe) {
				if (!list.isEmpty() && list.get(list.size() - 1)[0] == height) {
					list.get(list.size() - 1)[1]++;
				} else {
					list.add(new int[] { height, 1 });
				}
				boolean isSmallP = true;
				for (int i = list.size() - 1; i >= 0; i--) {
					if (list.get(i)[0] <= height) {
						ans += list.get(i)[1];
						if (list.get(i)[0] != height) {
							list.remove(i);
						}
					} else if (isSmallP) {
						if (list.get(i)[0] == preHe)
							ans += list.get(i)[1];
						else
							ans++;
						isSmallP = false;
					}
				}

			} else if (height > preHe) { // 증가할 때
				if (!list.isEmpty() && list.get(list.size() - 1)[0] == preHe) {
					list.get(list.size() - 1)[1]++;
				} else {
					list.add(new int[] { preHe, 1 });
				}
				boolean isSmallP = true;
				for (int i = list.size() - 1; i >= 0; i--) {
					if (list.get(i)[0] <= height) {
						ans += list.get(i)[1];
						if (list.get(i)[0] != height) {
							list.remove(i);
						}
					} else if (isSmallP) {
						if (list.get(i)[0] == preHe)
							ans += list.get(i)[1];
						else
							ans++;
						isSmallP = false;
					}
				}
			} else { // 감소할 때
				if (!list.isEmpty() && list.get(list.size() - 1)[0] == preHe) {
					list.get(list.size() - 1)[1]++;

				} else {
					list.add(new int[] { preHe, 1 });
				}
				ans++;

			}
			// System.out.println("cnt");
			// System.out.println(ans);
			// System.out.println("list");
			// for (int[] arr : list)
			// System.out.print(arr[0] + " ");
			// System.out.println();
			preHe = height;
		}
		System.out.println(ans);

	}

	static class CustomLinkedList {

		Node head, tail;

		CustomLinkedList() {
			head = new Node(-10);
			tail = new Node(-20);
			tail.next = head;
		}

		void add(int height) {
			tail.next.next = new Node(height);
			tail.next = tail.next.next;
		}
	}

	static class Node {
		int height;
		Node next;

		Node(int height) {
			this.height = height;
			next = null;
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