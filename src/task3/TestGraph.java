package task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class TestGraph extends Graph{
	public TestGraph() {
		super();
		SetforTest();
	}
	public void SetforTest() {
		adj_list = new HashMap<Pair<Integer,Integer>, ArrayList<Vertex>>(); 
		if(false) {
		//test graph1 
		Vertex A = new Vertex(0,0); v.put(A.getId(), A);
		Vertex B = new Vertex(2,0); v.put(B.getId(), B); 
		Vertex C = new Vertex(1,1); v.put(C.getId(), C);
		Vertex D = new Vertex(0,2); v.put(D.getId(), D);
		Vertex E = new Vertex(2,2); v.put(E.getId(), E);
		Vertex F = new Vertex(0,3); v.put(F.getId(), F);
		Vertex G = new Vertex(2,3); v.put(G.getId(), G);
		
		ArrayList<Vertex> a_adj = new ArrayList<>();
		a_adj.add(B); w.put(VertexPair(A,B), 3.0); 
		a_adj.add(D); w.put(VertexPair(A,D), 3.0); 
		adj_list.put(A.getId(), a_adj);
		
		ArrayList<Vertex> b_adj = new ArrayList<>();
		b_adj.add(C); w.put(VertexPair(B,C), 4.0); 
		adj_list.put(B.getId(), b_adj);
		
		ArrayList<Vertex> c_adj = new ArrayList<>();
		c_adj.add(A); w.put(VertexPair(C,A), 3.0);
		c_adj.add(D); w.put(VertexPair(C,D), 1.0);
		c_adj.add(E); w.put(VertexPair(C,E), 2.0);
		adj_list.put(C.getId(), c_adj);
		
		ArrayList<Vertex> d_adj = new ArrayList<>();
		d_adj.add(F); w.put(VertexPair(D,F), 6.0);
		d_adj.add(E); w.put(VertexPair(D,E), 2.0);
		adj_list.put(D.getId(), d_adj);
		
		ArrayList<Vertex> e_adj = new ArrayList<>();
		e_adj.add(B); w.put(VertexPair(E,B), 1.0); 
		e_adj.add(G); w.put(VertexPair(E,G), 1.0); 
		adj_list.put(E.getId(), e_adj);
		
		ArrayList<Vertex> f_adj = new ArrayList<>();
		f_adj.add(G); w.put(VertexPair(F,G), 9.0);  
		adj_list.put(F.getId(), f_adj);
		
		ArrayList<Vertex> g_adj = new ArrayList<>();
		adj_list.put(G.getId(), g_adj);
		}
		else {
		/*
		//test graph2 
		Vertex A = new Vertex(0,1); v.put(A.getId(), A);
		Vertex B = new Vertex(1,0); v.put(B.getId(), B); 
		Vertex C = new Vertex(2,0); v.put(C.getId(), C);
		Vertex D = new Vertex(3,0); v.put(D.getId(), D);
		Vertex E = new Vertex(4,1); v.put(E.getId(), E);
		Vertex F = new Vertex(1,2); v.put(F.getId(), F);
		Vertex G = new Vertex(2,2); v.put(G.getId(), G);
		Vertex H = new Vertex(3,2); v.put(H.getId(), H);
			
		ArrayList<Vertex> a_adj = new ArrayList<>();
		a_adj.add(B); w.put(VertexPair(A,B), 10.0); 
		a_adj.add(F); w.put(VertexPair(A,F), 10.0); 
		adj_list.put(A.getId(), a_adj);
			
		ArrayList<Vertex> b_adj = new ArrayList<>();
		b_adj.add(C); w.put(VertexPair(B,C), 10.0);
		b_adj.add(H); w.put(VertexPair(B,H), 1.0);
		adj_list.put(B.getId(), b_adj);
		
		ArrayList<Vertex> c_adj = new ArrayList<>();
		c_adj.add(D); w.put(VertexPair(C,D), 10.0);
		adj_list.put(C.getId(), c_adj);
			
		ArrayList<Vertex> d_adj = new ArrayList<>();
		d_adj.add(E); w.put(VertexPair(D,E), 10.0);	
		adj_list.put(D.getId(), d_adj);
			
		ArrayList<Vertex> e_adj = new ArrayList<>(); 
		adj_list.put(E.getId(), e_adj);
		
		ArrayList<Vertex> f_adj = new ArrayList<>();
		f_adj.add(G); w.put(VertexPair(F,G), 10.0);  
		adj_list.put(F.getId(), f_adj);
			
		ArrayList<Vertex> g_adj = new ArrayList<>();
		g_adj.add(H); w.put(VertexPair(G,H), 10.0); 
		adj_list.put(G.getId(), g_adj);
			
		ArrayList<Vertex> h_adj = new ArrayList<>();
		h_adj.add(E); w.put(VertexPair(H,E), 10.0); 
		adj_list.put(H.getId(), h_adj);
		*/
		
		//test graph3
		Vertex A = new Vertex(1,0); v.put(A.getId(), A);
		Vertex B = new Vertex(0,1); v.put(B.getId(), B); 
		Vertex C = new Vertex(2,1); v.put(C.getId(), C);
		Vertex D = new Vertex(0,2); v.put(D.getId(), D);
		Vertex E = new Vertex(2,2); v.put(E.getId(), E);
		Vertex F = new Vertex(1,3); v.put(F.getId(), F);
		
		ArrayList<Vertex> a_adj = new ArrayList<>();
		a_adj.add(B); w.put(VertexPair(A,B), 16.0); 
		a_adj.add(C); w.put(VertexPair(A,C), 13.0); 
		adj_list.put(A.getId(), a_adj);
			
		ArrayList<Vertex> b_adj = new ArrayList<>();
		b_adj.add(C); w.put(VertexPair(B,C), 10.0);
		b_adj.add(D); w.put(VertexPair(B,D), 12.0);
		adj_list.put(B.getId(), b_adj);
		
		ArrayList<Vertex> c_adj = new ArrayList<>();
		c_adj.add(B); w.put(VertexPair(C,B), 4.0);
		c_adj.add(E); w.put(VertexPair(C,E), 14.0);
		adj_list.put(C.getId(), c_adj);
			
		ArrayList<Vertex> d_adj = new ArrayList<>();
		d_adj.add(C); w.put(VertexPair(D,C), 9.0);
		d_adj.add(F); w.put(VertexPair(D,F), 20.0);
		adj_list.put(D.getId(), d_adj);
			
		ArrayList<Vertex> e_adj = new ArrayList<>();
		e_adj.add(D); w.put(VertexPair(E,D), 7.0);
		e_adj.add(F); w.put(VertexPair(E,F), 4.0);
		adj_list.put(E.getId(), e_adj);
		
		ArrayList<Vertex> f_adj = new ArrayList<>();  
		adj_list.put(F.getId(), f_adj);	
		}
	}
}

class TestResidualGraph extends TestGraph{
	// define a flow of an edge
	public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> flow 
		= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
	// define a residual capacity
		public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> residualCapacity
		= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
		
	// Constructor
	public TestResidualGraph() {
		initialize_flowAndresidualCapacity();
	}
		
	public void initialize_flowAndresidualCapacity() { 
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				Vertex vv = itj.next();
				Pair <Integer, Integer> vid = vv.getId(); 
					
				// flow(u,v) - f(u,v) = weight(u,v) // capacity(u,v)
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> uvid_pair
					= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer> >(uid,vid);
				//Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> vuid_pair
				//	= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer> >(vid,uid);
				flow.put(uvid_pair, 0.0);
				residualCapacity.put(uvid_pair, weight(v.get(uid),vv));
			}
		}
	}
	
	public double getflow(Vertex a, Vertex b) {
		return flow.get(VertexPair(a,b)); 
	}
	public void setflow(Vertex a, Vertex b, double _flow) {
		flow.put(VertexPair(a,b), _flow);
	}
	public double getResidualCapacity(Vertex a, Vertex b) {
		return residualCapacity.get(VertexPair(a,b));
	}
	public void setResidualCapacity(Vertex a, Vertex b, double _residualCapacity) {
		residualCapacity.put(VertexPair(a,b), _residualCapacity);
	}
	
	// show residualCapacity
	public void showResidualCapacity() {
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				Vertex vv = itj.next();
				Vertex uu = v.get(uid);
				if(w.containsKey(VertexPair(uu, vv))){
					System.out.print(" edge  (");
					uu.printId(); System.out.print(","); vv.printId(); 
					System.out.print(")"); System.out.print(" residualCapacity: ");
					//System.out.println(" "+ w.get(VertexPair(uu, vv)));
					System.out.println(getResidualCapacity(uu,vv)); 
				}
			}
		}
	}
	
	@Override // show flow / weight
	public void showWeight() {
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				Vertex vv = itj.next();
				Vertex uu = v.get(uid);
				if(w.containsKey(VertexPair(uu, vv))){
					System.out.print(" edge  (");
					uu.printId(); System.out.print(","); vv.printId(); 
					System.out.print(")"); System.out.print(" flow/weight: ");
					//System.out.println(" "+ w.get(VertexPair(uu, vv)));
					System.out.print(weight(uu,vv)- getResidualCapacity(uu,vv) + "/"); System.out.println(weight(uu,vv)); 
				}
			}
		}
	}
}