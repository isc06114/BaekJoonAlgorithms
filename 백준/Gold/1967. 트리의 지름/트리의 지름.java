import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    static int n, ans = -1;
    static Node nodePool[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        nodePool = new Node[n];
        for(int i = 1; i<n; i++){
            nodePool[i] = new Node(i);
        }
        nodePool[0] = new Node(0, 0);
        for(int i = 0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken())-1;
            int child = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            nodePool[child].w = weight;
            nodePool[parent].childs.add(nodePool[child]);
        }
        calLength(nodePool[0]);
        for(Node n :nodePool){
            findMaxLength(n);
            //System.out.println(n);
        }
        System.out.println(ans);
    }

    static int calLength(Node node){
        if(node == null) return 0;
        if(node.length != 0) return node.length;
        for(Node child : node.childs){
            node.length = Math.max(node.length,calLength(child));
        }
        return (node.length +=node.w);
    }

    static void findMaxLength(Node node){
        int temp1=0,temp2=0;
        for(Node child :node.childs){
            if(temp1<calLength(child)){temp2 = temp1; temp1 = calLength(child);}
            else if(temp2<calLength(child)) temp2 = calLength(child);
        }
        ans = Math.max(ans,temp1+temp2);
    }

    static class Node{
        int index, w, length;
        ArrayList<Node> childs;
        Node(int index, int w){
            this.index = index;
            this.w = w;
            this.childs = new ArrayList<>();
        }
        Node(int index){
            this(index,0);
        }
        @Override
        public String toString(){
            return index+"st node`s length = "+length+"\n";
        }
    }
}