import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int startMonth = 3,startDay = 1;
		int endMonth=3, endDay = 1;
		int END_MONTH = 11,END_DAY = 30;
		LinkedList<Project> projects = new LinkedList<>();
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sM = Integer.parseInt(st.nextToken());
			int sD = Integer.parseInt(st.nextToken());
			int eM = Integer.parseInt(st.nextToken());
			int eD = Integer.parseInt(st.nextToken());
			projects.add(new Project(sM,sD,eM,eD));
		}
		int ans = 0;
		boolean check = true;
		while(check&&!(endMonth>END_MONTH||(endMonth==END_MONTH&&endDay>END_DAY))) {
			check = false;
			ListIterator<Project> iter = projects.listIterator();
			while(iter.hasNext()) {
				Project p = iter.next();
				if(p.eM<endMonth||p.eM == endMonth&&p.eD<=endDay){
					iter.remove();
				}
				else if(p.sM<startMonth ||(p.sM==startMonth&&p.sD<=startDay)) {
					if(p.eM>endMonth||(p.eM==endMonth&&p.eD>endDay)){
						endMonth = p.eM;
						endDay = p.eD;
						iter.remove();
						check = true;
					}
				}
			}
//			System.out.println(startMonth+" "+startDay);
//			System.out.println(endMonth+" "+endDay);
			startDay = endDay;
			startMonth = endMonth;
			ans++;
		}
		System.out.println(check?ans:0);


	}
	static class Project{
		int sM,sD,eM,eD,length;
		public Project(int sM,int sD, int eM, int eD) {
			this.sM = sM;
			this.sD = sD;
			this.eM = eM;
			this.eD = eD;
		}
		
		
	}
}