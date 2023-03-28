package prac1;

import java.util.ArrayList;

public class Week4Q2 {
	
	int vertices;
	int adjacency_matrix[][];
	Week4Q2(int v){
		vertices = v;
		adjacency_matrix = new int[vertices][vertices];
	}
	
	public void addEdge(int source, int destination) {
		adjacency_matrix[source][destination] = 1;
	}
	
	public int solution(String resturants) {
		int[] indegree = new int[vertices];
		for(int i=0; i<vertices; i++) {
			for(int j=0; j<vertices; j++) {
				if (adjacency_matrix[i][j]!=0) {
					indegree[j]++;
				}
			}
		}
		
		int n = resturants.length();
		char[] resturant = resturants.toCharArray();
		
		int map[][] = new int[n][26];
		Queue Q = new Queue();
		
		for(int i=0; i<vertices; i++) {
			if(indegree[i]==0) {
				map[i][resturant[i]-'a']=1;
				
				Q.enqueue(i);
//				System.out.println("xD");
			}
		}
		
		int res = 0;
		while(!Q.isEmpty()) {
			
			int node = Q.dequeue();
			
			int max = getMax(map[node]);
			res = Math.max(res, max);
			
			for(int j=0; j<vertices; j++) {
				if(adjacency_matrix[node][j]!=0) {
					
					for(int i=0; i<26; i++) {
						map[j][i] = Math.max(map[j][i], map[node][i] + (resturant[j]-'a'==i?1:0));
					}
					indegree[j]--;
					if(indegree[j]==0) {
						Q.enqueue(j);
					}
				}
			}
		}
		return res;
	}
	
	public int getMax(int[] list) {
		int num = list[0];
		for(int i:list) {
			num = Math.max(i, num);
		}
		return num;
	}
	
	public static void main(String[] args) {
		Week4Q2 q = new Week4Q2(5);
		q.addEdge(0, 1);
		q.addEdge(0, 2);
		q.addEdge(2, 3);
		q.addEdge(3, 4);
//		q.addEdge(4, 5);
//		System.out.println("a");
		System.out.println(q.solution("abaca"));
//		q.solution("abaca");
		
	}
}
