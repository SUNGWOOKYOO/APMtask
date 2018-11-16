package task3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javafx.util.Pair;

public class Tool{
	public boolean BFS(ResidualGraph G, Vertex s, Vertex t) {
		Iterator<Vertex> GVit = G.v.values().iterator();
		while(GVit.hasNext()) {
			Vertex GV = GVit.next();
			G.v.get(GV.getId()).visited = false;
		}
		ArrayDeque<Vertex> Q = new ArrayDeque<>(); 
		Q.push(G.v.get(s.getId()));
		G.v.get(s.getId()).visited = true;
		
		while(!Q.isEmpty()) {
			Vertex uu = Q.pop();
			Iterator<Vertex> GadjOfu = G.adj_list.get(uu.getId()).iterator();
			while(GadjOfu.hasNext()) {
				Vertex vv = GadjOfu.next();
				boolean ConditionOfResidualCapacity = (G.getResidualCapacity(uu, vv)) > 0;
				if ((G.v.get(vv.getId()).visited == false )&&( ConditionOfResidualCapacity )) {
					Q.push(G.v.get(vv.getId()));
					G.v.get(vv.getId()).visited = true;
					
					// path info update 
					G.v.get(vv.getId()).pi = uu;
				}
			}
		}
		return G.v.get(t.getId()).visited;
	}
	public ArrayList<Vertex> ShortestPathList(ResidualGraph G, Vertex s, Vertex t){
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
	
	// test for TestResidualGraph
	public double EdmondsKarp(ResidualGraph G, Vertex source, Vertex sink) {
		double max_flow = 0;
		
		int iteration_BFS = 0;
		while(BFS(G,G.v.get(source.getId()),G.v.get(sink.getId()))) {
			// Debug: See BFS path
			System.out.println("============================================================================================================================================================");
			System.out.println("							"+ iteration_BFS +" th iteration BFS Result in Edmonds Algorithm: 							");
			System.out.println("============================================================================================================================================================");
			System.out.print("ShortestPath from source to sink using BFS: ");
			System.out.println(ShortestPathList(G, G.v.get(source.getId()), G.v.get(sink.getId())));
			
			// Find the Residual Capacity on the Shortest Path
			double path_flow = Double.MAX_VALUE;
			Vertex back1 = sink;
			while( back1.name != source.name ) {
				Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> edgeOfUV 
					= G.VertexPair(back1.pi, back1);
				path_flow = Math.min(path_flow, (G.getResidualCapacity(back1.pi, back1)));
				back1 = back1.pi;
			}
			max_flow += path_flow;
			System.out.println("Residual Capacity on the shortest path: "+ path_flow);
			
			// update residual capacities and reverse edge
			Vertex vv = sink;
			while( vv.name != source.name) {
				Vertex uu = vv.pi;
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> uvid_pair
					= G.VertexPair(uu,vv);
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> vuid_pair
					= G.VertexPair(vv,uu);
			
				G.setResidualCapacity(uu, vv, G.getResidualCapacity(uu, vv) - path_flow);
				if(!G.residualCapacity.containsKey(vuid_pair)) {
					G.adj_list.get(vv.getId()).add(G.v.get(uu.getId()));
					G.setResidualCapacity(vv, uu, path_flow);
				}else {
					G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				}
				vv = vv.pi;
			}
			//G.showResidualCapacity();
			iteration_BFS++; 
		}
		
		G.WriteWeightTofile(max_flow);
		System.out.print("Final Max flow value: ");
		return max_flow;
	}
}





