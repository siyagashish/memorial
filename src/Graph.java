
import java.util.*;
import java.util.Vector;

public class Graph {

	int V = 25;
	Vector<Vector<Integer>> adj;

	int weight[][] = new int[V][V];

	public Graph() {
		adj = new Vector<Vector<Integer>>(V);

		for (int i = 0; i <= 24; i++) {
			Vector<Integer> temp = new Vector<Integer>();
			adj.add(temp);
		}
		
		adj.get(0).add(9);	//for adding node 0
		adj.get(0).add(10);
		adj.get(9).add(0);
		adj.get(10).add(0);

		int j = 9;					//inner circle nodes
		for (int i = 1; i <= 8; i++) {
			if (i + 1 > 8) {
				adj.get(i).add((i + 1) % 8);
				adj.get((i + 1) % 8).add(i);
			} else {
				adj.get(i).add((i + 1));
				adj.get((i + 1)).add(i);
			}
			adj.get(i).add(j);
			adj.get(j).add(i);
			
			adj.get(i).add(j+1);
			adj.get(j+1).add(i);
			j+= 2;
		}

		for (int i = 9; i <= 24; i++) {		//outer circle nodes
			if (i + 1 > 24) {
				adj.get(i).add(9);
				adj.get(9).add(i);
			} else {
				adj.get(i).add((i + 1));
				adj.get((i + 1)).add(i);
			}
		}

		for (int i = 0; i <= 24; i++) {			//defining weights 
			if(i==0) {
				for(int k=0;k<=24;k++) {
					weight[i][k] = 10;
				}
			}
			else if (i <= 8) {
				for (int k = 0; k <= 24; k++) {
					if(k==0) {
						weight[i][k] =0;
					}
					else if (k <= 8) {
						weight[i][k] = 1;
					} else {
						weight[i][k] = 4;
					}
				}
			} else {
				for (int k = 0; k <= 24; k++) {
					if(k==0) {
						weight[i][k]=10;
					}
					else if (k <= 8) {
						weight[i][k] = 4;
					} else {
						weight[i][k] = 3;
					}
				}
			}
		}
	}

	HashMap<Integer,Integer> parent = new HashMap<Integer,Integer>();
	
	int min_dist(int dis[],boolean visited[]) {
		
		int min = Integer.MAX_VALUE;
		int index = 0;
		
		for(int i=0;i<=24;i++) {
			if(dis[i]<=min && visited[i]==false) {
				min = dis[i]; index = i;
			}
		}
		return index;
	}
	
	
	public void shortest_path(int v,int u) {
		int dis[]  = new int[25];
		for(int i=0;i<=24;i++) {
			dis[i] = Integer.MAX_VALUE;
		}
		boolean visited[] = new boolean[25];
		for(int i=0;i<=24;i++) {
			visited[i] = false;
		}
		dis[v] = 0;
		parent.put(v, null);
		
		for(int count=0;count<=24;count++) {
			
			int index = min_dist(dis,visited); 
			visited[index] = true;
			
			Iterator<Integer> it = adj.get(index).iterator();
			
			while(it.hasNext()) {
				int temp = it.next();
				if(!visited[temp] && dis[temp]> dis[index]+ weight[index][temp]) {
					dis[temp] = dis[index]+ weight[index][temp];
					parent.put(temp, index);
				}
			}
		}
		
		System.out.print("path from "+ v + " to "+ u +" : ");
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(u);
		while(parent.get(u)!=null) {
			stack.push(parent.get(u));
			u = parent.get(u);
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		System.out.println();
	}
	
	public void shortest_path(int v) {
		shortest_path(0,v);
	}

}
