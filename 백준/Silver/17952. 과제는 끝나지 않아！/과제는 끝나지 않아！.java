import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Stack<Task> tasks;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tasks = new Stack<>();
		Task curTask = new Task(0,0);
		int myScore = 0;
		for(int i =0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			if(cmd == '1') {
				int score = Integer.parseInt(st.nextToken());
				int wholeTime = Integer.parseInt(st.nextToken());
				tasks.add(curTask);
				curTask = new Task(score,wholeTime);
			}
			curTask.curTime++;
			if(curTask.wholeTime == curTask.curTime) {
				myScore+=curTask.score;
				if(!tasks.isEmpty()) {
					curTask = tasks.pop();
				}
			}
		}
		System.out.println(myScore);
		
	}
	static class Task{
		int score, wholeTime,curTime;
		Task(int score, int wholeTime){
			this.score = score;
			this.wholeTime = wholeTime;
		}
	}
	
}