package task3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			//System.out.println("============================================================================================================================================================");
			//System.out.println("							"+ iteration_BFS +" th iteration BFS Result in Edmonds Algorithm: 							");
			//System.out.println("============================================================================================================================================================");
			//System.out.print("ShortestPath from source to sink using BFS: ");
			//System.out.println(ShortestPathList(G, G.v.get(source.getId()), G.v.get(sink.getId())));
			
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
			//System.out.println("Residual Capacity on the shortest path: "+ path_flow);
			
			// update residual capacities and reverse edge
			Vertex vv = sink;
			while( vv.name != source.name) {
				Vertex uu = vv.pi;
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> uvid_pair
					= G.VertexPair(uu,vv);
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> vuid_pair
					= G.VertexPair(vv,uu);

				G.setResidualCapacity(uu, vv, G.getResidualCapacity(uu, vv) - path_flow);
				G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				if((G.getflow(uu, vv) + path_flow) > G.weight(uu, vv)) {
					G.setflow(uu, vv, G.getflow(uu, vv) + (path_flow - G.getflow(vv, uu)));
					G.setflow(vv, uu, G.getflow(vv, uu) + (path_flow - G.getflow(uu, vv)));
				}else {
					G.setflow(uu, vv, G.getflow(uu, vv) + path_flow );
					G.setflow(vv, uu, G.getflow(vv, uu) + path_flow );
				}
				vv = vv.pi;
			}
			//G.showWeight();
			iteration_BFS++; 
		}
		
		//Problem 4-A
		G.WriteWeightTofile(max_flow);
		//Problem 4-B
		G.WriteSaturatedEgdeTofile(max_flow);
		System.out.print("Final Max flow value: ");
		return max_flow;
	}
	
	public boolean BFS_FordFulkerson_with_order1(ResidualGraph G, Vertex s, Vertex t) {
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
			
			// Problem 4C - Sort G.adj_list.get(uu.getId()) 
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			Ascending_VertexName Ascending = new Ascending_VertexName(G);
			Collections.sort(G.adj_list.get(uu.getId()), Ascending);
			/*
			Iterator<Vertex> Dubug_GadjOfu = G.adj_list.get(uu.getId()).iterator();
			while(Dubug_GadjOfu.hasNext()) {
				Vertex vv = Dubug_GadjOfu.next();
				uu.printId(); vv.printId(); System.out.println(vv.name); 
			}
			*/
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			
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
	
	public boolean BFS_FordFulkerson_with_order2(ResidualGraph G, Vertex s, Vertex t) {
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
			
			// Problem 4C - Sort G.adj_list.get(uu.getId()) 
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			Ascending_ResidualCapacity Ascending = new Ascending_ResidualCapacity(G,uu);
			Collections.sort(G.adj_list.get(uu.getId()), Ascending);
			/*
			Iterator<Vertex> Dubug_GadjOfu = G.adj_list.get(uu.getId()).iterator();
			while(Dubug_GadjOfu.hasNext()) {
				Vertex vv = Dubug_GadjOfu.next();
				uu.printId(); vv.printId(); System.out.println(G.getResidualCapacity(uu, vv)); 
			}
			*/
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			
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
	
	public double FordFurkerson_order1(ResidualGraph G, Vertex source, Vertex sink) {
		double max_flow = 0;
		
		int iteration_BFS = 0;
		while(BFS_FordFulkerson_with_order1(G,G.v.get(source.getId()),G.v.get(sink.getId()))) {
			// Debug: See BFS path
			System.out.println("============================================================================================================================================================");
			System.out.println("							"+ iteration_BFS +" th iteration BFS_FordFulkerson Result in FordFulkerson Algorithm: 							");
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
				G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				if((G.getflow(uu, vv) + path_flow) > G.weight(uu, vv)) {
					G.setflow(uu, vv, G.getflow(uu, vv) + (path_flow - G.getflow(vv, uu)));
					G.setflow(vv, uu, G.getflow(vv, uu) + (path_flow - G.getflow(uu, vv)));
				}else {
					G.setflow(uu, vv, G.getflow(uu, vv) + path_flow );
					G.setflow(vv, uu, G.getflow(vv, uu) + path_flow );
				}
				vv = vv.pi;
			}
			iteration_BFS++;
			//G.showWeight();
		}
		//G.WriteWeightTofile(max_flow);
		System.out.print("Final Max flow value: ");
		return max_flow;
	}
	
	public double FordFurkerson_order2(ResidualGraph G, Vertex source, Vertex sink) {
		double max_flow = 0;
		
		int iteration_BFS = 0;
		while(BFS_FordFulkerson_with_order2(G,G.v.get(source.getId()),G.v.get(sink.getId()))) {
			// Debug: See BFS path
			System.out.println("============================================================================================================================================================");
			System.out.println("							"+ iteration_BFS +" th iteration BFS_FordFulkerson Result in FordFulkerson Algorithm: 							");
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
				G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				if((G.getflow(uu, vv) + path_flow) > G.weight(uu, vv)) {
					G.setflow(uu, vv, G.getflow(uu, vv) + (path_flow - G.getflow(vv, uu)));
					G.setflow(vv, uu, G.getflow(vv, uu) + (path_flow - G.getflow(uu, vv)));
				}else {
					G.setflow(uu, vv, G.getflow(uu, vv) + path_flow );
					G.setflow(vv, uu, G.getflow(vv, uu) + path_flow );
				}
				vv = vv.pi;
			}
			iteration_BFS++;
			//G.showWeight();
		}
		//G.WriteWeightTofile(max_flow);
		System.out.print("Final Max flow value: ");
		return max_flow;
	}
	
}

class Ascending_ResidualCapacity implements Comparator<Vertex>{
	
	public ResidualGraph G;
	public Vertex u;
	
	Ascending_ResidualCapacity(ResidualGraph _G, Vertex _u){
		G = _G;
		u = _u;
	}
	
	@Override
	public int compare(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		if (G.getResidualCapacity(u, v1) < G.getResidualCapacity(u, v2)) return 1;
		else if(G.getResidualCapacity(u, v1) == G.getResidualCapacity(u, v2)) return 0;
		else return -1;
	} 
}

class Ascending_VertexName implements Comparator<Vertex>{
	public ResidualGraph G;
	Ascending_VertexName(ResidualGraph _G){
		G = _G;
	}
	@Override
	public int compare(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		if (G.v.get(v1.getId()).name > G.v.get(v2.getId()).name) return 1;
		else if( G.v.get(v1.getId()).name < G.v.get(v2.getId()).name) return -1;
		else return 0;
	} 
}






