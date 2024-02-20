import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//12,736 kb 96 ms
public class Main {

    static StringTokenizer st;
    static int N,M;
    static Student[] students;
    static StringBuilder sb;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] hasTail = new boolean[N+1];
        students = new Student[N+1];
        for(int i = 0; i<=N; i++){
            students[i] = new Student(i);
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            students[second].frontIndex.add(first);
            hasTail[first] = true;
        }
        visited = new boolean[N+1];
        for(int i = 1; i<=N; i++){
            if(!hasTail[i]) {
                // System.out.println("tail:"+i);  //debug
                dfs(students[i],0);
            }
        }
        System.out.println(sb);
    }
    static void dfs(Student student,int cnt){
        student.height = cnt;
        visited[student.number] = true;
        for(int s:student.frontIndex){
            if(visited[s]) continue;
            dfs(students[s],cnt+1);
        }
        sb.append(student.number).append(' ');
    }


    static class Student{       //오름차순
        int number;
        long height;
        ArrayList<Integer> frontIndex;
        public Student(int number){
            this.number = number;
            this.height = -1;
            frontIndex = new ArrayList<Integer>();
        }
    }

}