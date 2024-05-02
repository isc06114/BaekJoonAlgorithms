import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	static int N, T, LOGN;

	public static void main(String[] args) throws IOException {
		initFI();
		StringBuilder sb = new StringBuilder();
		N = nextInt();
		LOGN = (int) Math.ceil(Math.log(N) / Math.log(2));
		Tree segTree = new Tree();
		for (int i = 1; i <= N; i++) {
			segTree.nodes[i].sum = nextInt();
		}
		T = nextInt();
		for (int i = 0; i < T; i++) {
			char cmd = nextChar();
			if (cmd == '1') {

				segTree.add(nextInt(), nextInt());
			} else {
				sb.append(segTree.find(nextInt())).append('\n');
			}
			// segTree.postOrder();
			// System.out.println(segTree.nodes[i + 1].sum);
		}
		System.out.print(sb);
	}

	static class Tree {
		Node head;
		Node[] nodes;

		void add(int left, int right) {
			left--;
			int start = 1;
			while (left != right) {

				// System.out.print(left + " ");
				// System.out.println(start);

				int newLeft = _add(left, right, start, head);
				start += newLeft - left;
				left = newLeft;
			}
		}

		int _add(int left, int right, int start, Node node) {
			if (node.leftIndex == left) {
				if (node.rightIndex > right) {
					return _add(left, right, start, node.left);
				} else {
					node.lazyStart += start;
					node.lazyPower++;
					return node.rightIndex;
				}

			} else {
				int mid = (node.leftIndex + node.rightIndex) / 2;
				if (left >= mid)
					return _add(left, right, start, node.right);
				else {
					return _add(left, right, start, node.left);
				}
			}
		}

		long find(int index) {
			_update(index, head);
			// System.out.println(nodes[index].sum);
			return nodes[index].sum;
		}

		void _update(int index, Node node) {
			if (node.leftIndex + 1 == node.rightIndex) {

				// System.out.println(node.rightIndex);
				// System.out.println(node.lazyPower + " " + node.lazyStart);

				if (node.lazyPower != 0) {
					node.sum += node.lazyStart;
					node.lazyPower = 0;
					node.lazyStart = 0;
				}
				return;
			}
			int mid = (node.leftIndex + node.rightIndex) / 2;
			if (node.lazyPower != 0) {
				node.left.lazyPower += node.lazyPower;
				node.left.lazyStart += node.lazyStart;
				node.right.lazyPower += node.lazyPower;
				node.right.lazyStart += node.lazyStart + node.lazyPower * (node.rightIndex - node.leftIndex) / 2;

				node.lazyStart = 0;
				node.lazyPower = 0;
			}
			if (index <= mid) {
				_update(index, node.left);
			} else {
				_update(index, node.right);
			}
		}

		Tree() {
			nodes = new Node[1 << LOGN + 1];
			head = new Node();
			makeTree(LOGN, 0, 1 << LOGN, head);
		}

		void makeTree(int cnt, int leftIndex, int rightIndex, Node node) {
			node.leftIndex = leftIndex;
			node.rightIndex = rightIndex;
			if (cnt == 0) {
				nodes[node.rightIndex] = node;
				return;
			}
			int mid = (leftIndex + rightIndex) / 2;
			node.left = new Node();
			node.left.parent = node;
			node.right = new Node();
			node.right.parent = node;
			makeTree(cnt - 1, leftIndex, mid, node.left);
			makeTree(cnt - 1, mid, rightIndex, node.right);
		}

		void postOrder() {
			postOrder(head);
		}

		void postOrder(Node node) {
			System.out.println(node.leftIndex + ", " + node.rightIndex + ", sum=" + node.sum);
			if (node.left != null) {
				postOrder(node.left);
			}
			if (node.right != null) {
				postOrder(node.right);
			}
		}
	}

	static class Node {
		long sum, lazyStart;
		Node left, right, parent;
		int leftIndex, rightIndex, lazyPower;

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