import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	static int N, multiInput[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = br.readLine().toCharArray();
        char c;
        Stack<Operator> optStack = new Stack<>();
        ArrayList<Character> postExpression = new ArrayList<>();
        Map<Character,Integer> optPriority = new HashMap<>();
        optPriority.put('-',1);
        optPriority.put('+',1);
        optPriority.put('*',2);
        optPriority.put('/',2);
        int priority = 0;


        for(int i = 0; i<charArray.length;i++){
            c = charArray[i];
            if(c<='Z' && c>='A'){
                postExpression.add(c);
            }
            else if(c =='(') priority+=10;
            else if(c ==')') priority-=10;
            else if(c == '+'||c=='-'|| c=='*'||c=='/'){
                while(!optStack.isEmpty()&&optStack.peek().priority>=optPriority.get(c)+priority){
                    postExpression.add(optStack.pop().opt);
                }
                optStack.add(new Operator(c, optPriority.get(c)+priority));
            }
        }
        while(!optStack.isEmpty()){
            postExpression.add(optStack.pop().opt);
        }
        postExpression.stream().forEach(System.out::print);
	}


    public static class Operator{
        char opt;
        int priority;
        public Operator(char opt, int priority){
            this.opt = opt;
            this.priority = priority;
        } 
    }
}