package task3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class ProblemCGraph {
	public int num_vertices = 0;
	public HashMap<Integer, ArrayList<Vertex>> adj_list; // HashMap< vertex id, G.adjacent[vertex] >
	public double[][] weight_mat = null;
	public Vertex[] vertice = null;
	public double[][] flow = null;
	public double[][] residualCapacity = null;
	
	public ProblemCGraph() {} // default Constructor
	public ProblemCGraph(String FILE_PATH) {
		MakeWeight_mat(FILE_PATH);
		MakeVertice();
		MakeAdjList();
		Initialize_flowAndresidualCapacity();
	}
	public void MakeWeight_mat(String FILE_PATH) {
		File f = null;
		FileReader fr = null;
		BufferedReader in_file = null;
				
		try{
			f = new File(FILE_PATH);
			fr = new FileReader(f);
			in_file = new BufferedReader(fr); 
			if(new File(FILE_PATH).exists()) {
				String temp = (new BufferedReader(new FileReader(new File(FILE_PATH)))).readLine();
				num_vertices = temp.split("\t").length;	
			}
			weight_mat = new double[num_vertices][num_vertices];
			if (f.exists()) {
				int i = 0;
				for (String str = in_file.readLine(); str != null; str = in_file.readLine()) {
					String []vector = str.split("\t");
					for (int j=0; j<vector.length; j++) {
						weight_mat[i][j] = Double.parseDouble(vector[j]);
					}
				i++;
				}
			}
		}catch (Exception e){
			System.out.println("I/O error" + e);
		}finally{
			try{
				in_file.close();
			}catch (Exception e){
				System.out.println("I/O error" + e);
			}
		}
	}
	
	public void MakeVertice() {
		vertice = new Vertex[num_vertices];
		for (int i=0; i<num_vertices; i++ ) {
			vertice[i] = new Vertex(i);
		}
	}
	public void showWeightMat() {
		for (int i=0; i<num_vertices; i++ ) {
			for (int j=0; j<num_vertices; j++ ) {
				System.out.print(weight_mat[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public void MakeAdjList() {
		adj_list = new HashMap<Integer, ArrayList<Vertex>>();
		for (int i=0; i<num_vertices; i++ ) {
			adj_list.put(i, new ArrayList<Vertex>());
			for (int j=0; j<num_vertices; j++ ) {
				if(weight_mat[i][j] != -1.0) {
					adj_list.get(i).add(vertice[j]);
				}
			}
		}
	}
	public void showAdjList() {
		Iterator<Integer> iti = adj_list.keySet().iterator();
		while(iti.hasNext()){
			Vertex I = vertice[iti.next()];
			System.out.print("Vertex.name: "); I.printName(); System.out.print(" "); 
			Iterator<Vertex> itj = adj_list.get(I.name).iterator();
			System.out.print("adj_list.name: ");
			while(itj.hasNext()) {
				Vertex J = itj.next();
				J.printName(); System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void Initialize_flowAndresidualCapacity() { 
		flow = new double[num_vertices][num_vertices];
		residualCapacity = new double[num_vertices][num_vertices];
		
		for (int i=0; i<num_vertices; i++ ) {
			for (int j=0; j<num_vertices; j++ ) {
				flow[i][j] = 0.0;
				if(weight_mat[i][j] != -1.0) {
					residualCapacity[i][j] = weight_mat[i][j];
				}else {
					residualCapacity[i][j] = 0;
				}
			}
		}
	}
	
	public void showflowWeight() {
		Iterator<Integer> iti = adj_list.keySet().iterator();
		while(iti.hasNext()){
			Vertex I = vertice[iti.next()];
			Iterator<Vertex> itj = adj_list.get(I.name).iterator();
			while(itj.hasNext()) {
				Vertex J = itj.next();
				System.out.print("("); I.printName(); System.out.print(","); 
				J.printName(); System.out.print(")"); System.out.print("flow/Weight: ");
				System.out.print(flow[I.name][J.name]); System.out.print("/"); System.out.println(weight_mat[I.name][J.name]);
			}
		}
	}
	
	public double getweight(Vertex a, Vertex b) {
		return weight_mat[a.name][b.name];
	}
	public void setweight(Vertex a, Vertex b, double _weight) {
		weight_mat[a.name][b.name] = _weight;
	}
	
	public double getflow(Vertex a, Vertex b) {
		return flow[a.name][b.name]; 
	}
	public void setflow(Vertex a, Vertex b, double _flow) {
		flow[a.name][b.name] = _flow;
	}
	public double getResidualCapacity(Vertex a, Vertex b) {
		return residualCapacity[a.name][b.name];
	}
	public void setResidualCapacity(Vertex a, Vertex b, double _residualCapacity) {
		residualCapacity[a.name][b.name] = _residualCapacity;
	}
}

class ProblemC{
	// Problem 4-C
	public boolean BFS_FordFulkerson_with_order1(ProblemCGraph G, Vertex s, Vertex t) {	
		for(int i=0; i<G.num_vertices; i++) {
			G.vertice[i].visited = false;
		}
		ArrayDeque<Vertex> Q = new ArrayDeque<>(); 
		Q.push(G.vertice[s.name]);
		G.vertice[s.name].visited = true;
			
		while(!Q.isEmpty()) {
			Vertex uu = Q.pop();	
			// Problem 4C - Sort G.adj_list.get(uu.getId()) 
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			Ascending1 Ascending = new Ascending1(G);
			Collections.sort(G.adj_list.get(uu.name), Ascending);
			/*
			Iterator<Vertex> Dubug_GadjOfu = G.adj_list.get(uu.name).iterator();
			while(Dubug_GadjOfu.hasNext()) {
			Vertex vv = Dubug_GadjOfu.next();
			System.out.print("("); uu.printName(); System.out.print(","); vv.printName(); System.out.print(")"); System.out.println(vv.name); 
			}
			*/
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");	
			Iterator<Vertex> GadjOfu = G.adj_list.get(uu.name).iterator();
			while(GadjOfu.hasNext()) {
				Vertex vv = GadjOfu.next();
				boolean ConditionOfResidualCapacity = G.residualCapacity[uu.name][vv.name] > 0;
				if ((G.vertice[vv.name].visited == false )&&( ConditionOfResidualCapacity )) {
					Q.push(G.vertice[vv.name]);
					G.vertice[vv.name].visited = true;
					
					// path info update 
					G.vertice[vv.name].pi = uu;
				}
			}
		}
		return G.vertice[t.name].visited;
	}	
	
	public boolean BFS_FordFulkerson_with_order2(ProblemCGraph G, Vertex s, Vertex t) {	
		for(int i=0; i<G.num_vertices; i++) {
			G.vertice[i].visited = false;
		}
		ArrayDeque<Vertex> Q = new ArrayDeque<>(); 
		Q.push(G.vertice[s.name]);
		G.vertice[s.name].visited = true;
			
		while(!Q.isEmpty()) {
			Vertex uu = Q.pop();	
			// Problem 4C - Sort G.adj_list.get(uu.getId()) 
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");
			Ascending2 Ascending = new Ascending2(G,uu);
			Collections.sort(G.adj_list.get(uu.name), Ascending);
			/*
			Iterator<Vertex> Dubug_GadjOfu = G.adj_list.get(uu.name).iterator();
			while(Dubug_GadjOfu.hasNext()) {
			Vertex vv = Dubug_GadjOfu.next();
			System.out.print("("); uu.printName(); System.out.print(","); vv.printName(); System.out.print(")"); 
			System.out.println(G.getResidualCapacity(uu, vv)); 
			}
			*/
			//System.out.println("====================== G.adj_list.get(uu.getId()) =================== ");	
			Iterator<Vertex> GadjOfu = G.adj_list.get(uu.name).iterator();
			while(GadjOfu.hasNext()) {
				Vertex vv = GadjOfu.next();
				boolean ConditionOfResidualCapacity = G.residualCapacity[uu.name][vv.name] > 0;
				if ((G.vertice[vv.name].visited == false )&&( ConditionOfResidualCapacity )) {
					Q.push(G.vertice[vv.name]);
					G.vertice[vv.name].visited = true;
					
					// path info update 
					G.vertice[vv.name].pi = uu;
				}
			}
		}
		return G.vertice[t.name].visited;
	}	
	
	public double FordFurkerson_order1(ProblemCGraph G, Vertex source, Vertex sink) {
		double max_flow = 0;
		
		int iteration_BFS = 0;
		while(BFS_FordFulkerson_with_order1(G,G.vertice[source.name],G.vertice[sink.name])) {
			// Debug: See BFS path
			//System.out.println("============================================================================================================================================================");
			//System.out.println("							"+ iteration_BFS +" th iteration BFS_FordFulkerson Result in FordFulkerson Algorithm: 							");
			//System.out.println("============================================================================================================================================================");
			//System.out.print("ShortestPath from source to sink using BFS: ");
			//System.out.println(ShortestPathList(G, G.vertice[source.name], G.vertice[sink.name]));
			
			// Find the Residual Capacity on the Shortest Path
			double path_flow = Double.MAX_VALUE;
			Vertex back1 = sink;
			while( back1.name != source.name ) {
				path_flow = Math.min(path_flow, G.residualCapacity[back1.pi.name][back1.name]);
				back1 = back1.pi;
			}
			max_flow += path_flow;
			//System.out.println("Residual Capacity on the shortest path: "+ path_flow);
			
			// update residual capacities and reverse edge
			Vertex vv = sink;
			while( vv.name != source.name) {
				Vertex uu = vv.pi;
				
				G.setResidualCapacity(uu, vv, G.getResidualCapacity(uu, vv) - path_flow);
				G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				if( ( ( G.getflow(uu, vv) + path_flow ) > G.getweight(uu, vv) ) && ( G.getweight(uu, vv) != -1 ) ) {
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
		System.out.print("1) Final Max flow value: ");
		return max_flow;
	}
	
	public double FordFurkerson_order2(ProblemCGraph G, Vertex source, Vertex sink) {
		double max_flow = 0;
		
		int iteration_BFS = 0;
		while(BFS_FordFulkerson_with_order2(G,G.vertice[source.name],G.vertice[sink.name])) {
			// Debug: See BFS path
			//System.out.println("============================================================================================================================================================");
			//System.out.println("							"+ iteration_BFS +" th iteration BFS_FordFulkerson Result in FordFulkerson Algorithm: 							");
			//System.out.println("============================================================================================================================================================");
			//System.out.print("ShortestPath from source to sink using BFS: ");
			//System.out.println(ShortestPathList(G, G.vertice[source.name], G.vertice[sink.name]));
			
			// Find the Residual Capacity on the Shortest Path
			double path_flow = Double.MAX_VALUE;
			Vertex back1 = sink;
			while( back1.name != source.name ) {
				path_flow = Math.min(path_flow, G.residualCapacity[back1.pi.name][back1.name]);
				back1 = back1.pi;
			}
			max_flow += path_flow;
			//System.out.println("Residual Capacity on the shortest path: "+ path_flow);
			
			// update residual capacities and reverse edge
			Vertex vv = sink;
			while( vv.name != source.name) {
				Vertex uu = vv.pi;
				
				G.setResidualCapacity(uu, vv, G.getResidualCapacity(uu, vv) - path_flow);
				G.setResidualCapacity(vv, uu, G.getResidualCapacity(vv, uu) + path_flow);
				if( ( ( G.getflow(uu, vv) + path_flow ) > G.getweight(uu, vv) ) && ( G.getweight(uu, vv) != -1 ) ) {
					G.setflow(uu, vv, G.getflow(uu, vv) + (path_flow - G.getflow(vv, uu)));
					G.setflow(vv, uu, G.getflow(vv, uu) + (path_flow - G.getflow(uu, vv)));
				}else {
					G.setflow(uu, vv, G.getflow(uu, vv) + path_flow );
					G.setflow(vv, uu, G.getflow(vv, uu) + path_flow );
				}
				vv = vv.pi;
			}
			iteration_BFS++;
		}
		System.out.print("2) Final Max flow value: ");
		return max_flow;
	}
}
class Ascending1 implements Comparator<Vertex>{
	public ProblemCGraph G;
	Ascending1(ProblemCGraph _G){
		G = _G;
	}
	@Override
	public int compare(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		if (G.vertice[v1.name].name > G.vertice[v2.name].name) return 1;
		else if(G.vertice[v1.name].name < G.vertice[v2.name].name) return -1;
		else return 0;
	} 
}

class Ascending2 implements Comparator<Vertex>{
	
	public ProblemCGraph G;
	public Vertex u;
	
	Ascending2(ProblemCGraph _G, Vertex _u){
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
