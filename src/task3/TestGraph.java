package task3;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class TestGraph extends Graph{
	public TestGraph() {
		super();
		SetforTest();
	}
	public void SetforTest() {
		adj_list = new HashMap<Pair<Integer,Integer>, ArrayList<Vertex>>(); 
		
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
		
		num_grid = 4;
		
		/*
		System.out.println("a_adj: " + a_adj);
		System.out.println("b_adj: " + b_adj);
		System.out.println("c_adj: " + c_adj);
		System.out.println("d_adj: " + d_adj);
		System.out.println("e_adj: " + e_adj);
		System.out.println("f_adj: " + f_adj);
		System.out.println("g_adj: " + f_adj);
		*/
	}
}
