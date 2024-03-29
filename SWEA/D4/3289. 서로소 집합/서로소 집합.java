import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//12,736 kb 96 ms
public class Solution {

    static int N,M;
    static Node<Integer>[] nodes;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            sb.append('#').append(tc).append(' ');
            TC();
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void TC() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new Node[N+1];
        for(int i = 0; i<=N; i++) nodes[i] = new Node<Integer>(i);
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            
            if(cmd == '0'){
                Node.union(nodes[first],nodes[second]);
            }
            else{
                sb.append(Node.isUnion(nodes[first],nodes[second])?'1':'0');
            }
        }
    }

    static class Node<T>{
        Node<T> rep;
        T data;
        int rank;
        public Node(T data){
            this.rep = this;
            this.data = data;
            this.rank = 0;
        }

        public static void union(Node first,Node  second){
            Node firstR = first.findRep();
            Node secondR = second.findRep();
            if(firstR.rank>secondR.rank){
                secondR.rep = firstR;
            }
            else if(firstR.rank<secondR.rank){
                firstR.rep = secondR;
            }
            else{
                secondR.rep = firstR;
                firstR.rank+=1;
            }
        }

        public static boolean isUnion(Node first,Node  second){
            return(first.findRep()==second.findRep());
        }

        public Node<T> findRep(){
            return findRep(this.rep);
        }

        private Node<T> findRep(Node<T> node){
            if(node.rep == node) return node;
            else return findRep(node.rep);
        }
        
    }
}