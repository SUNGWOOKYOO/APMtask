package task3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Tool {
	public boolean BFS(Graph G, Vertex s, Vertex t) {
		
		// Mark all vertices into "visted"
		Iterator<Vertex> GVit = G.v.values().iterator();
		while(GVit.hasNext()) {
			Vertex GV = GVit.next();
			G.v.get(GV.getId()).visited = false;
			//debug : print all vertices
			//System.out.print(G.v.get(GV.getId()).toString()+" ");
			//System.out.print(G.v.get(GV.getId()).getname()+" ");
			//System.out.println(G.v.get(GV.getId()).visited);
		}
		
		// Create a queue for BFS
		ArrayDeque<Vertex> Q = new ArrayDeque<>(); 
		Q.push(G.v.get(s.getId()));
		G.v.get(s.getId()).visited = true;
		// debug : check if queue is automatically updated when we change G.v
		//System.out.println(G.v.get(s.getId()).visited);
		//System.out.println(Q);
		//System.out.println(Q.peek().visited);
		
		while(!Q.isEmpty()) {
			Vertex u = Q.pop();
			//System.out.println(Q);
			
			// Go over G.adj[u] List  
			Iterator<Vertex> GadjOfu = G.adj_list.get(u.getId()).iterator();
			while(GadjOfu.hasNext()) {
				Vertex aVertex = GadjOfu.next();
				if (G.v.get(aVertex.getId()).visited == false) {
					Q.push(G.v.get(aVertex.getId()));
					G.v.get(aVertex.getId()).visited = true;
					
					// path info update 
					G.v.get(aVertex.getId()).pi = u;
					//G.v.get(u.getId()).next = G.v.get(aVertex.getId());
				}
			}
		}
		
		return G.v.get(t.getId()).visited;
	}
	public ArrayList<Vertex> ShortestPathList(Graph G, Vertex s, Vertex t){
		ArrayList<Vertex> Pathset = new ArrayList<>();
		if(BFS(G, G.v.get(s.getId()), G.v.get(t.getId()))) {
			
			Vertex temp = G.v.get(t.getId());
			while(temp.name != s.name) {
				Pathset.add(temp);
				temp = G.v.get(temp.pi.getId());
			}
			Pathset.add(G.v.get(s.getId()));
		};
		
		Collections.reverse(Pathset);
		return Pathset;
	}
}
